package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {

    /**
     * 获取用户信息做标示/用户登录
     *
     * @param employeeID
     * @return
     */
    LoginInformation findStaffByEmployeeId(@Param("employeeID") String employeeID);


}
