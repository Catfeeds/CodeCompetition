package com.isoftstone.pmit.project.hrbp.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.EmpInformationResult;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;

/**
 * @author lf
 */
public interface IUserManageService {
    /**
     * 获取用户信息 做标示
     * @param employeeID
     * @return
     */
    LoginInformation getEmployee(String employeeID);

    /**
     * 根据employeeID获取详细用户信息
     * @param employeeID
     * @return
     */
    EmpInformationResult findEmpInformationByEmpId(String employeeID);

    /**
     * 获取全部用户信息
     * @param
     * @return
     */
    PageInfo<EmpInformationResult> findEmpInformation(Integer pageNum, Integer pageSize);
}
