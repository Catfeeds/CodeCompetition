package com.isoftstone.pmit.project.hrbp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.isoftstone.pmit.system.menu.entity.Menu;

@Mapper
public interface MenuManageMapper {
	
	List<Menu> getMenuListByRoleId(String RoleId);
	
}
