package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.common.util.ListUtils;
import com.isoftstone.pmit.project.hrbp.entity.PersonalAffairdimension;
import com.isoftstone.pmit.project.hrbp.mapper.PersonalCenterMapper;
import com.isoftstone.pmit.project.hrbp.service.IPersonalCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonalCenterService implements IPersonalCenterService {

    @Autowired
    private PersonalCenterMapper personalCenterMapper;

    @Override
    public List<Map<String, Object>> getPendingSolved(Map<String, Object> paramMap) {
        List<Map<String, Object>> returnMapList = new ArrayList<Map<String, Object>>();
        //获取所有该评价人的所有事务项
        List<Map<String, Object>> pendingSolved = personalCenterMapper.getPendingSolved(paramMap);
        if (!ListUtils.isEmpty(pendingSolved)) {
            for (Map<String, Object> pendingSolvedMap : pendingSolved) {
                //获取当前员工所对应的角色/bu/级别对应的规则设置中的事务项
                List<Map<String, Object>> staffAffair = personalCenterMapper.getStaffAffair(pendingSolvedMap);
                if (!ListUtils.isEmpty(staffAffair)) {
                    for (Map<String, Object> staffAffairMap : staffAffair) {
                        String itemId = (String) staffAffairMap.get("itemId");
                        String affairId = (String) pendingSolvedMap.get("affairId");
                        if (itemId.equals(affairId)) {
                            returnMapList.add(pendingSolvedMap);
                        }
                    }
                }
            }
        }
        return returnMapList;
    }

    @Override
    public List<Map<String, Object>> getDimensionByAffair(Map<String, Object> paramMap) {
        return personalCenterMapper.getDimensionByAffair(paramMap);
    }

    @Override
    public List<Map<String, Object>> queryHistorySolved(Map<String, Object> paramMap) {
        return personalCenterMapper.queryHistorySolved(paramMap);
    }

    @Override
    public void saveEvaluation(List<PersonalAffairdimension> personalAffairdimensionList) {
        for (PersonalAffairdimension personalAffairdimension : personalAffairdimensionList) {
            personalCenterMapper.saveEvaluation(personalAffairdimension);
        }

    }

    @Override
    public Map<String, Object> countPendingSolved(Map<String, Object> paramMap) {
        List<Map<String, Object>> pendingSolved = getPendingSolved(paramMap);
        int size = pendingSolved.size();
        Map<String, Object> countMap = new HashMap<>();
        countMap.put("count",size);
        return countMap;
    }
}
