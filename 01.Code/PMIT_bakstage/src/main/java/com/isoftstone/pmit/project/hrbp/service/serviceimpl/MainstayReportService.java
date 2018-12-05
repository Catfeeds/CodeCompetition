package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.common.util.StringUtilsMethod;
import com.isoftstone.pmit.project.hrbp.entity.BaseTreeNode;
import com.isoftstone.pmit.project.hrbp.entity.MainstayInfo;
import com.isoftstone.pmit.project.hrbp.entity.TupleData;
import com.isoftstone.pmit.project.hrbp.mapper.MainstayReportMapper;
import com.isoftstone.pmit.project.hrbp.service.IMainstayReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MainstayReportService implements IMainstayReportService {

    @Autowired
    private MainstayReportMapper mapper;

    @Override
    public BaseTreeNode queryMainstayLevel(Map<String, Object> params) {
        List<Map<String, String>> levelInfo = mapper.queryMainstayLevel();
        BaseTreeNode root = new BaseTreeNode();
        root.setNodeName("root");
        root.setChildList(new ArrayList<BaseTreeNode>());
        buildLevelTreeInfo(root, levelInfo);
        return root;
    }

    private void buildLevelTreeInfo(BaseTreeNode root, List<Map<String, String>> levelInfo) {
        if (levelInfo != null) {
            Map<String, BaseTreeNode> tempMap = new HashMap<String, BaseTreeNode>();
            for (Map<String, String> temp : levelInfo) {
                BaseTreeNode parentNode = root;

                String bu = temp.get("bu");
                parentNode = getBaseTreeNode(tempMap, parentNode, bu);

                String workPlaceArea = temp.get("workPlaceArea");
                parentNode = getBaseTreeNode(tempMap, parentNode, workPlaceArea);

                String pdu = temp.get("pdu");
                parentNode = getBaseTreeNode(tempMap, parentNode, pdu);
            }
        }
    }

    private BaseTreeNode getBaseTreeNode(Map<String, BaseTreeNode> tempMap, BaseTreeNode parentNode, String currentNode) {
        if (tempMap.containsKey(currentNode)) {
            parentNode = tempMap.get(currentNode);
        } else {
            BaseTreeNode child = new BaseTreeNode();
            child.setNodeName(currentNode);
            child.setChildList(new ArrayList<BaseTreeNode>());
            parentNode.getChildList().add(child);
            tempMap.put(currentNode, child);
            parentNode = child;
        }
        return parentNode;
    }

    @Override
    public List<TupleData> queryMainstayProportionChart(Map<String, Object> params) {
        Map<String, Object> datas = mapper.queryMainstayProportionChart(params);
        List<TupleData> proportionChart = new ArrayList<TupleData>();
        buildMainstayNum(datas, proportionChart);
        return proportionChart;
    }

    private void buildMainstayNum(Map<String, Object> datas, List<TupleData> proportionChart) {
        if (datas != null) {
            for (Map.Entry<String, Object> temp : datas.entrySet()) {
                String name = temp.getKey();
                String value = String.valueOf(temp.getValue());
                if (!StringUtilsMethod.isEmpty(name) && !StringUtilsMethod.isEmpty(value)) {
                    TupleData tupleData = new TupleData(name, value);
                    proportionChart.add(tupleData);
                }
            }
        }
    }

    @Override
    public List<TupleData> queryCostCenterMainstay(Map<String, Object> params) {
        List<Map<String, Object>> datas = mapper.queryCostCenterMainstay(params);
        List<TupleData> costCenterMainstayNum = new ArrayList<TupleData>();
        buildMainstayNum(datas, costCenterMainstayNum, "其他");
        return costCenterMainstayNum;
    }

    @Override
    public List<TupleData> queryMainstayByPost(Map<String, Object> params) {
        List<Map<String, Object>> datas = mapper.queryMainstayByPost(params);
        List<TupleData> postMainstayNum = new ArrayList<TupleData>();
        buildMainstayNum(datas, postMainstayNum, "Other");
        return postMainstayNum;
    }

    private void buildMainstayNum(List<Map<String, Object>> datas, List<TupleData> BUMainstayNumber, String defName) {
        if (datas != null) {
            for (Map<String, Object> temp : datas) {
                String name = String.valueOf(temp.get("name"));
                if (name.equalsIgnoreCase("null") || name.isEmpty()) {
                    name = defName;
                }
                String value = String.valueOf(temp.get("number"));
                if (!StringUtilsMethod.isEmpty(name) && !StringUtilsMethod.isEmpty(value)) {
                    TupleData tupleData = new TupleData(name, value);
                    BUMainstayNumber.add(tupleData);
                }
            }
        }
    }

    @Override
    public List<TupleData> queryMainstayTraining(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<TupleData> queryMainstayAge(Map<String, Object> params) {
        Calendar age30 = Calendar.getInstance();
        age30.roll(Calendar.YEAR, -30);
        params.put("age30", age30.getTime());

        Calendar age40 = Calendar.getInstance();
        age40.roll(Calendar.YEAR, -40);
        params.put("age40", age40.getTime());

        Map<String, Object> datas = mapper.queryMainstayAge(params);
        List<TupleData> costCenterMainstayNum = new ArrayList<TupleData>();
        buildMainstayNum(datas, costCenterMainstayNum);
        return costCenterMainstayNum;
    }

    @Override
    public MainstayInfo queryMainstayTable(Map<String, Object> params) {
        return null;
    }
}
