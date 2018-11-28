package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.OneStaffAllInfo;
import com.isoftstone.pmit.project.hrbp.mapper.BaseStaffInfoMapper;
import com.isoftstone.pmit.project.hrbp.service.BaseStaffInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseStaffInfoServiceImpl implements BaseStaffInfoService {

    @Autowired
    private BaseStaffInfoMapper baseStaffInfoMapper;

    @Override
    public OneStaffAllInfo getBaseStaffInfoByID(String employeeID) {
        if (employeeID ==null){
            throw new NullPointerException();
        }
        OneStaffAllInfo staffInfo = baseStaffInfoMapper.getBaseStaffInfoByID(employeeID);
        if (staffInfo == null){
            return null;
        }
        return staffInfo;
    }

    @Override
    public OneStaffAllInfo updateBaseStaffInfoByID(String employeeID) {
        return null;
    }

    @Override
    public int insertBaseStaffInfo(OneStaffAllInfo baseStaffInfo) {
        return 0;
    }

    @Override
    public int deleteBaseStaffInfo(String employeeID) {
        return 0;
    }
}
