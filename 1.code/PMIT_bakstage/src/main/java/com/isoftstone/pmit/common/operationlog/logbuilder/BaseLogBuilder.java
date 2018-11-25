package com.isoftstone.pmit.common.operationlog.logbuilder;

import com.isoftstone.pmit.common.operationlog.ILogBuilder;
import com.isoftstone.pmit.common.operationlog.entity.OperationLogVO;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BaseLogBuilder implements ILogBuilder{
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

    @Override
    public OperationLogVO getOperationLogVO(ServletRequestAttributes attributes,JoinPoint joinPoint) {
        OperationLogVO operationLogVO = new OperationLogVO();
        HttpServletRequest request = attributes.getRequest();

        operationLogVO.setTime(new Date());
        operationLogVO.setUrl(request.getRequestURL().toString());
        operationLogVO.setIPAddress( request.getRemoteAddr());

        // TODO: 2018/11/24 cookie中获取用户信息
        operationLogVO.setAccount("admin");

        operationLogVO.setMethod(request.getMethod());
        operationLogVO.setParam(buildParam(attributes,joinPoint));
        operationLogVO.setMessage(buildMessage(attributes,joinPoint,operationLogVO));

        return operationLogVO;
    }

    @Override
    public String buildMessage(ServletRequestAttributes attributes,JoinPoint joinPoint,OperationLogVO operationLogVO) {
        HttpServletRequest request = attributes.getRequest();
        String message = "amdin perform " + request.getRequestURL().toString()
                + " on " + simpleDateFormat.format(operationLogVO.getTime());
        return message;
    }

    @Override
    public String buildParam(ServletRequestAttributes attributes,JoinPoint joinPoint) {
        String params =  Arrays.toString(joinPoint.getArgs());
        return params;
    }
}
