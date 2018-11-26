package com.isoftstone.pmit.common.operationlog.mapper;

import com.isoftstone.pmit.common.operationlog.entity.OperationLogVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogMapper {
    public void insertLog(OperationLogVO operationLogVO);
}
