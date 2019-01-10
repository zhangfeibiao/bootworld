package com.zfb.bootworld.system.common;


import com.zfb.bootworld.system.enums.ErrorCodeEnum;
import com.zfb.bootworld.system.exception.BusinessException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class CommonErrorController extends AbstractErrorController {
    private static final Logger log = LoggerFactory.getLogger(CommonErrorController.class);
    private final ErrorProperties errorProperties;
    private final ErrorAttributes errorAttributes;
    private final String errorCodePrefix;

    public CommonErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties,
                                 String errorCodePrefix) {
        this(errorAttributes, errorProperties, Collections.emptyList(), errorCodePrefix);
    }

    public CommonErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties,
                                 List<ErrorViewResolver> errorViewResolvers, String errorCodePrefix) {
        super(errorAttributes, errorViewResolvers);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        Assert.hasText(errorCodePrefix, "errorCodePrefix  must not be null");
        this.errorProperties = errorProperties;
        this.errorAttributes = errorAttributes;
        this.errorCodePrefix = errorCodePrefix;
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

    @RequestMapping(produces = {"text/html"})
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        return modelAndView == null ? new ModelAndView("error", model) : modelAndView;
    }

    protected Exception getError(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        WebRequest webRequest = (WebRequest) requestAttributes;
        return (Exception) this.errorAttributes.getError(webRequest);
    }

    @ResponseBody
    @RequestMapping
    public ResponseEntity<Response<Map<String, Object>>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        String traceId = MDC.get("X-B3-TraceId");

        Response<Map<String, Object>> response = new Response();
        response.setSuccess(Integer.valueOf(0));
        response.setErrorCode(this.errorCodePrefix + ".service_error");
        response.setErrorMsg("服务繁忙,请稍后重试!");


        try {
            Exception ex = getError(request);

            if (ex != null) {
                throw ex;
            }
        } catch (NoHandlerFoundException var1) {
            status = HttpStatus.NOT_FOUND;
            response = Response.failure(ErrorCodeEnum.NOT_FOUND);
        } catch (HttpRequestMethodNotSupportedException var2) {
            status = HttpStatus.METHOD_NOT_ALLOWED;
            response = Response.failure(ErrorCodeEnum.METHOD_NOT_ALLOWED);
        } catch (HttpMediaTypeNotAcceptableException var3) {
            status = HttpStatus.NOT_ACCEPTABLE;
            response = Response.failure(ErrorCodeEnum.NOT_ACCEPTABLE);
        } catch (HttpMediaTypeNotSupportedException var4) {
            status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
            response = Response.failure(ErrorCodeEnum.UNSUPPORTED_MEDIA_TYPE);
        } catch (MissingServletRequestParameterException var5) {
            status = HttpStatus.BAD_REQUEST;
            response = Response.failure(ErrorCodeEnum.BAD_REQUEST);

        } catch (HttpMessageNotReadableException var6) {
            status = HttpStatus.BAD_REQUEST;
            response = Response.failure(ErrorCodeEnum.JSON_CONVERT_ERROR);

        } catch (ConstraintViolationException var7) {
            Set<ConstraintViolation<?>> constraintViolations = var7.getConstraintViolations();
            String errorMsg = "";
            for (ConstraintViolation violation : constraintViolations) {
                errorMsg += violation.getMessageTemplate();
            }
            status = HttpStatus.BAD_REQUEST;
            response = Response.failure(ErrorCodeEnum.BAD_REQUEST.getErrorCode(), errorMsg);

        } catch (Exception var8) {
            log.error("业务处理失败,未知异常!Exception:{}", ExceptionUtils.getStackTrace(var8));
            response = Response.failure(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }

        response.setTraceId(traceId);
        return new ResponseEntity(response, new HttpHeaders(), status);
    }
}
