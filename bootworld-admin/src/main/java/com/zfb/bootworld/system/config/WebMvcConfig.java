package com.zfb.bootworld.system.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zfb.bootworld.system.common.CommonErrorController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.*;

import java.io.IOException;

/**
 *
 *
 *
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] AUTH_WHITELIST = {

            // -- swagger ui
            "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**", "/autoconfig", "/beans",
            "/configprops", "/dump", "/env", "/health", "/info", "/metrics",

            "/mappings", "/shutdown", "trace"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowCredentials(true).allowedHeaders("X" +
                "-Requested-With,Token,deviceNo,authorizationCode").maxAge(3600);
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        // solved swagger2
        registry.addRedirectViewController("/v2/api-docs", "/v2/api-docs?group=restful-api");
        registry.addRedirectViewController("/swagger-resources/configuration/ui", "/swagger-resources/configuration" +
                "/ui");
        registry.addRedirectViewController("/swagger-resources/configuration/security", "/swagger-resources" +
                "/configuration/security");
        registry.addRedirectViewController("/swagger-resources", "/swagger-resources");
    }

    /**
     * 增加自定义目录映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        // solved swagger2
        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger" +
                "-ui.html");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    // Validator参数校验
    @Override
    public Validator getValidator() {

        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        return bean;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.favorPathExtension(false);

    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public CommonErrorController commonErrorController(ErrorAttributes errorAttributes,
                                                       ServerProperties serverProperties) {
        return new CommonErrorController(errorAttributes, serverProperties.getError(), "bootworld");
    }

    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString("");
            }
        });
        return objectMapper;
    }


}
