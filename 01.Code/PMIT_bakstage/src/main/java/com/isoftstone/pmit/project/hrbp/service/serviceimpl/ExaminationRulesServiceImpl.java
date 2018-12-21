package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.common.constant.CommonConst;
import com.isoftstone.pmit.common.model.ExaminationRulesParam;
import com.isoftstone.pmit.common.util.ListUtils;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationRulesInfo;
import com.isoftstone.pmit.project.hrbp.entity.SingleRuleInfo;
import com.isoftstone.pmit.project.hrbp.entity.SpecialRolesInfo;
import com.isoftstone.pmit.project.hrbp.mapper.ExaminationRulesMapper;
import com.isoftstone.pmit.project.hrbp.service.IExaminationRulesService;
import com.isoftstone.pmit.project.hrbp.util.ParamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ExaminationRulesServiceImpl implements IExaminationRulesService {

    @Autowired
    private ExaminationRulesMapper examinationRulesMapper;

    @Override
    public List<SpecialRolesInfo> querySpecialRolesInfo(ExaminationRulesParam params) {
        Map<String, Object> queryParams = ParamUtils.getExaminationRulesParams(params);
        return examinationRulesMapper.querySpecialRolesInfo(queryParams);
    }

    @Override
    public boolean saveSpecialRole(SpecialRolesInfo oneRole) {
        int rst;
        if (CommonConst.INVALID_VALUE == oneRole.getRoleId()) {
            rst = examinationRulesMapper.addSpecialRole(oneRole);
        } else {
            rst = examinationRulesMapper.updateSpecialRole(oneRole);
        }
        return CommonConst.SQL_EXECUTE_FAILED != rst;
    }

    @Override
    public boolean deleteSpecialRole(int roleId) {
        return CommonConst.SQL_EXECUTE_FAILED != examinationRulesMapper.deleteSpecialRole(roleId);
    }

    @Override
    public List<ExaminationRulesInfo> queryExaminationRulesInfo(ExaminationRulesParam params) {
        Map<String, Object> queryParams = ParamUtils.getExaminationRulesParams(params);
        return examinationRulesMapper.queryExaminationRulesInfo(queryParams);
    }

    @Override
    public boolean saveExaminationRulesInfo(ExaminationRulesParam params) {
        List<SingleRuleInfo> singleRule = params.getSingleRuleInfo();
        if (ListUtils.isEmpty(singleRule)) {
            return false;
        }

        // 不论新增还是更新，均先删除掉原来的单项规则后再追加
        int sqlRst;
        ExaminationRulesInfo oneRule = params.getOneRule();
        oneRule.setSystem(params.getSystem()); // 后续让前台下发到里头
        if (CommonConst.SQL_EXECUTE_FAILED == examinationRulesMapper.deleteSingleRuleInfo(singleRule.get(0).getRuleId())) {
            sqlRst = examinationRulesMapper.addExaminationRulesInfo(oneRule);
        } else {
            sqlRst = examinationRulesMapper.updateExaminationRulesInfo(oneRule);
        }

        if (CommonConst.SQL_EXECUTE_FAILED  == sqlRst) {
            return false;
        }

        for (SingleRuleInfo oneSingleRule : singleRule) {
            oneSingleRule.setRuleId(oneRule.getRuleId());
        }

        return CommonConst.SQL_EXECUTE_FAILED != examinationRulesMapper.addSigleRuleInfo(singleRule);
    }

    @Override
    public boolean deleteExaminationRulesInfo(int ruleId) {
        return CommonConst.SQL_EXECUTE_FAILED != examinationRulesMapper.deleteExaminationRulesInfo(ruleId);
    }

    @Override
    public List<SingleRuleInfo> querySigleRuleInfo(int ruleId) {
        return examinationRulesMapper.querySigleRuleInfo(ruleId);
    }

    @Override
    public List<ExaminationRulesInfo> queryAllRole() {
        return examinationRulesMapper.queryAllRole();
    }
}
