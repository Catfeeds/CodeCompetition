package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.EmpInformationResult;
import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import com.isoftstone.pmit.project.hrbp.mapper.LoginMapper;
import com.isoftstone.pmit.project.hrbp.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lf
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginInformation getEmployee(String employeeID) {
        return loginMapper.findStaffByEmployeeId(employeeID) ;
    }

    @Override
    public EmpInformationResult findEmpInformationByEmpId(String employeeID) {
        return loginMapper.findEmpInformationByEmpId(employeeID);
    }

    @Override
    public List<EmpInformationResult> findEmpInformation() {
        return loginMapper.findEmpInformation();
    }
}
