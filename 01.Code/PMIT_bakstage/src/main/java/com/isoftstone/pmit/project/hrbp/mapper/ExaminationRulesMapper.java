package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationRulesInfo;
import com.isoftstone.pmit.project.hrbp.entity.SingleRuleInfo;
import com.isoftstone.pmit.project.hrbp.entity.SpecialRolesInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ExaminationRulesMapper {
    /**
     * 查询所有关键角色
     * @param params 产品线、角色、体系
     * @return 所有关键角色
     */
    List<SpecialRolesInfo> querySpecialRolesInfo(Map<String, Object> params);

    /**
     * 增加关键角色
     * @param oneRole 指定关键角色
     * @return 新增结果，返回0表示增加失败
     */
    int addSpecialRole(SpecialRolesInfo oneRole);

    /**
     * 更新关键角色
     * @param oneRole 指定关键角色
     * @return 更新结果，返回0表示增加失败
     */
    int updateSpecialRole(SpecialRolesInfo oneRole);

    /**
     * 删除关键角色
     * @param roleId 角色Id，主键
     * @return 删除结果，返回0表示增加失败
     */
    int deleteSpecialRole(int roleId);

    /**
     * 查询所有考核规则
     * @param params 产品线、角色、体系
     * @return 考核规则列表
     */
    List<ExaminationRulesInfo> queryExaminationRulesInfo(Map<String, Object> params);

    /**
     * 新增考核规则
     * @param oneRule 指定单个考核规则
     * @return 新增结果，返回0表示增加失败
     */
    int addExaminationRulesInfo(ExaminationRulesInfo oneRule);

    /**
     * 更新考核规则
     * @param oneRule 指定单个考核规则
     * @return 更新结果，返回0表示增加失败
     */
    int updateExaminationRulesInfo(ExaminationRulesInfo oneRule);

    /**
     * 删除考核规则
     * @param ruleId 规则Id，主键
     * @return 删除结果，返回0表示增加失败
     */
    int deleteExaminationRulesInfo(int ruleId);

    /**
     * 查询单项规则
     * @param ruleId  规则Id，主键
     * @return 所有单项规则细节
     */
    List<SingleRuleInfo> querySigleRuleInfo(int ruleId);

    /**
     * 新增单项规则
     * @param singleRuleInfo 批量添加单项规则
     * @return 添加结果，返回0表示增加失败
     */
    int addSigleRuleInfo(List<SingleRuleInfo> singleRuleInfo);

    /**
     * 删除单项规则
     * @param ruleId 角色Id，主键
     * @return 删除结果，返回0表示增加失败
     */
    int deleteSingleRuleInfo(int ruleId);

    List<ExaminationRulesInfo> queryAllRole();
}
