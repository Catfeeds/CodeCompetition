package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.EmpInformationResult;
import com.isoftstone.pmit.project.hrbp.entity.ExaminationAffair;
import com.isoftstone.pmit.project.hrbp.mapper.ExaminationAffairMapper;
import com.isoftstone.pmit.project.hrbp.mapper.UserManageMapper;
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
    @Autowired
    private UserManageMapper userManageMapper;

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
        EmpInformationResult empInformationByEmpId = userManageMapper.findEmpInformationByEmpId(examinationAffair.getEmployeeID());
        examinationAffair.setUpdateBy(empInformationByEmpId.getEmployeeName());
        examinationAffairMapper.updateAffair(examinationAffair);
    }

    @Override
    public void insertAffair(ExaminationAffair examinationAffair) {
        EmpInformationResult empInformationByEmpId = userManageMapper.findEmpInformationByEmpId(examinationAffair.getEmployeeID());
        examinationAffair.setCreateBy(empInformationByEmpId.getEmployeeName());
        examinationAffairMapper.insertAffair(examinationAffair);
    }

    @Override
    public ExaminationAffair findExamAffairByAffairId(Integer affairID) {
        return examinationAffairMapper.findExamAffairByAffairId(affairID);
    }

    @Override
    public List<ExaminationAffair> findExamAffairSystem() {
        return  examinationAffairMapper.findExamAffairSystem();
    }

    @Override
    public List<ExaminationAffair> findExamAffairSeries() {
        return  examinationAffairMapper.findExamAffairSeries();
    }

    @Override
    public List<ExaminationAffair> findAllAffairName() {
        return examinationAffairMapper.findAllAffairName();
    }
}
