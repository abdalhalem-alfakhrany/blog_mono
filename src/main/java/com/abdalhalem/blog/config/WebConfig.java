package com.abdalhalem.blog.config;

import java.lang.reflect.Method;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @SuppressWarnings("null")
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping(
            ContentNegotiationManager contentNegotiationManager, FormattingConversionService conversionService,
            ResourceUrlProvider resourceUrlProvider) {

        return new RequestMappingHandlerMapping() {

            protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
                String packageName = handlerType.getPackageName();

                String prefix = "";
                if (packageName.startsWith("com.abdalhalem.blog.api")) {
                    prefix = "/api";
                } else if (packageName.startsWith("com.abdalhalem.blog.dashboard")) {
                    prefix = "/dashboard";
                } else if (packageName.startsWith("com.abdalhalem.blog.web")) {
                    prefix = "/";
                }

                RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);

                if (mappingInfo != null) {
                    mappingInfo = RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
                    return mappingInfo;
                }

                return null;
            }
        };
    }
}
