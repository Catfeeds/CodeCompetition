package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.project.hrbp.mapper.BaseStaffInfoMapper;
import com.isoftstone.pmit.project.hrbp.service.BaseStaffInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BaseStaffInfoServiceImpl implements BaseStaffInfoService {

    private Logger log = LoggerFactory.getLogger(BaseStaffInfoServiceImpl.class);
    @Autowired
    private BaseStaffInfoMapper baseStaffInfoMapper;

    @Override
    public PersonalInformation getBaseStaffInfoByID(String employeeID) {
        if (employeeID ==null){
            throw new NullPointerException();
        }
        BaseStaffInfo baseStaffInfo = baseStaffInfoMapper.getBaseStaffInfoById(employeeID);
        log.info("base"+baseStaffInfo);
        List<CompanyQualification> qualification = baseStaffInfoMapper.getCompanyQualificationById(employeeID);
        log.info("qua"+qualification);
        FamilyInformation familyInfomation = baseStaffInfoMapper.getFamilyInfomationById(employeeID);
        log.info("family"+familyInfomation);
        PersonalStyle personalStyle = baseStaffInfoMapper.getPersonalStyleById(employeeID);
        log.info("person"+baseStaffInfo);
        List<TechnicalInformation> techicalInforation = baseStaffInfoMapper.getTechicalInforationById(employeeID);
        log.info("techical"+techicalInforation);
        if (baseStaffInfo==null){
            throw new NullPointerException();
        }
        PersonalInformation staffInfo =new PersonalInformation();
        staffInfo.setBaseStaffInfo(baseStaffInfo);
        staffInfo.setCompanyQualifications(qualification);
        staffInfo.setFamilyInformations(familyInfomation);
        staffInfo.setPersonalStyle(personalStyle);
        staffInfo.setTechnicalInformation(techicalInforation);
        return staffInfo;
    }

    @Override
    @Transactional
    public void updatePersonalInformation(PersonalInformation personalInformation) {
        BaseStaffInfo baseStaffInfo = personalInformation.getBaseStaffInfo();
        if (baseStaffInfo == null){
            throw new NullPointerException();
        }
        baseStaffInfoMapper.updateBaseStaffInfo(baseStaffInfo);
        baseStaffInfoMapper.updateFamilyInformation(personalInformation.getFamilyInformations());
        baseStaffInfoMapper.updatePersonalStyle(personalInformation.getPersonalStyle());
        baseStaffInfoMapper.updateTechnicalInformation(personalInformation.getTechnicalInformation());

    }


}
