package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationDimension;
import com.isoftstone.pmit.project.hrbp.mapper.DimensionMapper;
import com.isoftstone.pmit.project.hrbp.mapper.ExaminationAffairMapper;
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

    @Autowired
    private ExaminationAffairMapper examinationAffairMapper;

    @Override
    public void insertDimension(ExaminationAffair examinationAffair, List<ExaminationDimension> examinationDimensionList) {
        //查询该事物下所有的维度
        List<ExaminationDimension> existDimension = dimensionMapper.queryDimension(examinationAffair.getAffairID());
        for (ExaminationDimension examinationDimension : existDimension) {
            //删除维度表下所有维度
            dimensionMapper.deleteDimension(examinationDimension);
        }
        //删除该事务维度表中所有维度
        dimensionMapper.deleteAffairAndDimension(examinationAffair);
        List<Integer> integerList = new ArrayList<>();
        for (ExaminationDimension examinationDimension : examinationDimensionList) {
            dimensionMapper.insertDimension(examinationDimension);
            integerList.add(examinationDimension.getDimensionID());
        }
        String returnAffairID = examinationAffair.getAffairID();
        for (Integer dimensionId : integerList) {
            dimensionMapper.insertAffairDimension(returnAffairID,dimensionId);
        }
        examinationAffairMapper.updateAffairTime(examinationAffair);
    }

    @Override
    public List<ExaminationDimension> queryDimension(String affairID) {
        return dimensionMapper.queryDimension(affairID);
    }
}
