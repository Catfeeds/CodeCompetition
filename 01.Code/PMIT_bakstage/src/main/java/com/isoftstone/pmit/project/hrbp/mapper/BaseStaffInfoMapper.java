package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    void updateBaseStaffInfo(BaseStaffInfo info);
    void updatePersonalStyle(PersonalStyle personalStyle);
    void updateFamilyInformation(FamilyInformation familyInformation);
    void updateTechnicalInformation(List<TechnicalInformation> technicalInformation);

}
