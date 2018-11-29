package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.PersonalInformation;

public interface BaseStaffInfoService {


    /*
     * 通过ID获取一个员工的详细信息
     * */
    PersonalInformation getBaseStaffInfoByID(String employeeID);

    /*
     * 修改员工的详细信息
     * */
    void updatePersonalInformation(PersonalInformation personalInformation);
}
