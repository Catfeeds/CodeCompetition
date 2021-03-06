package com.isoftstone.pmit.project.hrbp.entity;

public class CompanyQualification {

    /*员工工号*/
    private String employeeID;
    /*证书编号*/
    private int certificateNum;
    /*资质名称*/
    private String qualificationName;
    /*资质类型*/
    private String qualificationType;
    /*认证等级*/
    private String certificationLevel;
    /*通过日期*/
    private String passDate;

    public int getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(int certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }


    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    public String getPassDate() {
        return passDate;
    }

    public void setPassDate(String passDate) {
        this.passDate = passDate;
    }
}
