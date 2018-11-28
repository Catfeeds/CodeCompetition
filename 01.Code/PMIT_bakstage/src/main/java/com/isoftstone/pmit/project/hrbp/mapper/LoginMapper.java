package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {
    /**
     * 获取用户信息
     *
     * @param employeeID
     * @return
     */
    LoginInformation findStaffByEmployeeId(String employeeID);

}
