package com.isoftstone.pmit.project.hrbp.service;



import com.isoftstone.pmit.project.hrbp.entity.SysRole;
import com.isoftstone.pmit.project.hrbp.entity.SysStaffbaseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LoginService {

     List<SysStaffbaseinfo> getUserInfos(Map<String, Object> paramMap);


    public List<SysRole> getUserRoleInfos(Map<String, Object> paramMap);


}
