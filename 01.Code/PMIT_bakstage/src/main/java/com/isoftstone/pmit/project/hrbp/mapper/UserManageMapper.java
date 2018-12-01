package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.EmpInformationResult;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lf
 */
@Mapper
@Repository
public interface UserManageMapper {


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

    /**
     * 模糊查询用户信息
     * @param empInformationResult
     * @return
     */
    List<EmpInformationResult> queryUserByEmployeeName(EmpInformationResult empInformationResult);

    /**
     * 删除用户角色
     * @param employeeID
     */
    void deleteUserRole(String employeeID);

    /**
     * 插入用户角色
     * @param loginInformation
     */
    void insertUserRole(LoginInformation loginInformation);

    /**
     * 更新员工
     * @param loginInformation
     */
    void updateUserRole(LoginInformation loginInformation);
}
