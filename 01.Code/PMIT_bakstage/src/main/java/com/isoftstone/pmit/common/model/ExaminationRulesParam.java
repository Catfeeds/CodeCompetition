package com.isoftstone.pmit.common.model;

import com.isoftstone.pmit.common.util.JsonUtils;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationRulesInfo;
import com.isoftstone.pmit.project.hrbp.entity.SingleRuleInfo;
import com.isoftstone.pmit.project.hrbp.entity.SpecialRolesInfo;

import java.util.ArrayList;
import java.util.List;

public class ExaminationRulesParam extends CommonParam {
    // 所属体系
    private String system;

    // 所属角色
    private String roleName;

    // 考核规则信息
    private ExaminationRulesInfo oneRule;

    // 单项考核规则细节
    private List<SingleRuleInfo> singleRuleInfo;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public ExaminationRulesInfo getOneRule() {
        return oneRule;
    }

    public void setOneRule(ExaminationRulesInfo oneRule) {
        this.oneRule = oneRule;
    }

    public List<SingleRuleInfo> getSingleRuleInfo() {
        return singleRuleInfo;
    }

    public void setSingleRuleInfo(List<SingleRuleInfo> singleRuleInfo) {
        this.singleRuleInfo = singleRuleInfo;
    }

    public static void main(String[] args) {
        ExaminationRulesParam examinationRulesParam = new ExaminationRulesParam();
        examinationRulesParam.setOneRule(new ExaminationRulesInfo());
        examinationRulesParam.setSingleRuleInfo(new ArrayList<>());

        ExaminationRulesInfo examinationRulesInfo = examinationRulesParam.getOneRule();
        examinationRulesInfo.setRoleId(12);
        examinationRulesInfo.setRoleLevel("高级");
        examinationRulesInfo.setCreatorId("10008611");
        examinationRulesInfo.setCreatorName("张一路");

        List<SingleRuleInfo> singleRules = examinationRulesParam.getSingleRuleInfo();
        singleRules.add(new SingleRuleInfo());
        singleRules.add(new SingleRuleInfo());

        SingleRuleInfo one = singleRules.get(0);
        one.setRuleId(12);
        one.setItemId(1);
        one.setItemType(1);
        one.setItemName("信息安全培训");
        one.setCredit(2);
        one.setPass(80);

        SingleRuleInfo two = singleRules.get(1);
        two.setRuleId(12);
        two.setItemId(2);
        two.setItemType(1);
        two.setItemName("骨干MDE培训");
        two.setCredit(5);
        two.setPass(90);
        System.out.println(JsonUtils.toJSON(examinationRulesParam));
    }
}
