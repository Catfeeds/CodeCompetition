package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface SysRoleMapper {


    /**
     *  更新角色信息
     * @param sysRole
     */
    void updateSystemRole(SysRole sysRole);

    /**
     * 根据用户id找角色
     * @param employeeID
     * @return
     */
    SysRole getRolesByEmployeeID(String employeeID);

    /**
     * 查询所有角色
     * @return
     */
    List<SysRole> queryAllRoles();

    /**
     * 插入角色
     */
    int insertSystemRole(SysRole role);

    /**
     * 根据roleId获取角色
     * @param roleId
     */
    SysRole getRoleByRoleId(Integer roleId);
}
