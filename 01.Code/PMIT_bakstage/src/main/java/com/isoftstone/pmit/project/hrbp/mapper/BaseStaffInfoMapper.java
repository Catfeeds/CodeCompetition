package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author lf
 */
@Mapper
public interface BaseStaffInfoMapper {

    BaseStaffInfo getBaseStaffInfoById(String employeeID);

    List<CompanyQualification> getCompanyQualificationById(String employeeID);

    PersonalStyle getPersonalStyleById(String employeeID);

    FamilyInformation getFamilyInfomationById(String employeeID);

    List<TechnicalInformation> getTechicalInforationById(String employeeID);

    List<TeamInfo> getTeamInfoById(String employeeID);

    void updateBaseStaffInfo(BaseStaffInfo info);
    void updatePersonalStyle(PersonalStyle personalStyle);
    void updateFamilyInformation(FamilyInformation familyInformation);
    void updateTechnicalInformation(List<TechnicalInformation> technicalInformation);

    void deletePersonalInformationById(String employeeID);
    void deletePersonalInformationsByList(List<String> employeeIdList);

    void insertBaseStaffInfo(BaseStaffInfo info);
    void insertPersonalStyle(PersonalStyle personalStyle);
    void insertFamilyInformation(FamilyInformation familyInformation);
    void insertTechnicalInformation(List<TechnicalInformation> technicalInformation);
    void insertCompanyQualification(List<CompanyQualification> companyQualifications);

    List<BaseStaffInfo> getPersonalInfoByFuzzyQuery(Map<String,Object> paramter);



}
