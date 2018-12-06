package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.MainstayInfo;
import com.isoftstone.pmit.project.hrbp.entity.TupleData;

import java.util.List;
import java.util.Map;

public interface IMainstayReportService {
    public List<TupleData> queryMainstayProportionChart(Map<String,Object> params);

    public List<TupleData> queryCostCenterMainstay(Map<String,Object> params);

    public List<TupleData> queryMainstayByPost(Map<String,Object> params);

    public List<TupleData> queryMainstayTraining(Map<String,Object> params);

    public List<TupleData> queryMainstayAge(Map<String,Object> params);

    public MainstayInfo queryMainstayTable(Map<String,Object> params);


}
