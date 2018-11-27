package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.LoginInformation;
import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.entity.SysStaffbaseinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Mapper
@Repository
public interface LoginMapper {

    /**
     * 获取用户信息
     * @param paramMap
     * @return List<UserInfo>
     */
    List<SysStaffbaseinfo> getUserInfos(Map<String, Object> paramMap);

    /**
     * 获取用户角色信息
     * @param paramMap
     * @return List<UserRoleInfo>
     */
    List<SysRole> getUserRoleInfos(Map<String, Object> paramMap);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    LoginInformation findStaffByEmployeeId(String username);

}
