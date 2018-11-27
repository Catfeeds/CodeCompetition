package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.BaseStaffInfo;
import com.isoftstone.pmit.system.staff.entity.StaffBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author lf
 */
@Mapper
@Repository
public interface BaseStaffInfoMapper {

    BaseStaffInfo findUserByEmployeeId(String username);
}
