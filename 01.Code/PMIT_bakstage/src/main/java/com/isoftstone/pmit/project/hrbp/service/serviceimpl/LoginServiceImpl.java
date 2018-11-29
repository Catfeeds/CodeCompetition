package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.entity.SysStaffbaseinfo;
import com.isoftstone.pmit.project.hrbp.mapper.LoginMapper;
import com.isoftstone.pmit.project.hrbp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public List<SysStaffbaseinfo> getUserInfos(Map<String, Object> paramMap) {
        paramMap.put("staffState", 1);
        return loginMapper.getUserInfos(paramMap);
    }

    @Override
    public List<SysRole> getUserRoleInfos(Map<String, Object> paramMap) {
        paramMap.put("state", 1);
        return loginMapper.getUserRoleInfos(paramMap);
    }


}
