package com.isoftstone.pmit.system.exceloperation.service.serviceimpl;

import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.system.exceloperation.mapper.*;
import com.isoftstone.pmit.system.exceloperation.service.IPersonalInfoService;
import com.isoftstone.pmit.system.exceloperation.util.ExcelOperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonalInfoServiceImpl implements IPersonalInfoService {

    @Autowired
    ExcelBaseStaffInfoMapper baseStaffInfoMapper;

    @Autowired
    ExcelFamilyInfoMapper familyInforMapper;

    @Autowired
    ExcelPersonalStyleMapper personalStyleMapper;

    @Autowired
    ExcelTechnicalInfoMapper technicalInfoMapper;

    @Autowired
    ExcelCompanyQualificationMapper companyQualificationMapper;

    /**
     * 导入个人信息分表数据组装并实现插入数据库
     * @param personalInfoExcelPath 需要导入excel文件路径
     * @return
     */
    @Override
    public String importPersonalInfo(String personalInfoExcelPath) {

        // 读取excel文件的所有数据
        Map<Integer, Object> result = new HashMap<Integer, Object>();
        result = ExcelOperationUtils.importExcel(personalInfoExcelPath);
        if (result.containsKey(0)) {
            return (String)result.get(0);
        }

        // 得到正确的excel文件的数据
        List<Map<String, String>> personalInfoList = (List<Map<String, String>>)result.get(1);

        List<BaseStaffInfo> baseStaffInfoList = new ArrayList<BaseStaffInfo>();
        List<FamilyInformation> familyInfoList = new ArrayList<FamilyInformation>();
        List<PersonalStyle> personalStyleList = new ArrayList<PersonalStyle>();
        List<TechnicalInformation> technicalInforList = new ArrayList<TechnicalInformation>();
        List<CompanyQualification> companyQualificationList = new ArrayList<CompanyQualification>();

        for (Map<String, String> personalInfo : personalInfoList){

            // 组装人员基本信息表数据
            BaseStaffInfo baseStaffInfo = new BaseStaffInfo();
            baseStaffInfo.setEmployeeID(personalInfo.get("软通工号"));
            baseStaffInfo.setEmployeeName(personalInfo.get("员工姓名"));
            baseStaffInfo.setIdNumber(personalInfo.get("身份证号"));
            baseStaffInfo.setIsoftStatus(personalInfo.get("软通状态"));
            baseStaffInfo.setPersonCategory(personalInfo.get("人员类别"));
            baseStaffInfo.setIfBackBone(personalInfo.get("是否骨干"));
            baseStaffInfo.setIfOffShore(personalInfo.get("是否在岸"));
            baseStaffInfo.setWorkPlace(personalInfo.get("办公实际场地"));
            baseStaffInfo.setWorkStationNumber(Integer.parseInt(personalInfo.get("工位编号")));
            baseStaffInfo.setCooperationMode(personalInfo.get("合作模式"));
            baseStaffInfo.setPositionRole(personalInfo.get("岗位角色"));
            baseStaffInfo.setSkill(personalInfo.get("技能"));
            baseStaffInfo.setSillDirection(personalInfo.get("技能方向"));
            baseStaffInfo.setDirectLeaderId(personalInfo.get("直接汇报上级工号"));
            baseStaffInfo.setDirectLeaderName(personalInfo.get("直接汇报上级姓名"));
            baseStaffInfo.setEmploymentDate(personalInfo.get("入职日期"));
            baseStaffInfo.setDepartureDate(personalInfo.get("离职日期"));
            baseStaffInfo.setGeneralPosition(personalInfo.get("通用软通职位"));
            baseStaffInfo.setCurrentPositionDate(personalInfo.get("现职位生效日期"));
            baseStaffInfo.setCostCenter(personalInfo.get("成本中心"));
            baseStaffInfo.setCostCenterLeaderName(personalInfo.get("成本中心负责人"));
            baseStaffInfo.setBu(personalInfo.get("BU"));
            baseStaffInfo.setDu(personalInfo.get("DU"));
            baseStaffInfo.setPdu(personalInfo.get("PDU"));
            baseStaffInfo.setWorkPlaceArea(personalInfo.get("所在区域"));
            baseStaffInfo.setSex(personalInfo.get("性别"));
            baseStaffInfo.setGraduationSchool(personalInfo.get("毕业学校"));
            baseStaffInfo.setGraduationTime(personalInfo.get("毕业时间"));
            baseStaffInfo.setIfUnderGraduates(personalInfo.get("是否统招"));
            baseStaffInfo.setEducation(personalInfo.get("学历"));
            baseStaffInfo.setDegree(personalInfo.get("学位"));
            baseStaffInfo.setIf211(personalInfo.get("是否211"));
            baseStaffInfo.setIfGraduate(personalInfo.get("是否应届"));
            baseStaffInfo.setGraduationMajor(personalInfo.get("专业名称"));
            baseStaffInfo.setIfAbvoveTwoInstitutions(personalInfo.get("是否二本及以上"));
            baseStaffInfo.setEducationMode(personalInfo.get("教育模式"));
            baseStaffInfo.setTelephone(personalInfo.get("联系电话"));
            baseStaffInfo.setIsoftEmai(personalInfo.get("软通邮箱"));
            baseStaffInfo.setDomainUserName(personalInfo.get("域用户名"));
            baseStaffInfo.setNation(personalInfo.get("民族"));
            baseStaffInfo.setNativePlace(personalInfo.get("籍贯"));
            baseStaffInfo.setPolitical(personalInfo.get("政治面貌"));
            baseStaffInfo.setProjectTeamId(Integer.parseInt(personalInfo.get("项目组ID")));
            baseStaffInfo.setCurrentStatus(personalInfo.get("当前人员状态"));
            baseStaffInfoList.add(baseStaffInfo);

            // 组装家庭信息表数据
            FamilyInformation familyInformation = new FamilyInformation();
            familyInformation.setEmployeeID(personalInfo.get("软通工号"));
            familyInformation.setBirthdayDate(personalInfo.get("出生日期"));
            familyInformation.setHeigh(personalInfo.get("身高"));
            familyInformation.setWeight(personalInfo.get("体重"));
            familyInformation.setCurrentHomeAddress(personalInfo.get("现居住地址"));
            familyInformation.setEmergencyContact(personalInfo.get("紧急联系人"));
            familyInformation.setEmergencyContactRelation(personalInfo.get("紧急联系人关系"));
            familyInformation.setEmergencyContactAdress(personalInfo.get("紧急联系人住址"));
            familyInformation.setEmergencyContactPhone(personalInfo.get("紧急联系人电话"));
            familyInformation.setMaritalStatus(personalInfo.get("婚姻状态"));
            familyInformation.setChildInformation(personalInfo.get("子女信息"));
            familyInformation.setWayToWork(personalInfo.get("上班方式"));
            familyInformation.setWorkingHour(personalInfo.get("上班路上时长"));
            familyInformation.setIsbuyHouse(personalInfo.get("是否买房"));
            familyInformation.setDayOfBuyHouse(personalInfo.get("买房日期"));
            familyInformation.setPropertyAddress(personalInfo.get("房产地址"));
            familyInfoList.add(familyInformation);

            // 组装个人风采表数据
            PersonalStyle personalStyle = new PersonalStyle();
            personalStyle.setEmployeeID(personalInfo.get("软通工号"));
            personalStyle.setFood(personalInfo.get("喜欢的食物"));
            personalStyle.setGame(personalInfo.get("喜欢的游戏"));
            personalStyle.setMotion(personalInfo.get("喜欢的运动"));
            personalStyle.setBook(personalInfo.get("喜欢的书籍"));
            personalStyle.setMovie(personalInfo.get("喜欢的电影"));
            personalStyle.setMusic(personalInfo.get("喜欢的音乐"));
            personalStyle.setOneSentence(personalInfo.get("喜欢的一句话"));
            personalStyle.setDislike(personalInfo.get("不喜欢的"));
            personalStyle.setPhotosOfLife(personalInfo.get("生活照照片路径"));
            personalStyle.setDocumentPhoto(personalInfo.get("证件照照片路径"));
            personalStyleList.add(personalStyle);

            // 组装技能信息表数据
            List<TechnicalInformation> technicalInformations = new ArrayList<TechnicalInformation>();
            String technicalName = personalInfo.get("技能名称");
            if (technicalName.contains(";")){
                String[] technicalNames = technicalName.split(";");
                String[] technologyPlatforms = personalInfo.get("技术平台").split(";");
                String[] technologyGrades = personalInfo.get("技术等级").split(";");
                String[] areasOfExpertises = personalInfo.get("擅长领域").split(";");
                int length= technicalNames.length;
                for (int index = 0; index < length; index++){
                    TechnicalInformation technicalInformation = new TechnicalInformation();
                    technicalInformation.setTechnicalName(technicalNames[index]);
                    technicalInformation.setEmployeeID(personalInfo.get("软通工号"));
                    technicalInformation.setTechnologyPlatform(technologyPlatforms[index]);
                    technicalInformation.setTechnologyGrade(technologyGrades[index]);
                    technicalInformation.setAreasOfExpertise(areasOfExpertises[index]);
                    technicalInformations.add(technicalInformation);
                }
            }else{
                TechnicalInformation technicalInformation = new TechnicalInformation();
                technicalInformation.setTechnicalName(technicalName);
                technicalInformation.setEmployeeID(personalInfo.get("软通工号"));
                technicalInformation.setTechnologyPlatform(personalInfo.get("技术平台"));
                technicalInformation.setTechnologyGrade(personalInfo.get("技术等级"));
                technicalInformation.setAreasOfExpertise(personalInfo.get("擅长领域"));
                technicalInformations.add(technicalInformation);
            }
            technicalInforList.addAll(technicalInformations);

            // 组装资质认证信息表数据
            List<CompanyQualification> companyQualifications = new ArrayList<CompanyQualification>();
            String certificateNum = personalInfo.get("证书编号");
            if (certificateNum.contains(";")){
                String[] certificateNums = certificateNum.split(";");
                String[] qualificationNames = personalInfo.get("资质名称").split(";");
                String[] qualificationTypes = personalInfo.get("资质类型").split(";");
                String[] qualificationLevels = personalInfo.get("认证等级").split(";");
                String[] passDates = personalInfo.get("通过日期").split(";");
                int length= certificateNums.length;
                for (int index = 0; index < length; index++){
                    CompanyQualification companyQualification = new CompanyQualification();
                    companyQualification.setEmployeeID(personalInfo.get("软通工号"));
                    companyQualification.setCertificateNum(Integer.parseInt(certificateNums[index]));
                    companyQualification.setQualificationName(qualificationNames[index]);
                    companyQualification.setQualificationType(qualificationTypes[index]);
                    companyQualification.setCertificationLevel(qualificationLevels[index]);
                    companyQualification.setPassDate(passDates[index]);
                    companyQualifications.add(companyQualification);
                }
            }else{
                CompanyQualification companyQualification = new CompanyQualification();
                companyQualification.setEmployeeID(personalInfo.get("软通工号"));
                companyQualification.setCertificateNum(Integer.parseInt(certificateNum));
                companyQualification.setQualificationName(personalInfo.get("资质名称"));
                companyQualification.setQualificationType(personalInfo.get("资质类型"));
                companyQualification.setCertificationLevel(personalInfo.get("认证等级"));
                companyQualification.setPassDate(personalInfo.get("通过日期"));
                companyQualifications.add(companyQualification);
            }
            companyQualificationList.addAll(companyQualifications);
        }


        // 分别插入5张个人相关信息表中
        int baseStaffInfoAddResult = baseStaffInfoMapper.insertBaseStaffInfo(baseStaffInfoList);
        int familyInforAddResult = familyInforMapper.insertFamilyInformation(familyInfoList);
        int personalStyleAddResult = personalStyleMapper.insertPersonalStyle(personalStyleList);
        int technicalInfoAddResult = technicalInfoMapper.insertTechnicalInformation(technicalInforList);
        int comQuaAddResult = companyQualificationMapper.insertCompanyQualification(companyQualificationList);
        if (baseStaffInfoAddResult + familyInforAddResult + personalStyleAddResult
                + technicalInfoAddResult + comQuaAddResult >= 5){
            return "SUCCESS";
        }
        return "有数据插入数据库失败";
    }
}






















