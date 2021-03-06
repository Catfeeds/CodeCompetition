package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PersonalInformation getBaseStaffInfoByID(Map<String,String> paraterMap) {
        if (null == paraterMap ){
            throw new NullPointerException();
        }

        BaseStaffInfo baseStaffInfo = baseStaffInfoMapper.getBaseStaffInfoById(paraterMap);
        if (null == baseStaffInfo){
            LOGGER.info("该员工已离职");
            new BaseStaffInfo();
        }
        List<CompanyQualification> qualification = baseStaffInfoMapper.getCompanyQualificationById(paraterMap);
        if (null == qualification ){
            qualification = new ArrayList<CompanyQualification>();
        }

        FamilyInformation familyInfomation = baseStaffInfoMapper.getFamilyInfomationById(paraterMap);
        if (null == familyInfomation){
            familyInfomation = new FamilyInformation();
        }
        PersonalStyle personalStyle = baseStaffInfoMapper.getPersonalStyleById(paraterMap);
        if (null == personalStyle){
            personalStyle = new PersonalStyle();
        }
        List<TechnicalInformation> techicalInforation = baseStaffInfoMapper.getTechicalInforationById(paraterMap);
        if (null == techicalInforation){
            techicalInforation = new ArrayList<TechnicalInformation>();
        }
        List<TeamInfo> teamInfoList = baseStaffInfoMapper.getTeamInfoById(paraterMap);

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
    public void deletePersonalInformationById(Map<String,String> paraterMap) {
        if (null == paraterMap) {
            throw new NullPointerException();
        }
        baseStaffInfoMapper.deletePersonalInformationById(paraterMap);
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
    public PageInfo<BaseStaffInfo> getPersonalInfoByFuzzyQuery(PersonInfoAndPageInfo paramter) {

        Map<String,Object> paramterMap = new HashMap<String, Object>();
        if (null == paramter){
            new PersonInfoAndPageInfo();
        }
        if (null == paramter.getPageInfo() ){
            new PageParam();
        }
        PageHelper.startPage(paramter.getPageInfo().getCurrPage(), paramter.getPageInfo().getPageSize());
        String sortColumn = paramter.getPageInfo().getSortColumn();
        String sortType = paramter.getPageInfo().getSortType();
        if (null != sortColumn && sortColumn != "" && sortType != "" && null != sortType) {
            PageHelper.orderBy(sortColumn + " " + sortType);
        }
        paramterMap.put("bu",paramter.getBu());
        paramterMap.put("pdu",paramter.getPdu());
        paramterMap.put("du",paramter.getDu());
        paramterMap.put("employeeId",paramter.getEmployeeID());
        paramterMap.put("employeeName",paramter.getEmployeeName());

        List<BaseStaffInfo> staffInfos = baseStaffInfoMapper.getPersonalInfoByFuzzyQuery(paramterMap);
        PageInfo<BaseStaffInfo> pageInfoBaseStaff = new PageInfo<>(staffInfos);
        return pageInfoBaseStaff;
    }

    @Override
    public List<PersonalInformation> exportPersonalInformation(PersonInfoAndPageInfo paramter) {
        if (paramter == null){
            new PersonalScoreParam();
        }
        List<PersonalInformation> personalInformations = baseStaffInfoMapper.exportPersonalInformation(paramter);

        return personalInformations;
    }


}
