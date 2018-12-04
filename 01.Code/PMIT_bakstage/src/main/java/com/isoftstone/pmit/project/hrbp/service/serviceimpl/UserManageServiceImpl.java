package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.EmpInformationResult;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import com.isoftstone.pmit.project.hrbp.mapper.LoginMapper;
import com.isoftstone.pmit.project.hrbp.mapper.UserManageMapper;
import com.isoftstone.pmit.project.hrbp.service.IUserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lf
 */
@Service
public class UserManageServiceImpl implements IUserManageService {

    @Autowired
    private UserManageMapper userManageMapper;

    @Autowired
    private LoginMapper loginMapper;

    /**
     * 获取登录信息坐标示
     *
     * @param employeeID
     * @return
     */
    @Override
    public LoginInformation getEmployee(String employeeID) {
        return loginMapper.findStaffByEmployeeId(employeeID);
    }

    /**
     * 获取登录用户信息
     *
     * @param employeeID
     * @return
     */
    @Override
    public EmpInformationResult findEmpInformationByEmpId(String employeeID) {
        return userManageMapper.findEmpInformationByEmpId(employeeID);
    }

    /**
     * 获取所有登录用户信息
     *
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfo<EmpInformationResult> findEmpInformation(com.isoftstone.pmit.project.hrbp.entity.PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getCurrPage(), pageInfo.getPageSize());
        String sortColumn = pageInfo.getSortColumn();
        String sortType = pageInfo.getSortType();
        if (null != sortColumn && sortColumn!="" &&sortType!="" && null != sortType) {
            PageHelper.orderBy(sortColumn + " " + sortType);
        }
        List<EmpInformationResult> empInformationResults = userManageMapper.findEmpInformations();
        PageInfo<EmpInformationResult> empInformationResultPageInfo = new PageInfo<>(empInformationResults);
        return empInformationResultPageInfo;
    }

    /**
     * 模糊查询用户信息
     *
     * @param parameter
     * @return
     */
    @Override
    public List<EmpInformationResult> queryUserByKeyword(String parameter) {
        return userManageMapper.queryUserByKeyword(parameter);
    }

    @Override
    public void deleteUserRole(String employeeID) {
        userManageMapper.deleteUserRole(employeeID);
    }

    @Override
    public void insertUserRole(LoginInformation loginInformation) {
        userManageMapper.insertUserRole(loginInformation);
    }

    @Override
    public void updateUserRole(LoginInformation loginInformation) {
        userManageMapper.updateUserRole(loginInformation);
    }

    @Override
    public List<EmpInformationResult> findEmpInformation() {
        return userManageMapper.findEmpInformations();
    }

}
