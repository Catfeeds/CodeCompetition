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

import java.util.List;

/**
 * @author lf
 */
@Service
public class UserManageServiceImpl implements IUserManageService {

    @Autowired
    private UserManageMapper userManageMapper;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginInformation getEmployee(String employeeID) {
        return loginMapper.findStaffByEmployeeId(employeeID) ;
    }

    @Override
    public EmpInformationResult findEmpInformationByEmpId(String employeeID) {
        return userManageMapper.findEmpInformationByEmpId(employeeID);
    }

    @Override
    public PageInfo<EmpInformationResult> findEmpInformation(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<EmpInformationResult> empInformationResults = userManageMapper.findEmpInformation();
        PageInfo<EmpInformationResult> empInformationResultPageInfo = new PageInfo<>(empInformationResults);
        return empInformationResultPageInfo;
    }

}
