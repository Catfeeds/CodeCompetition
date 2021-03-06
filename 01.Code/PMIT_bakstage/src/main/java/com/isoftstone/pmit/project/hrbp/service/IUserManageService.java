package com.isoftstone.pmit.project.hrbp.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.EmpInformationResult;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import com.isoftstone.pmit.project.hrbp.entity.PageParam;

import java.util.List;

/**
 * @author lf
 */
public interface IUserManageService {
    /**
     * 获取用户信息 做标示
     *
     * @param employeeID
     * @return
     */
    LoginInformation getEmployee(String employeeID);

    /**
     * 根据employeeID获取详细用户信息
     *
     * @param employeeID
     * @return
     */
    EmpInformationResult findEmpInformationByEmpId(String employeeID);

    /**
     * 获取全部用户信息
     *
     * @param
     * @return
     */
    PageInfo<EmpInformationResult> findEmpInformation(PageParam pageInfo);

    /**
     * 模糊查询用户信息
     *
     * @param parameter
     * @return
     */
    List<EmpInformationResult> queryUserByKeyword(String parameter);

    /**
     * 删除用户角色
     *
     * @param employeeID
     */
    void deleteUserRole(String employeeID);

    /**
     * 添加用户角色
     *
     * @param loginInformation
     */
    void insertUserRole(LoginInformation loginInformation);

    /**
     * 更新用户信息
     *
     * @param loginInformation
     */
    void updateUserRole(LoginInformation loginInformation);

    /**
     * 查询所有用户信息
     * @return
     */
    List<EmpInformationResult> findEmpInformation();

    /**
     * 根据个人工号，修改密码
     * @param employeeID
     * @param changePwd
     */
    void updateUserPassword(String employeeID,String changePwd);
}
