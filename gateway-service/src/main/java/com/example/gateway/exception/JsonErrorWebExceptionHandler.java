package com.example.gateway.exception;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import java.util.HashMap;
import java.util.Map;

public class JsonErrorWebExceptionHandler /*extends DefaultErrorWebExceptionHandler*/ {

//    public JsonErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ErrorProperties errorProperties, ApplicationContext applicationContext) {
//        super(errorAttributes, resources, errorProperties, applicationContext);
//    }
//
//    @Override
//    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
//        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
//    }
//
//    @Override
//    protected int getHttpStatus(Map<String, Object> errorAttributes) {
//        return HttpStatus.INTERNAL_SERVER_ERROR.value();
//    }
//
//    @Override
//    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
//        Throwable error = super.getError(request);
//        Map<String, Object> errorsAttributes = new HashMap<>();
//        errorsAttributes.put("message", error.getMessage());
//        errorsAttributes.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        errorsAttributes.put("method", request.methodName());
//        errorsAttributes.put("path", request.path());
//        return errorsAttributes;
//    }
}
