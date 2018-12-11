<template>
  <div class="app-container team-settings">
    <el-tabs @tab-click="changeTab" v-model="activeTab">
      <el-tab-pane label="人员设置" name="employee">
        <emp-settings :teamInfo="postForm" ref="employeeSetting"></emp-settings>
      </el-tab-pane>
      <el-tab-pane label="关联PO" name="PO">
        <associated-p-o ref="associatedPO" :teamInfo="postForm"></associated-p-o>
      </el-tab-pane>
      <el-tab-pane label="关键角色" name="keyRole">
        <key-role ref="keyRole" :teamInfo="postForm"></key-role>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import empSettings from "./components/employeeSettings.vue";
import associatedPO from "./components/associatedPO.vue";
import keyRole from "./components/keyRole.vue";

export default {
  name: "teamSettings",
  components: {
    empSettings,
    associatedPO,
    keyRole
  },
  created() {
    const id = this.$route.params && this.$route.params.id;
    this.fetchData(id);
    this.tempRoute = Object.assign({}, this.$route);
  },
  data() {
    return {
      postForm: "",
      activeTab: "employee"
    };
  },
  methods: {
    fetchData(id) {
      let vm = this;
      vm.$store
        .dispatch("getProjectGroupInfoById", id)
        .then(res => {
          if (res.success) {
            this.postForm = res.data.datas[0];
            this.setTagsViewTitle();
            this.$refs.employeeSetting.getAreaAndCuBycondition(
              this.postForm.pdu
            );
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    setTagsViewTitle() {
      const title = "团队设置";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}-${this.postForm.projectName}`
      });
      this.$store.dispatch("updateVisitedView", route);
    },
    changeTab(tab) {
      if(tab.name === "PO") {
        this.$refs.associatedPO.handleFilter(null, this.teamInfo);
      }
      if (tab.name === "keyRole") {
        this.$refs.keyRole.getPOList({
          teamId: this.postForm.projectID,
          startDate: "",
          endDate: ""
        });
      }
    }
  }
};
</script>

<style>
</style>
