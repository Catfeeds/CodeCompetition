package com.isoftstone.pmit.project.hrbp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;

@Mapper
public interface MenuManageMapper {
	
	List<MenuInfo> getMenuListByRoleId(String RoleId);
	
}
