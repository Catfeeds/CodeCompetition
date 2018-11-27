package com.isoftstone.pmit.project.hrbp.mapper;

import java.util.List;

import com.isoftstone.pmit.system.menu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MenuManageMapper {

	List<MenuInfo> getMenuListByRoleId(String RoleId);

	List<MenuInfo> getMenusBySysRoleId(Integer roleId);
}
