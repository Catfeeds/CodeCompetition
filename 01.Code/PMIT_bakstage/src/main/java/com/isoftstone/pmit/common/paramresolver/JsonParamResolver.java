package com.isoftstone.pmit.common.paramresolver;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.isoftstone.pmit.common.exception.ParamResolveException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class JsonParamResolver implements HandlerMethodArgumentResolver {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    //判断是否是要转换的参数（加了注解的参数）
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonParam.class);
    }

    //当支持后进行相应的转换
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object param;

        JsonParam jsonParam = parameter.getParameterAnnotation(JsonParam.class);
        String value = jsonParam.value();
        boolean required = jsonParam.required();
        String requestBody = getRequestBody(webRequest);
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        if (jsonObject == null) {
            if (required) {
                throw new ParamResolveException(value);
            }
            param = null;
        } else {
            Type type = parameter.getGenericParameterType();
            JavaType javaType = getJavaType(type);
            param = objectMapper.readValue(jsonObject.getString(value), javaType);
        }
        return param;
    }

    private String getRequestBody(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String jsonBody = (String) servletRequest.getAttribute("JSON_REQUEST_BODY");
        if (jsonBody == null) {
            try {
                InputStream ss = servletRequest.getInputStream();
                jsonBody = IOUtils.toString(ss,"UTF-8");
                ss.close();
                servletRequest.setAttribute("JSON_REQUEST_BODY", jsonBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonBody;
    }

    public static JavaType getJavaType(Type type) {
        //判断是否带有泛型
        if (type instanceof ParameterizedType) {
            //获取基本类型
            Class rowClass = (Class) ((ParameterizedType) type).getRawType();

            //获取泛型的类型，可以是多个
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            JavaType[] javaTypes = new JavaType[actualTypeArguments.length];

            //泛型也可能带有泛型，递归获取
            for (int i = 0; i < actualTypeArguments.length; i++) {
                javaTypes[i] = getJavaType(actualTypeArguments[i]);
            }
            return TypeFactory.defaultInstance().constructParametricType(rowClass, javaTypes);
        } else {
            //简单类型直接用该类构建JavaType
            Class cla = (Class) type;
            return TypeFactory.defaultInstance().constructParametricType(cla, new JavaType[0]);
        }
    }
}
