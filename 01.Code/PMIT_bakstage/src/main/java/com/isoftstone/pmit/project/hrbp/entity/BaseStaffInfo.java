package com.isoftstone.pmit.project.hrbp.entity;

import java.util.Date;

public class BaseStaffInfo {

    /*软通工号*/
    private String employeeID;
    /*员工姓名*/
    private String employeeName;
    /*身份证号*/
    private String idNumber;
    /*软通状态*/
    private String isoftStatus;
    /*是否骨干*/
    private String ifBackBone;
    /*是否在岸*/
    private String ifOffShore;
    /*办公场地*/
    private String workPlace;
    /*工位编号*/
    private int workStationNumber;
    /*合作模式*/
    private String cooperationMode;
    /*岗位角色*/
    private String positionRole;
    /*岗位*/
    private String position;
    /*技能*/
    private String skill;
    /*技能方向*/
    private String sillDirection;
    /*直接汇报上级工号*/
    private String directLeaderId;
    /*直接汇报上级姓名*/
    private String directLeaderName;
    /*入职日期*/
    private String employmentDate;
    /*离职日期*/
    private String departureDate;
    /*通用软通职位。eg：主管工程师(四级)-HW*/
    private String generalPosition;
    /*现职位生效时间*/
    private String currentPositionDate;
    /*成本中心*/
    private String costCenter;
    /*成本中心负责人*/
    private String costCenterLeaderName;
    /*bu*/
    private String bu;
    /*du*/
    private String du;
    /*pdu*/
    private String pdu;
    /*办公地点*/
    private String workPlaceArea;
    /*性别*/
    private String sex;
    /*联系电话*/
    private String telephone;
    /*软通邮箱*/
    private String isoftEmai;
    /*域用户名*/
    private String domainUserName;
    /*民族*/
    private String nation;
    /*籍贯*/
    private String nativePlace;
    /*政治面貌*/
    private String political;
    /*项目组ID*/
    private int projectTeamId;
    /*当前人员状态*/
    private String currentStatus;
    /*工龄*/
    private String lengthOfService;
    /*司龄*/
    private String divisionAge;
    /*==================教育信息========================*/
    /*毕业学校*/
    private String graduationSchool;
    /*专业名称*/
    private String graduationMajor;
    /*毕业时间*/
    private String graduationTime;
    /*是否211*/
    private String if211;
    /*是否统招。eg:是/否*/
    private String ifUnderGraduates;
    /*是否二本及以上。eg:是/否*/
    private String ifAbvoveTwoInstitutions;
    /*学历*/
    private String education;
    /*学位*/
    private String degree;
    /*是否应届*/
    private String ifGraduate;
    /*教育模式*/
    private String educationMode;



    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIsoftStatus() {
        return isoftStatus;
    }

    public void setIsoftStatus(String isoftStatus) {
        this.isoftStatus = isoftStatus;
    }

    public String getIfBackBone() {
        return ifBackBone;
    }

    public void setIfBackBone(String ifBackBone) {
        this.ifBackBone = ifBackBone;
    }

    public String getIfOffShore() {
        return ifOffShore;
    }

    public void setIfOffShore(String ifOffShore) {
        this.ifOffShore = ifOffShore;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public int getWorkStationNumber() {
        return workStationNumber;
    }

    public void setWorkStationNumber(int workStationNumber) {
        this.workStationNumber = workStationNumber;
    }

    public String getCooperationMode() {
        return cooperationMode;
    }

    public void setCooperationMode(String cooperationMode) {
        this.cooperationMode = cooperationMode;
    }

    public String getPositionRole() {
        return positionRole;
    }

    public void setPositionRole(String positionRole) {
        this.positionRole = positionRole;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSillDirection() {
        return sillDirection;
    }

    public void setSillDirection(String sillDirection) {
        this.sillDirection = sillDirection;
    }

    public String getDirectLeaderId() {
        return directLeaderId;
    }

    public void setDirectLeaderId(String directLeaderId) {
        this.directLeaderId = directLeaderId;
    }

    public String getDirectLeaderName() {
        return directLeaderName;
    }

    public void setDirectLeaderName(String directLeaderName) {
        this.directLeaderName = directLeaderName;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getGeneralPosition() {
        return generalPosition;
    }

    public void setGeneralPosition(String generalPosition) {
        this.generalPosition = generalPosition;
    }

    public String getCurrentPositionDate() {
        return currentPositionDate;
    }

    public void setCurrentPositionDate(String currentPositionDate) {
        this.currentPositionDate = currentPositionDate;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getCostCenterLeaderName() {
        return costCenterLeaderName;
    }

    public void setCostCenterLeaderName(String costCenterLeaderName) {
        this.costCenterLeaderName = costCenterLeaderName;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public String getDu() {
        return du;
    }

    public void setDu(String du) {
        this.du = du;
    }

    public String getPdu() {
        return pdu;
    }

    public void setPdu(String pdu) {
        this.pdu = pdu;
    }

    public String getWorkPlaceArea() {
        return workPlaceArea;
    }

    public void setWorkPlaceArea(String workPlaceArea) {
        this.workPlaceArea = workPlaceArea;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIsoftEmai() {
        return isoftEmai;
    }

    public void setIsoftEmai(String isoftEmai) {
        this.isoftEmai = isoftEmai;
    }

    public String getDomainUserName() {
        return domainUserName;
    }

    public void setDomainUserName(String domainUserName) {
        this.domainUserName = domainUserName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public int getProjectTeamId() {
        return projectTeamId;
    }

    public void setProjectTeamId(int projectTeamId) {
        this.projectTeamId = projectTeamId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getLengthOfService() {
        return lengthOfService;
    }

    public void setLengthOfService(String lengthOfService) {
        this.lengthOfService = lengthOfService;
    }

    public String getDivisionAge() {
        return divisionAge;
    }

    public void setDivisionAge(String divisionAge) {
        this.divisionAge = divisionAge;
    }

    public String getGraduationSchool() {
        return graduationSchool;
    }

    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool;
    }

    public String getGraduationMajor() {
        return graduationMajor;
    }

    public void setGraduationMajor(String graduationMajor) {
        this.graduationMajor = graduationMajor;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getIf211() {
        return if211;
    }

    public void setIf211(String if211) {
        this.if211 = if211;
    }

    public String getIfUnderGraduates() {
        return ifUnderGraduates;
    }

    public void setIfUnderGraduates(String ifUnderGraduates) {
        this.ifUnderGraduates = ifUnderGraduates;
    }

    public String getIfAbvoveTwoInstitutions() {
        return ifAbvoveTwoInstitutions;
    }

    public void setIfAbvoveTwoInstitutions(String ifAbvoveTwoInstitutions) {
        this.ifAbvoveTwoInstitutions = ifAbvoveTwoInstitutions;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getIfGraduate() {
        return ifGraduate;
    }

    public void setIfGraduate(String ifGraduate) {
        this.ifGraduate = ifGraduate;
    }

    public String getEducationMode() {
        return educationMode;
    }

    public void setEducationMode(String educationMode) {
        this.educationMode = educationMode;
    }
}
