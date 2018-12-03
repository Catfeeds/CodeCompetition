package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationDimension;
import com.isoftstone.pmit.project.hrbp.mapper.DimensionMapper;
import com.isoftstone.pmit.project.hrbp.service.IDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lf
 */
@Service
public class DimensionServiceImpl implements IDimensionService {

    @Autowired
    private DimensionMapper dimensionMapper;

    @Override
    public void insertDimension(ExaminationAffair examinationAffair, List<ExaminationDimension> examinationDimensionList) {
        List<Integer> integerlinkedList = new ArrayList<>();
        for (ExaminationDimension examinationDimension : examinationDimensionList) {
            dimensionMapper.insertDimension(examinationDimension);
            integerlinkedList.add(examinationDimension.getDimensionID());
        }
        Integer affairID = examinationAffair.getAffairID();
        for (Integer dimensionId : integerlinkedList) {
            dimensionMapper.insertAffairDimension(affairID,dimensionId);
        }
    }

    @Override
    public List<ExaminationDimension> queryDimension(Integer affairID) {
        return dimensionMapper.queryDimension(affairID);
    }
}
