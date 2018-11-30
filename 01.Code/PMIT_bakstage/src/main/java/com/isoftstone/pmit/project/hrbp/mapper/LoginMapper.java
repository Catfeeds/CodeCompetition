package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.EmpInformationResult;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginMapper {
    /**
     * 获取用户信息做标示
     *
     * @param employeeID
     * @return
     */
    LoginInformation findStaffByEmployeeId(@Param("employeeID") String employeeID);

    /**
     * 根据empolyeeID获取用户具体信息
     * @param employeeID
     * @return
     */
    EmpInformationResult findEmpInformationByEmpId(String employeeID);

    /**
     * 获取全部用户信息
     * @return
     */
    List<EmpInformationResult> findEmpInformation();
}
