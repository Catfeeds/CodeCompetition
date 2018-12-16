package com.isoftstone.pmit.project.hrbp.util;

import com.isoftstone.pmit.common.model.ExaminationRulesParam;
import com.isoftstone.pmit.common.model.PostOptionParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ParamUtils {
    public static Map<String, Object> handleServletParameter(HttpServletRequest request) {
        Map<String, String[]> parameter = request.getParameterMap();
        Map<String, Object> rParams = new HashMap<String, Object>(0);
        for (Map.Entry<String, String[]> m : parameter.entrySet()) {
            String key = m.getKey();
            String[] obj = parameter.get(key);
            rParams.put(key, (obj.length > 1) ? obj : obj[0]);
        }
        return rParams;
    }

    public static Map<String, Object> getExaminationRulesParams(ExaminationRulesParam params) {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("bu", params.getBu());
        queryParam.put("system", params.getSystem());
        queryParam.put("roleName", params.getRoleName());
        return queryParam;
    }

    public static Map<String, Object> getPostOptionParams(PostOptionParam params) {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("bu", params.getBu());
        queryParam.put("cu", params.getCu());
        queryParam.put("area", params.getArea());
        queryParam.put("employeeId", params.getEmployeeId());
        queryParam.put("authType", params.getAuthType());
        queryParam.put("postName", params.getPostName());
        return queryParam;
    }
}
