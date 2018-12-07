<template>
  <div class="app-container detail-container">
    <el-tabs v-model="activeTab">
      <el-tab-pane label="基本信息" name="tab1">
        <div style="height:440px">
          <el-scrollbar>
            <table class="base-information">
              <tr>
                <th>员工编号</th>
                <td>{{postForm.baseStaffInfo.employeeID}}</td>
                <th>员工姓名</th>
                <td>{{postForm.baseStaffInfo.employeeName}}</td>
                <th>性别</th>
                <td>{{postForm.baseStaffInfo.sex}}</td>
              </tr>
              <tr>
                <th>籍贯</th>
                <td>{{postForm.baseStaffInfo.nativePlace}}</td>
                <th>民族</th>
                <td>{{postForm.baseStaffInfo.nation}}</td>
                <th>政治面貌</th>
                <td>{{postForm.baseStaffInfo.political}}</td>
              </tr>
              <tr>
                <th>手机号码</th>
                <td>{{postForm.baseStaffInfo.telephone}}</td>
                <th>身份证号</th>
                <td>{{postForm.baseStaffInfo.idNumber}}</td>
                <th>软通状态</th>
                <td>{{postForm.baseStaffInfo.isoftStatus}}</td>
              </tr>
              <tr>
                <th>人员类别</th>
                <td>{{postForm.baseStaffInfo.personCategory}}</td>
                <th>是否骨干</th>
                <td>{{postForm.baseStaffInfo.ifBackBone}}</td>
                <th>是否在岸</th>
                <td>{{postForm.baseStaffInfo.ifOffShore}}</td>
              </tr>
              <tr>
                <th>办公场地</th>
                <td>{{postForm.baseStaffInfo.workPlace}}</td>
                <th>工位编号</th>
                <td>{{postForm.baseStaffInfo.workStationNumber}}</td>
                <th>合作模式</th>
                <td>{{postForm.baseStaffInfo.cooperationMode}}</td>
              </tr>
              <tr>
                <th>岗位角色</th>
                <td>{{postForm.baseStaffInfo.positionRole}}</td>
                <th>技能</th>
                <td>{{postForm.baseStaffInfo.skill}}</td>
                <th>技能方向</th>
                <td>{{postForm.baseStaffInfo.sillDirection}}</td>
              </tr>
              <tr>
                <th>软通职位</th>
                <td>{{postForm.baseStaffInfo.generalPosition}}</td>
                <th>职位生效时间</th>
                <td>{{postForm.baseStaffInfo.currentPositionDate}}</td>
                <th>直接汇报上级工号</th>
                <td>{{postForm.baseStaffInfo.directLeaderId}}</td>
              </tr>
              <tr>
                <th>直接汇报上级工号</th>
                <td>{{postForm.baseStaffInfo.directLeaderName}}</td>
                <th>成本中心</th>
                <td>{{postForm.baseStaffInfo.costCenter}}</td>
                <th>成本中心负责人</th>
                <td>{{postForm.baseStaffInfo.costCenterLeaderName}}</td>
              </tr>
              <tr>
                <th>BU</th>
                <td>{{postForm.baseStaffInfo.bu}}</td>
                <th>DU</th>
                <td>{{postForm.baseStaffInfo.du}}</td>
                <th>PDU</th>
                <td>{{postForm.baseStaffInfo.pdu}}</td>
              </tr>
              <tr>
                <th>地域</th>
                <td>{{postForm.baseStaffInfo.workPlaceArea}}</td>
                <th>毕业学校</th>
                <td>{{postForm.baseStaffInfo.graduationSchool}}</td>
                <th>毕业时间</th>
                <td>{{postForm.baseStaffInfo.graduationTime}}</td>
              </tr>
              <tr>
                <th>是否统招</th>
                <td>{{postForm.baseStaffInfo.ifUnderGraduates}}</td>
                <th>是否211</th>
                <td>{{postForm.baseStaffInfo.if211}}</td>
                <th>学历</th>
                <td>{{postForm.baseStaffInfo.education}}</td>
              </tr>
              <tr>
                <th>学位</th>
                <td>{{postForm.baseStaffInfo.degree}}</td>
                <th>是否应届生</th>
                <td>{{postForm.baseStaffInfo.ifGraduate}}</td>
                <th>专业名称</th>
                <td>{{postForm.baseStaffInfo.graduationMajor}}</td>
              </tr>
              <tr>
                <th>是否二本及以上</th>
                <td>{{postForm.baseStaffInfo.ifAbvoveTwoInstitutions}}</td>
                <th>教育模式</th>
                <td>{{postForm.baseStaffInfo.educationMode}}</td>
                <th>软通邮件</th>
                <td>{{postForm.baseStaffInfo.isoftEmai}}</td>
              </tr>
              <tr>
                <th>域用户名</th>
                <td>{{postForm.baseStaffInfo.domainUserName}}</td>
                <th>入职日期</th>
                <td>{{postForm.baseStaffInfo.employmentDate}}</td>
                <th>离职日期</th>
                <td>{{postForm.baseStaffInfo.departureDate}}</td>
              </tr>
              <tr>
                <th>当前人员状态</th>
                <td>{{postForm.baseStaffInfo.currentStatus}}</td>
                <th>项目组</th>
                <td>{{postForm.baseStaffInfo.projectTeamId}}</td>
                <th>&nbsp;</th>
                <td>&nbsp;</td>
              </tr>
            </table>
          </el-scrollbar>
        </div>
      </el-tab-pane>
      <el-tab-pane label="资质认证" name="tab2">
        <el-table :data="certificationData" style="width: 100%" stripe border size="mini">
          <el-table-column prop="qualificationName" header-align="center" label="资质名称" width="180"></el-table-column>
          <el-table-column prop="qualificationType" header-align="center" label="资质名称" width="180"></el-table-column>
          <el-table-column prop="certificationLevel" header-align="center" label="认证等级"></el-table-column>
          <el-table-column prop="passDate" header-align="center" label="通过日期"></el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="技能信息" name="tab3">
        <el-table :data="skillData" style="width: 100%" stripe border size="mini">
          <el-table-column prop="technologyPlatform" header-align="center" label="技术平台" width="180"></el-table-column>
          <el-table-column prop="technologyGrade" header-align="center" label="技术等级" width="180"></el-table-column>
          <el-table-column prop="areasOfExpertise" header-align="center" label="擅长领域"></el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="教育信息" name="tab4">
        
      </el-tab-pane>
      <el-tab-pane label="家庭信息" name="tab5">
        <table class="base-information">
          <tr>
            <th>出生年月</th>
            <td>{{postForm.familyInformations.birthdayDate}}</td>
            <th>身高</th>
            <td>{{postForm.familyInformations.heigh}}</td>
            <th>体重</th>
            <td>{{postForm.familyInformations.weight}}</td>
          </tr>
          <tr>
            <th>现居住地址</th>
            <td>{{postForm.familyInformations.currentHomeAddress}}</td>
            <th>紧急联系人</th>
            <td>{{postForm.familyInformations.emergencyContact}}</td>
            <th>紧急联系人关系</th>
            <td>{{postForm.familyInformations.emergencyContactRelation}}</td>
          </tr>
          <tr>
            <th>紧急联系人住址</th>
            <td>{{postForm.familyInformations.emergencyContactAdress}}</td>
            <th>紧急联系人电话</th>
            <td>{{postForm.familyInformations.emergencyContactPhone}}</td>
            <th>婚姻状态</th>
            <td>{{postForm.familyInformations.maritalStatus}}</td>
          </tr>
          <tr>
            <th>子女信息</th>
            <td>{{postForm.familyInformations.childInformation}}</td>
            <th>上班方式</th>
            <td>{{postForm.familyInformations.wayToWork}}</td>
            <th>上班路上时长</th>
            <td>{{postForm.familyInformations.workingHour}}</td>
          </tr>
          <tr>
            <th>是否买房</th>
            <td>{{postForm.familyInformations.isbuyHouse}}</td>
            <th>买房日期</th>
            <td>{{postForm.familyInformations.dayOfBuyHouse}}</td>
            <th>房产地址</th>
            <td>{{postForm.familyInformations.propertyAddress}}</td>
          </tr>
        </table>
      </el-tab-pane>
      <el-tab-pane label="个人风采" name="tab6">
        <table class="base-information">
          <tr>
            <th>喜欢的食物</th>
            <td>{{postForm.personalStyle.food}}</td>
            <td rowspan="8" style="width:35%;border:0"><img :src="postForm.personalStyle.photosOfLife"/></td>
          </tr>
          <tr>
            <th>喜欢的游戏</th>
            <td>{{postForm.personalStyle.game}}</td>
          </tr>
          <tr>
            <th>喜欢的运动</th>
            <td>{{postForm.personalStyle.motion}}</td>
          </tr>
          <tr>
            <th>喜欢的书籍</th>
            <td>{{postForm.personalStyle.book}}</td>
          </tr>
          <tr>
            <th>喜欢的电影</th>
            <td>{{postForm.personalStyle.movie}}</td>
          </tr>
          <tr>
            <th>喜欢的音乐</th>
            <td>{{postForm.personalStyle.music}}</td>
          </tr>
          <tr>
            <th>喜欢的一句话</th>
            <td>{{postForm.personalStyle.oneSentence}}</td>
          </tr>
          <tr>
            <th>不喜欢的</th>
            <td>{{postForm.personalStyle.dislike}}</td>
          </tr>
        </table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeTab: "tab1",
      postForm: {},
      tempRoute: {},
      certificationData: [],
      skillData: []
    };
  },
  created() {
    const id = this.$route.params && this.$route.params.id;
    this.fetchData(id);
    this.tempRoute = Object.assign({}, this.$route);
  },
  methods: {
    fetchData(id) {
      let vm = this;
      vm.$store
        .dispatch("getEmployeeInfoById", id)
        .then(res => {
          if (res.success) {
            this.postForm = res.data;
            this.certificationData = this.postForm.companyQualifications;
            this.skillData = this.postForm.technicalInformation;
            this.setTagsViewTitle();
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    setTagsViewTitle() {
      const title = "员工详情";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}-${this.postForm.baseStaffInfo.employeeName}`
      });
      this.$store.dispatch("updateVisitedView", route);
    }
  }
};
</script>
<style rel="stylesheet/scss" lang="scss">
.base-information {
  width: 100%;
  color: #606266;
  font-size: 14px;
  th {
    width: 10%;
    text-align: right;
    border-bottom: 1px solid #ebeef5;
  }
  td {
    width: 20%;
    text-align: left;
    border-bottom: 1px solid #ebeef5;
  }
  tr {
    line-height: 35px;
  }
}
.detail-container {
  .el-scrollbar {
    height: 100%;
    .el-scrollbar__wrap {
      overflow-x: hidden;
    }
  }
}
</style>
