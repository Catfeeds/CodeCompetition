package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isoftstone.pmit.project.hrbp.entity.MenuInfo;
import com.isoftstone.pmit.project.hrbp.mapper.MenuManageMapper;
import com.isoftstone.pmit.project.hrbp.service.IMenuManageService;

@Service
public class MenuManageServiceImpl implements IMenuManageService {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MenuManageMapper menuManageMapper;
	
	@Override
	public List<MenuInfo> getMenuListByRoleId(Integer roleId) {
		return menuManageMapper.getMenuListByRoleId(roleId);
	}
	
	/**
	 * 
	 * 插入菜单与角色配置信息，添加事务注解  插入数据报错时回滚
	 * 
	 */
	@Transactional(rollbackOn=Exception.class)
	@Override
	public String saveMenuByRoleId(Integer roleId, List<MenuInfo> menuInfos) {
		
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			for(MenuInfo menuInfo : menuInfos) {
				map.put("roleId", roleId);
				map.put("menuId", menuInfo.getMenuId());
				menuManageMapper.addMenuByRoleId(map);
			}
		} catch (Exception e) {
			logger.error("saveMenuByRoleId error" + e.getMessage());
			return "saveMenuByRoleId error";
		}
		return "saveMenuByRoleId success";
	}

}
