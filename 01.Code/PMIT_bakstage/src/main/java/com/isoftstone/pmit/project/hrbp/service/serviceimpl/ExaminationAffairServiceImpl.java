package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.mapper.ExaminationAffairMapper;
import com.isoftstone.pmit.project.hrbp.service.IExaminationAffairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lf
 */
@Service
public class ExaminationAffairServiceImpl implements IExaminationAffairService {

    @Autowired
    private ExaminationAffairMapper examinationAffairMapper;

    @Override
    public List<ExaminationAffair> findAllAffair(ExaminationAffair examinationAffair) {
        return  examinationAffairMapper.findAllAffair(examinationAffair);
    }

    @Override
    public void deleteAffair(Integer affairID) {
        examinationAffairMapper.deleteAffair(affairID);
    }

    @Override
    public void updateAffair(ExaminationAffair examinationAffair) {
        examinationAffairMapper.updateAffair(examinationAffair);
    }

    @Override
    public ExaminationAffair findExamAffairByAffairId(Integer affairID) {
        return examinationAffairMapper.findExamAffairByAffairId(affairID);
    }

    @Override
    public void insertAffair(ExaminationAffair examinationAffair) {
        examinationAffairMapper.insertAffair(examinationAffair);
    }

    @Override
    public List<ExaminationAffair> findExamAffairSystem() {
        return  examinationAffairMapper.findExamAffairSystem();
    }
}
