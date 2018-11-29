package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MenuManageMapper {
	
	List<MenuInfo> getMenuListByRoleId(Integer RoleId);
	
	void addMenuByRoleId(Map<String, Integer> map);

	List<MenuInfo> getMenus();
}
