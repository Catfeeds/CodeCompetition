package com.isoftstone.pmit.system.exceloperation.controller;

import com.isoftstone.pmit.system.exceloperation.service.IPersonalInfoService;
import com.isoftstone.pmit.system.exceloperation.util.ExcelOperationUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/exceloperation")
@Api(value = "个人信息", tags = {"导入EXCEL文件的个人信息"})
public class PersonalInfoController {

    @Autowired
    private IPersonalInfoService personalInfoService;

    /**
     * 导入个人所有信息
     * @param personalInfoPath 需要导入excel文件路径
     * @return
     */
    @RequestMapping(value = "/importPersonalInfo")
    public String importPersonalInfo(@RequestParam(value = "personalInfoPath", required = true)
                                               String personalInfoPath){
        return personalInfoService.importPersonalInfo(personalInfoPath);
    }

    @RequestMapping(value = "/exportScore")
    public void exportScore(HttpServletRequest request, HttpServletResponse response){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        List<String> listTitle = new ArrayList<String>();
        listTitle.add("员工编号");
        listTitle.add("年龄");
        listTitle.add("入职日期");
        listTitle.add("成本中心");
        listTitle.add("BU");

        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("员工编号", "90001");
        map1.put("年龄", "23");
        map1.put("入职日期", "2018/3/15");
        list.add(map1);

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("员工编号", "90002");
        map2.put("年龄", "24");
        map2.put("入职日期", "2018/3/16");
        map2.put("成本中心", "HW西安2012实施部");
        list.add(map2);

        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("员工编号", "90003");
        map3.put("年龄", "25");
        map3.put("入职日期", "2018/3/17");
        map3.put("成本中心", "HW西安2012实施部");
        map3.put("BU", "2012");
        list.add(map3);

        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("员工编号", "90004");
        map4.put("年龄", "26");
        map4.put("入职日期", "2018/3/18");
        list.add(map4);

        String fileName = "骨干员工信息表";
        String tableName = "骨干考核系统员工信息表";

        ExcelOperationUtils.exportExcel(fileName, tableName, listTitle, list, request, response);
    }
}
