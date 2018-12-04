package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.project.hrbp.mapper.BaseStaffInfoMapper;
import com.isoftstone.pmit.project.hrbp.service.BaseStaffInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaseStaffInfoServiceImpl implements BaseStaffInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseStaffInfoServiceImpl.class);
    @Autowired
    private BaseStaffInfoMapper baseStaffInfoMapper;

    @Override
    public PersonalInformation getBaseStaffInfoByID(String employeeID) {
        if (null == employeeID ){
            throw new NullPointerException();
        }
        BaseStaffInfo baseStaffInfo = baseStaffInfoMapper.getBaseStaffInfoById(employeeID);
        if (null == baseStaffInfo){
            LOGGER.info("该员工已离职");
            new BaseStaffInfo();
        }
        List<CompanyQualification> qualification = baseStaffInfoMapper.getCompanyQualificationById(employeeID);
        if (null == qualification ){
            qualification = new ArrayList<CompanyQualification>();
        }

        FamilyInformation familyInfomation = baseStaffInfoMapper.getFamilyInfomationById(employeeID);
        if (null == familyInfomation){
            familyInfomation = new FamilyInformation();
        }
        PersonalStyle personalStyle = baseStaffInfoMapper.getPersonalStyleById(employeeID);
        if (null == personalStyle){
            personalStyle = new PersonalStyle();
        }
        List<TechnicalInformation> techicalInforation = baseStaffInfoMapper.getTechicalInforationById(employeeID);
        if (null == techicalInforation){
            techicalInforation = new ArrayList<TechnicalInformation>();
        }
        List<TeamInfo> teamInfoList = baseStaffInfoMapper.getTeamInfoById(employeeID);

        if (null == teamInfoList){
            teamInfoList = new ArrayList<>();
        }

        PersonalInformation staffInfo =new PersonalInformation();
        staffInfo.setBaseStaffInfo(baseStaffInfo);
        staffInfo.setCompanyQualifications(qualification);
        staffInfo.setFamilyInformations(familyInfomation);
        staffInfo.setPersonalStyle(personalStyle);
        staffInfo.setTechnicalInformation(techicalInforation);
        staffInfo.setTeamInfos(teamInfoList);
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
             new PersonalInformation();
        }

        baseStaffInfoMapper.insertBaseStaffInfo(personalInformation.getBaseStaffInfo());
        baseStaffInfoMapper.insertCompanyQualification(personalInformation.getCompanyQualifications());
        baseStaffInfoMapper.insertFamilyInformation(personalInformation.getFamilyInformations());
        baseStaffInfoMapper.insertTechnicalInformation(personalInformation.getTechnicalInformation());
        baseStaffInfoMapper.insertPersonalStyle(personalInformation.getPersonalStyle());
    }

    @Override
    public List<BaseStaffInfo> getPersonalInfoByFuzzyQuery(PersonInfoAndPageInfo paramter) {

        Map<String,Object> paramterMap = new HashMap<String, Object>();
        if (null == paramter){
            new PersonInfoAndPageInfo();
        }
        if (null == paramter.getPageInfo() ){
            new PageInfo();
        }

        paramterMap.put("currIndex",(paramter.getPageInfo().getCurrPage()-1)*paramter.getPageInfo().getPageSize());
        paramterMap.put("pageSize",paramter.getPageInfo().getPageSize());
        paramterMap.put("bu",paramter.getBaseStaffInfo().getBu());
        paramterMap.put("pdu",paramter.getBaseStaffInfo().getPdu());
        paramterMap.put("du",paramter.getBaseStaffInfo().getDu());
        paramterMap.put("employeeId",paramter.getBaseStaffInfo().getEmployeeID());
        paramterMap.put("employeeName",paramter.getBaseStaffInfo().getEmployeeName());
        if (null == paramterMap){
            new HashMap<String,Object>();
        }

        List<BaseStaffInfo> staffInfos = baseStaffInfoMapper.getPersonalInfoByFuzzyQuery(paramterMap);
        return staffInfos;
    }


}
