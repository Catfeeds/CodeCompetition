package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.common.model.ExaminationRulesParam;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationRulesInfo;
import com.isoftstone.pmit.project.hrbp.entity.SingleRuleInfo;
import com.isoftstone.pmit.project.hrbp.entity.SpecialRolesInfo;

import java.util.List;

public interface IExaminationRulesService {
    /**
     * 查询所有关键角色
     * @param params 产品线、角色、体系
     * @return 所有关键角色
     */
    List<SpecialRolesInfo> querySpecialRolesInfo(ExaminationRulesParam params);

    /**
     * 存储关键角色 (增加/修改 合一)
     * @param oneRole 指定关键角色
     * @return 操作是否成功
     */
    boolean saveSpecialRole(SpecialRolesInfo oneRole);

    /**
     * 删除关键角色
     * @param roleId 角色Id，主键
     * @return 操作是否成功
     */
    boolean deleteSpecialRole(int roleId);


    /**
     * 查询所有考核规则
     * @param params 产品线、角色、体系
     * @return 考核规则列表
     */
    List<ExaminationRulesInfo> queryExaminationRulesInfo(ExaminationRulesParam params);

    /**
     * 存储考核规则 (增加/修改 合一)
     * @param examinationRulesParam 一个规则信息、单项规则细节
     * @return 操作是否成功
     */
    boolean saveExaminationRulesInfo(ExaminationRulesParam examinationRulesParam);

    /**
     * 删除考核规则
     * @param ruleId 规则Id，主键
     * @return 操作是否成功
     */
    boolean deleteExaminationRulesInfo(int ruleId);

    /**
     * 查询单项规则
     * @param ruleId  规则Id，主键
     * @return 所有单项规则细节
     */
    List<SingleRuleInfo> querySigleRuleInfo(int ruleId);
}
