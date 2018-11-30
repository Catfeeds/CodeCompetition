package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.project.hrbp.mapper.BaseStaffInfoMapper;
import com.isoftstone.pmit.project.hrbp.service.BaseStaffInfoService;
import org.apache.ibatis.jdbc.Null;
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
        if (null == employeeID ){
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
        if (null == baseStaffInfo){
            log.info("该员工不存在");
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
        if (null == personalInformation.getBaseStaffInfo()
                || null == personalInformation.getCompanyQualifications()
                || null == personalInformation.getFamilyInformations()
                || null == personalInformation.getPersonalStyle()
                || null == personalInformation.getTechnicalInformation()){
            throw new NullPointerException();
        }
        List<TechnicalInformation> technicalInformation = personalInformation.getTechnicalInformation();
        baseStaffInfoMapper.updateBaseStaffInfo(personalInformation.getBaseStaffInfo());
        baseStaffInfoMapper.updateFamilyInformation(personalInformation.getFamilyInformations());
        baseStaffInfoMapper.updatePersonalStyle(personalInformation.getPersonalStyle());
        baseStaffInfoMapper.updateTechnicalInformation(technicalInformation);

    }

    @Override
    public void deletePersonalInformationById(String employeeID) {
        if (null == employeeID) {
            throw new NullPointerException();
        }
        baseStaffInfoMapper.deletePersonalInformationById(employeeID);
    }

    @Override
    public void deletePersonalInformationByList(List<String> employeeIdList) {
        if (null == employeeIdList){
            throw new NullPointerException();
        }
        baseStaffInfoMapper.deletePersonalInformationsByList(employeeIdList);

    }

    @Override
    @Transactional
    public void insertPersonalInformation(PersonalInformation  personalInformation) {

        if (null == personalInformation.getBaseStaffInfo()
                || null == personalInformation.getCompanyQualifications()
                || null == personalInformation.getFamilyInformations()
                || null == personalInformation.getPersonalStyle()
                || null == personalInformation.getTechnicalInformation()){
            throw new NullPointerException();
        }

        baseStaffInfoMapper.insertBaseStaffInfo(personalInformation.getBaseStaffInfo());
        baseStaffInfoMapper.insertCompanyQualification(personalInformation.getCompanyQualifications());
        baseStaffInfoMapper.insertFamilyInformation(personalInformation.getFamilyInformations());
        baseStaffInfoMapper.insertTechnicalInformation(personalInformation.getTechnicalInformation());
        baseStaffInfoMapper.insertPersonalStyle(personalInformation.getPersonalStyle());
    }



}
