package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.mapper.ExaminationAffairMapper;
import com.isoftstone.pmit.project.hrbp.service.IExaminationAffairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        return examinationAffairMapper.findAllAffair(examinationAffair);
    }

    @Override
    public void deleteAffair(String affairID) {
        examinationAffairMapper.deleteAffair(affairID);
    }

    @Override
    public void updateAffair(ExaminationAffair examinationAffair) {
        examinationAffairMapper.updateAffair(examinationAffair);
    }

    @Override
    public Integer insertAffair(ExaminationAffair examinationAffair) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
        examinationAffair.setAffairID("lss" + sdf.format(new Date()));
        return examinationAffairMapper.insertAffair(examinationAffair);
    }

    @Override
    public ExaminationAffair findExamAffairByAffairId(String affairID) {
        return examinationAffairMapper.findExamAffairByAffairId(affairID);
    }

    @Override
    public List<ExaminationAffair> findExamAffairSystem() {
        return examinationAffairMapper.findExamAffairSystem();
    }

    @Override
    public List<ExaminationAffair> findExamAffairSeries() {
        return examinationAffairMapper.findExamAffairSeries();
    }

}
