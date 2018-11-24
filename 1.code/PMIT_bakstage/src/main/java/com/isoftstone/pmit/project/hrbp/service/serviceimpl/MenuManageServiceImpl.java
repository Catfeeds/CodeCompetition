package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isoftstone.pmit.project.hrbp.mapper.MenuManageMapper;
import com.isoftstone.pmit.project.hrbp.service.IMenuManageService;
import com.isoftstone.pmit.system.menu.entity.Menu;

@Service
public class MenuManageServiceImpl implements IMenuManageService {
	
	@Autowired
	private MenuManageMapper menuManageMapper;
	
	@Override
	public List<Menu> getMenuListByRoleId(String roleId) {
		return menuManageMapper.getMenuListByRoleId(roleId);
	}

}
