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

    BaseStaffInfo getBaseStaffInfoById(Map<String,String> paraterMap);

    List<CompanyQualification> getCompanyQualificationById(Map<String,String> paraterMap);

    PersonalStyle getPersonalStyleById(Map<String,String> paraterMap);

    FamilyInformation getFamilyInfomationById(Map<String,String> paraterMap);

    List<TechnicalInformation> getTechicalInforationById(Map<String,String> paraterMap);

    List<TeamInfo> getTeamInfoById(Map<String,String> paraterMap);

    void updateBaseStaffInfo(BaseStaffInfo info);
    void updatePersonalStyle(PersonalStyle personalStyle);
    void updateFamilyInformation(FamilyInformation familyInformation);
    void updateTechnicalInformation(List<TechnicalInformation> technicalInformation);

    void deletePersonalInformationById(Map<String,String> paraterMap);
    void deletePersonalInformationsByList(List<String> employeeIdList);

    void insertBaseStaffInfo(BaseStaffInfo info);
    void insertPersonalStyle(PersonalStyle personalStyle);
    void insertFamilyInformation(FamilyInformation familyInformation);
    void insertTechnicalInformation(List<TechnicalInformation> technicalInformation);
    void insertCompanyQualification(List<CompanyQualification> companyQualifications);

    List<BaseStaffInfo> getPersonalInfoByFuzzyQuery(Map<String,Object> paramter);
    int getPersonalListSize();


}
