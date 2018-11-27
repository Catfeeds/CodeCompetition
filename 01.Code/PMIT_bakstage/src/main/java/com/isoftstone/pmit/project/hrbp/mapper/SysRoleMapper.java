package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface SysRoleMapper {

    List<SysRole> selectAllSystemRole();

    void insertSystemRole(SysRole sysRole);

    void deleteSystemRole(String str);

    void updateSystemRole(SysRole sysRole);

    List<SysRole> getAllRoles(Map<String, String> sysRoleParam);

    List<SysRole> getRolesByEmployeeID(String employeeID);
}
