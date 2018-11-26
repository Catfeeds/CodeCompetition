package com.isoftstone.pmit.common.operationlog;

import com.isoftstone.pmit.common.operationlog.entity.OperationLogVO;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.ServletRequestAttributes;



public interface ILogBuilder {
    //构建日志入库VO类对象
    public OperationLogVO getOperationLogVO(ServletRequestAttributes attributes,JoinPoint joinPoint);

    //构建日志对象Message字段
    public String buildMessage(ServletRequestAttributes attributes,JoinPoint joinPoint,OperationLogVO operationLogVO);

    //构建日志对象Param集合
    public String buildParam(ServletRequestAttributes attributes,JoinPoint joinPoint);
}
