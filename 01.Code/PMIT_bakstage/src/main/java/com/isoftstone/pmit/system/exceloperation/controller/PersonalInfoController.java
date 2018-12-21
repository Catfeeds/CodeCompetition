package com.isoftstone.pmit.system.exceloperation.controller;

import com.isoftstone.pmit.common.util.AjaxResult;
import com.isoftstone.pmit.project.hrbp.entity.*;
import com.isoftstone.pmit.project.hrbp.service.BaseStaffInfoService;
import com.isoftstone.pmit.system.exceloperation.service.IPersonalInfoService;
import com.isoftstone.pmit.system.exceloperation.util.ExcelOperationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/exceloperation")
@Api(value = "个人信息", tags = {"导入EXCEL文件的个人信息"})
public class PersonalInfoController {

    @Autowired
    private IPersonalInfoService personalInfoService;

    @Autowired
    private BaseStaffInfoService baseStaffInfoService;

    /**
     * 导入个人所有信息
     * @param file 导入的excel文件
     * @param request
     * @return
     */
    @PostMapping(value ="/importPersonalInfo")
    @ResponseBody
    @ApiOperation(value = "importPersonalInfo", notes = "importPersonalInfo")
    public String importPersonalInfo(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + saveFileName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return personalInfoService.importPersonalInfo(saveFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return AjaxResult.returnToResult(false,"上传失败," + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxResult.returnToResult(false,"上传失败," + e.getMessage());
            }
        } else {
            return AjaxResult.returnToResult(false,"上传失败，因为文件为空.");
        }
    }

    @RequestMapping(value = "/exportPersonalInfo", method = { RequestMethod.POST })
    @ResponseBody
    @ApiOperation(value="exportPersonalInfo", notes="exportPersonalInfo")
    public void exportScore(@RequestBody PersonInfoAndPageInfo param,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        List<PersonalInformation> personalInfoList = baseStaffInfoService.exportPersonalInformation(param);

        List<String> baseInfoTitleName = new ArrayList<String>();
        baseInfoTitleName.add("软通工号");
        baseInfoTitleName.add("员工姓名");
        baseInfoTitleName.add("身份证号");
        baseInfoTitleName.add("软通状态");
        baseInfoTitleName.add("人员类别");
        baseInfoTitleName.add("是否骨干");
        baseInfoTitleName.add("是否在岸");
        baseInfoTitleName.add("办公实际场地");
        baseInfoTitleName.add("工位编号");
        baseInfoTitleName.add("合作模式");
        baseInfoTitleName.add("岗位角色");
        baseInfoTitleName.add("技能");
        baseInfoTitleName.add("技能方向");
        baseInfoTitleName.add("直接汇报上级工号");
        baseInfoTitleName.add("直接汇报上级姓名");
        baseInfoTitleName.add("入职日期");
        baseInfoTitleName.add("离职日期");
        baseInfoTitleName.add("通用软通职位");
        baseInfoTitleName.add("现职位生效日期");
        baseInfoTitleName.add("成本中心");
        baseInfoTitleName.add("成本中心负责人");
        baseInfoTitleName.add("BU");
        baseInfoTitleName.add("DU");
        baseInfoTitleName.add("PDU");
        baseInfoTitleName.add("所在区域");
        baseInfoTitleName.add("性别");
        baseInfoTitleName.add("毕业学校");
        baseInfoTitleName.add("毕业时间");
        baseInfoTitleName.add("是否统招");
        baseInfoTitleName.add("学历");
        baseInfoTitleName.add("学位");
        baseInfoTitleName.add("是否211");
        baseInfoTitleName.add("是否应届");
        baseInfoTitleName.add("专业名称");
        baseInfoTitleName.add("是否二本及以上");
        baseInfoTitleName.add("教育模式");
        baseInfoTitleName.add("联系电话");
        baseInfoTitleName.add("软通邮箱");
        baseInfoTitleName.add("域用户名");
        baseInfoTitleName.add("民族");
        baseInfoTitleName.add("籍贯");
        baseInfoTitleName.add("政治面貌");
        baseInfoTitleName.add("项目组ID");
        baseInfoTitleName.add("当前人员状态");
        baseInfoTitleName.add("等级");

        List<String> familyInfoTitleName = new ArrayList<String>();
        familyInfoTitleName.add("软通工号");
        familyInfoTitleName.add("出生日期");
        familyInfoTitleName.add("身高");
        familyInfoTitleName.add("体重");
        familyInfoTitleName.add("现居住地址");
        familyInfoTitleName.add("紧急联系人");
        familyInfoTitleName.add("紧急联系人关系");
        familyInfoTitleName.add("紧急联系人住址");
        familyInfoTitleName.add("紧急联系人电话");
        familyInfoTitleName.add("婚姻状态");
        familyInfoTitleName.add("子女信息");
        familyInfoTitleName.add("上班方式");
        familyInfoTitleName.add("上班路上时长");
        familyInfoTitleName.add("是否买房");
        familyInfoTitleName.add("买房日期");
        familyInfoTitleName.add("房产地址");

        List<String> personStyleTitleName = new ArrayList<String>();
        personStyleTitleName.add("软通工号");
        personStyleTitleName.add("喜欢的食物");
        personStyleTitleName.add("喜欢的游戏");
        personStyleTitleName.add("喜欢的运动");
        personStyleTitleName.add("喜欢的书籍");
        personStyleTitleName.add("喜欢的电影");
        personStyleTitleName.add("喜欢的音乐");
        personStyleTitleName.add("喜欢的一句话");
        personStyleTitleName.add("不喜欢的");
        personStyleTitleName.add("生活照照片路径");
        personStyleTitleName.add("证件照照片路径");

        List<String> techTitleName = new ArrayList<String>();
        techTitleName.add("软通工号");
        techTitleName.add("技能名称");
        techTitleName.add("技术平台");
        techTitleName.add("技术等级");
        techTitleName.add("擅长领域");

        List<String> companyTitleName = new ArrayList<String>();
        companyTitleName.add("软通工号");
        companyTitleName.add("证书编号");
        companyTitleName.add("资质名称");
        companyTitleName.add("资质类型");
        companyTitleName.add("认证等级");
        companyTitleName.add("通过日期");

        List<Map<String, String>> baseInfoListMap = new ArrayList<Map<String, String>>();
        List<Map<String, String>> familyInfoListMap = new ArrayList<Map<String, String>>();
        List<Map<String, String>> personStyleListMap = new ArrayList<Map<String, String>>();
        List<Map<String, String>> techListMap = new ArrayList<Map<String, String>>();
        List<Map<String, String>> companyListMap = new ArrayList<Map<String, String>>();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (PersonalInformation personalInfo : personalInfoList)
        {
            Map<String, String> baseInfoMap = new HashMap<String, String>();
            BaseStaffInfo baseInfo = personalInfo.getBaseStaffInfo();
            baseInfoMap.put("软通工号", baseInfo.getEmployeeID());
            baseInfoMap.put("员工姓名", baseInfo.getEmployeeName());
            baseInfoMap.put("身份证号", baseInfo.getIdNumber());
            baseInfoMap.put("软通状态", baseInfo.getIsoftStatus());
            baseInfoMap.put("人员类别", baseInfo.getPersonCategory());
            baseInfoMap.put("是否骨干", baseInfo.getIfBackBone());
            baseInfoMap.put("是否在岸", baseInfo.getIfOffShore());
            baseInfoMap.put("办公实际场地", baseInfo.getWorkPlace());
            baseInfoMap.put("工位编号", baseInfo.getWorkStationNumber());
            baseInfoMap.put("合作模式", baseInfo.getCooperationMode());
            baseInfoMap.put("岗位角色", baseInfo.getPositionRole());
            baseInfoMap.put("技能", baseInfo.getSkill());
            baseInfoMap.put("技能方向", baseInfo.getSillDirection());
            baseInfoMap.put("直接汇报上级工号", baseInfo.getDirectLeaderId());
            baseInfoMap.put("直接汇报上级姓名", baseInfo.getDirectLeaderName());
            baseInfoMap.put("入职日期", baseInfo.getEmploymentDate());
            baseInfoMap.put("离职日期", baseInfo.getDepartureDate());
            baseInfoMap.put("通用软通职位", baseInfo.getGeneralPosition());
            baseInfoMap.put("现职位生效日期", baseInfo.getCurrentPositionDate());
            baseInfoMap.put("成本中心", baseInfo.getCostCenter());
            baseInfoMap.put("成本中心负责人", baseInfo.getCostCenterLeaderName());
            baseInfoMap.put("BU", baseInfo.getBu());
            baseInfoMap.put("DU", baseInfo.getDu());
            baseInfoMap.put("PDU", baseInfo.getPdu());
            baseInfoMap.put("所在区域", baseInfo.getWorkPlaceArea());
            baseInfoMap.put("性别", baseInfo.getSex());
            baseInfoMap.put("毕业学校", baseInfo.getGraduationSchool());
            baseInfoMap.put("毕业时间", baseInfo.getGraduationTime());
            baseInfoMap.put("是否统招", baseInfo.getIfUnderGraduates());
            baseInfoMap.put("学历", baseInfo.getEducation());
            baseInfoMap.put("学位", baseInfo.getDegree());
            baseInfoMap.put("是否211", baseInfo.getIf211());
            baseInfoMap.put("是否应届", baseInfo.getIfGraduate());
            baseInfoMap.put("专业名称", baseInfo.getGraduationMajor());
            baseInfoMap.put("是否二本及以上", baseInfo.getIfAbvoveTwoInstitutions());
            baseInfoMap.put("教育模式", baseInfo.getEducationMode());
            baseInfoMap.put("联系电话", baseInfo.getTelephone());
            baseInfoMap.put("软通邮箱", baseInfo.getIsoftEmai());
            baseInfoMap.put("域用户名", baseInfo.getDomainUserName());
            baseInfoMap.put("民族", baseInfo.getNation());
            baseInfoMap.put("籍贯", baseInfo.getNativePlace());
            baseInfoMap.put("政治面貌", baseInfo.getPolitical());
            baseInfoMap.put("项目组ID", String.valueOf(baseInfo.getProjectTeamId()));
            baseInfoMap.put("当前人员状态", baseInfo.getCurrentStatus());
            baseInfoMap.put("等级", baseInfo.getRank());
            baseInfoListMap.add(baseInfoMap);

            Map<String, String> familyInfoMap = new HashMap<String, String>();
            FamilyInformation familyInfo = personalInfo.getFamilyInformations();
            familyInfoMap.put("软通工号", familyInfo.getEmployeeID());
            familyInfoMap.put("出生日期", familyInfo.getBirthdayDate());
            familyInfoMap.put("身高", familyInfo.getHeigh());
            familyInfoMap.put("体重", familyInfo.getWeight());
            familyInfoMap.put("现居住地址", familyInfo.getCurrentHomeAddress());
            familyInfoMap.put("紧急联系人", familyInfo.getEmergencyContact());
            familyInfoMap.put("紧急联系人关系", familyInfo.getEmergencyContactRelation());
            familyInfoMap.put("紧急联系人住址", familyInfo.getEmergencyContactAdress());
            familyInfoMap.put("紧急联系人电话", familyInfo.getEmergencyContactPhone());
            familyInfoMap.put("婚姻状态", familyInfo.getMaritalStatus());
            familyInfoMap.put("子女信息", familyInfo.getChildInformation());
            familyInfoMap.put("上班方式", familyInfo.getWayToWork());
            familyInfoMap.put("上班路上时长", familyInfo.getWorkingHour());
            familyInfoMap.put("是否买房", familyInfo.getIsbuyHouse());
            familyInfoMap.put("买房日期", familyInfo.getDayOfBuyHouse());
            familyInfoMap.put("房产地址", familyInfo.getPropertyAddress());
            familyInfoListMap.add(familyInfoMap);

            Map<String, String> personStyleMap = new HashMap<String, String>();
            PersonalStyle personInfo = personalInfo.getPersonalStyle();
            personStyleMap.put("软通工号", personInfo.getEmployeeID());
            personStyleMap.put("喜欢的食物", personInfo.getFood());
            personStyleMap.put("喜欢的游戏", personInfo.getGame());
            personStyleMap.put("喜欢的运动", personInfo.getMotion());
            personStyleMap.put("喜欢的书籍", personInfo.getBook());
            personStyleMap.put("喜欢的电影", personInfo.getMovie());
            personStyleMap.put("喜欢的音乐", personInfo.getMusic());
            personStyleMap.put("喜欢的一句话", personInfo.getOneSentence());
            personStyleMap.put("不喜欢的", personInfo.getDislike());
            personStyleMap.put("生活照照片路径", personInfo.getPhotosOfLife());
            personStyleMap.put("证件照照片路径", personInfo.getDocumentPhoto());
            personStyleListMap.add(personStyleMap);

            for (TechnicalInformation tech : personalInfo.getTechnicalInformation())
            {
                Map<String, String> techMap = new HashMap<String, String>();
                techMap.put("软通工号", tech.getEmployeeID());
                techMap.put("技能名称", tech.getTechnicalName());
                techMap.put("技术平台", tech.getTechnologyPlatform());
                techMap.put("技术等级", tech.getTechnologyGrade());
                techMap.put("擅长领域", tech.getAreasOfExpertise());
                techListMap.add(techMap);
            }

            for (CompanyQualification company : personalInfo.getCompanyQualifications())
            {
                Map<String, String> companyMap = new HashMap<String, String>();
                companyMap.put("软通工号", company.getEmployeeID());
                companyMap.put("证书编号", String.valueOf(company.getCertificateNum()));
                companyMap.put("资质名称", company.getQualificationName());
                companyMap.put("资质类型", company.getQualificationType());
                companyMap.put("认证等级", company.getCertificationLevel());
                companyMap.put("通过日期", company.getPassDate());
                companyListMap.add(companyMap);
            }

        }

        try{
            File templateFile = new File("src/main/resources/configfilemodel/exportPersonalinfoTemplate.xlsx");
            InputStream inputStream = new FileInputStream(templateFile);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            ExcelOperationUtils.writeSheet(workbook.getSheetAt(0), baseInfoTitleName, baseInfoListMap);
            ExcelOperationUtils.writeSheet(workbook.getSheetAt(1), familyInfoTitleName, familyInfoListMap);
            ExcelOperationUtils.writeSheet(workbook.getSheetAt(2), personStyleTitleName, personStyleListMap);
            ExcelOperationUtils.writeSheet(workbook.getSheetAt(3), techTitleName, techListMap);
            ExcelOperationUtils.writeSheet(workbook.getSheetAt(4), companyTitleName, companyListMap);
            OutputStream os = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=personalInfoTable.xlsx");
            workbook.write(os);
            os.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
