package com.zfb.bootworld.system.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.stream.Collectors;


/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理
 * @Author: zhangfeibiao
 * @CreateDate: 2018/12/04 14:37
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";

    @Value("${error.path:/error}")
    private String errorPath;

    /**
     * 转发异常至统一处理，避免日志记录ERROR级别的业务异常
     * @param request
     * @param response
     * @param ex
     * @throws ServletException
     * @throws IOException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public void error(HttpServletRequest request, HttpServletResponse response, ConstraintViolationException ex) throws ServletException, IOException {
        // 将异常装换成业务异常。
        final String msg = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        log.debug("参数错误信息：{}",msg);

        BusinessException businessException = new BusinessException("400", msg);
        request.setAttribute("javax.servlet.error.status_code", 200);
        request.setAttribute("javax.servlet.error.exception", businessException);
        request.setAttribute(ERROR_ATTRIBUTE, businessException);
        request.getRequestDispatcher(this.errorPath).forward(request, response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private void illegalParamsExceptionHandler(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException e) throws ServletException, IOException {
        log.error("---------> invalid request!", e);

        BusinessException businessException = new BusinessException("400", "参数错误");
        request.setAttribute("javax.servlet.error.status_code", 200);
        request.setAttribute("javax.servlet.error.exception", businessException);
        request.setAttribute(ERROR_ATTRIBUTE, businessException);
        request.getRequestDispatcher(this.errorPath).forward(request, response);
    }



    /**
     * 转发异常至统一处理，避免日志记录ERROR级别的业务异常
     * @param request
     * @param response
     * @param ex
     * @throws ServletException
     * @throws IOException
     */
    @ExceptionHandler(BusinessException.class)
    public void error(HttpServletRequest request, HttpServletResponse response, BusinessException ex) throws ServletException, IOException {

        request.setAttribute("javax.servlet.error.status_code", 200);
        request.setAttribute("javax.servlet.error.exception", ex);
        request.getRequestDispatcher(this.errorPath).forward(request, response);
    }

/**
     * 转发异常至统一处理，避免日志记录ERROR级别的业务异常
     * @param request
     * @param response
     * @param ex
     * @throws ServletException
     * @throws IOException
     */
    @ExceptionHandler(BadCredentialsException.class)
    public void error(HttpServletRequest request, HttpServletResponse response, BadCredentialsException ex) throws ServletException, IOException {

        request.setAttribute("javax.servlet.error.status_code", 200);
        request.setAttribute("javax.servlet.error.exception", ex);
        request.getRequestDispatcher(this.errorPath).forward(request, response);
    }/**
     * 转发异常至统一处理，避免日志记录ERROR级别的业务异常
     * @param request
     * @param response
     * @param ex
     * @throws ServletException
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    public void error(HttpServletRequest request, HttpServletResponse response, Exception ex) throws ServletException, IOException {

        request.setAttribute("javax.servlet.error.status_code", 200);
        request.setAttribute("javax.servlet.error.exception", ex);
        request.getRequestDispatcher(this.errorPath).forward(request, response);
    }

}

