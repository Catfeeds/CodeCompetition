package com.isoftstone.pmit.common.operationlog;

import com.isoftstone.pmit.common.operationlog.entity.OperationLogVO;
import com.isoftstone.pmit.common.operationlog.mapper.OperationLogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private LogBuilderManager logBuilderManager;


    @Pointcut("execution(public * com.isoftstone.pmit.system.test.controller.*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        ILogBuilder logBuilder = logBuilderManager.getLogBuilderByUrl(url);
        OperationLogVO operationLogVO = logBuilder.getOperationLogVO(attributes,joinPoint);
        operationLogMapper.insertLog(operationLogVO);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }
}