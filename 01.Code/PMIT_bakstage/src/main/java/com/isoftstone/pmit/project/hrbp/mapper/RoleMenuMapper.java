package com.isoftstone.pmit.project.hrbp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lf
 */
@Mapper
@Repository
public interface RoleMenuMapper {

    void deleteMenuByRid(@Param("roleId")Integer roleId);

    int addMenu(@Param("roleId")Integer roleId, @Param("menuId")Integer menuId);

    void deleteRole(@Param("roleId") Integer roleId);
}
