package com.isoftstone.pmit.project.hrbp.entity;

import java.util.List;

public class OneStaffAllInfo {
    /*个人基本信息*/
    private BaseStaffInfo baseStaffInfo;
    /*家庭信息*/
    private List<FamilyInformation> familyInformations;
    /*个人风采*/
    private PersonalStyle personalStyle;
    /*公司资格认证*/
    private List<CompanyQualification> companyQualifications;

    public BaseStaffInfo getBaseStaffInfo() {
        return baseStaffInfo;
    }

    public void setBaseStaffInfo(BaseStaffInfo baseStaffInfo) {
        this.baseStaffInfo = baseStaffInfo;
    }

    public List<FamilyInformation> getFamilyInformations() {
        return familyInformations;
    }

    public void setFamilyInformations(List<FamilyInformation> familyInformations) {
        this.familyInformations = familyInformations;
    }

    public PersonalStyle getPersonalStyle() {
        return personalStyle;
    }

    public void setPersonalStyle(PersonalStyle personalStyle) {
        this.personalStyle = personalStyle;
    }

    public List<CompanyQualification> getCompanyQualifications() {
        return companyQualifications;
    }

    public void setCompanyQualifications(List<CompanyQualification> companyQualifications) {
        this.companyQualifications = companyQualifications;
    }
}
