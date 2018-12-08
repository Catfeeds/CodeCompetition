package com.isoftstone.pmit.common.model;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationRulesInfo;
import com.isoftstone.pmit.project.hrbp.entity.SingleRuleInfo;
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
}
