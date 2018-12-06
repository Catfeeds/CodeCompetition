package com.isoftstone.pmit.project.hrbp.entity;

import java.util.List;

public class PersonalInformation {
    /**个人基本信息*/
    private BaseStaffInfo baseStaffInfo;
    /**家庭信息*/
    private FamilyInformation familyInformations;
    /**个人风采*/
    private PersonalStyle personalStyle;
    /**公司资格认证*/
    private List<CompanyQualification> companyQualifications;
    /**技能信息*/
    private List<TechnicalInformation> technicalInformation;
    /**个人履历信息*/
    private List<TeamInfo> teamInfos;

    public List<TeamInfo> getTeamInfos() {
        return teamInfos;
    }

    public void setTeamInfos(List<TeamInfo> teamInfos) {
        this.teamInfos = teamInfos;
    }

    public List<TechnicalInformation> getTechnicalInformation() {
        return technicalInformation;
    }

    public void setTechnicalInformation(List<TechnicalInformation> technicalInformation) {
        this.technicalInformation = technicalInformation;
    }

    public BaseStaffInfo getBaseStaffInfo() {
        return baseStaffInfo;
    }

    public void setBaseStaffInfo(BaseStaffInfo baseStaffInfo) {
        this.baseStaffInfo = baseStaffInfo;
    }

    public FamilyInformation getFamilyInformations() {
        return familyInformations;
    }

    public void setFamilyInformations(FamilyInformation familyInformations) {
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
