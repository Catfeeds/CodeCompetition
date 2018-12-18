package com.isoftstone.pmit.project.hrbp.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.pmit.project.hrbp.entity.AllPersonalResult;
import com.isoftstone.pmit.project.hrbp.entity.BaseStaffInfo;
import com.isoftstone.pmit.project.hrbp.entity.PersonInfoAndPageInfo;
import com.isoftstone.pmit.project.hrbp.entity.PersonalInformation;

import java.util.List;
import java.util.Map;

public interface BaseStaffInfoService {


    /*
     * 通过ID获取一个员工的详细信息
     * */
    PersonalInformation getBaseStaffInfoByID(Map<String,String> paraterMap);

    /*
     * 修改员工的详细信息
     * */
    void updatePersonalInformation(PersonalInformation personalInformation);
    /*
    * 删除一个员工信息
    * */
    void deletePersonalInformationById(Map<String,String> paraterMap);

    /*
    * 删除多个员工的信息
    * */
    void deletePersonalInformationByList(List<String> employeeIdList);

    /*
    * 增加一个员工的所有信息
    * */
    void   insertPersonalInformation(PersonalInformation personalInformation);

    /*
    * 模糊查询全员查询
    * */
    PageInfo<BaseStaffInfo> getPersonalInfoByFuzzyQuery(PersonInfoAndPageInfo paramter);

}
