package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.BaseStaffInfo;
import com.isoftstone.pmit.project.hrbp.entity.OneStaffAllInfo;

public interface BaseStaffInfoService {


    /*
     * 通过ID获取一个员工的详细信息
     * */
    OneStaffAllInfo getBaseStaffInfoByID(String employeeID);

    /*
     * 通过ID修改个人员工的详细信息
     * */
    OneStaffAllInfo updateBaseStaffInfoByID(String employeeID);

    /*
     * 增加新员工信息
     * */
    int insertBaseStaffInfo(OneStaffAllInfo baseStaffInfo);

    /*
     * 删除一个员工信息
     * */
    int deleteBaseStaffInfo(String employeeID);
}
