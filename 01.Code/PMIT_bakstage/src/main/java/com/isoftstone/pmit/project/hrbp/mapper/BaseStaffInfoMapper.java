package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.BaseStaffInfo;
import com.isoftstone.pmit.project.hrbp.entity.OneStaffAllInfo;
import com.isoftstone.pmit.system.staff.entity.StaffBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author lf
 */
@Mapper
@Repository
public interface BaseStaffInfoMapper {

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
    int insertBaseStaffInfo(OneStaffAllInfo oneStaffAllInfo);

    /*
     * 删除一个员工信息
     * */
    int deleteBaseStaffInfo(String employeeID);
}
