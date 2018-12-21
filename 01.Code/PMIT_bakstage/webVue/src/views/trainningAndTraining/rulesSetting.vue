<template>
  <div class="app-container">
    <div class="filter-container" style="padding-bottom:0px">
      <el-select
        size="mini"
        v-model="searchForm.system"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="所属体系"
      >
        <el-option v-for="item in systemOptions" :key="item" :label="item" :value="item"></el-option>
      </el-select>
      <el-select
        size="mini"
        v-model="searchForm.roleName"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="所属角色"
      >
        <el-option
          v-for="item in roleOptions"
          :key="item.roleName"
          :label="item.roleName"
          :value="item.roleName"
        ></el-option>
      </el-select>
      <el-button
        class="filter-item"
        type="primary"
        size="mini"
        icon="el-icon-search"
        @click="handleFilter"
      >{{ $t("table.search") }}</el-button>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        size="mini"
        icon="el-icon-plus"
        @click="handleAdd"
      >{{ $t("table.add") }}</el-button>
    </div>
    <el-tabs @tab-click="changeTab" v-model="activeTab">
      <el-tab-pane label="关键角色设置" name="role">
        <role-tab ref="roleTab" :condition="searchForm"></role-tab>
      </el-tab-pane>
      <el-tab-pane label="考核规则设置" name="rule">
        <rule-tab ref="ruleTab" :condition="searchForm"></rule-tab>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import RoleTab from "./components/role.vue";
import RuleTab from "./components/rule.vue";
export default {
  name: "AssessmentRules",
  components: { RoleTab, RuleTab },
  data() {
    return {
      activeTab: "role",
      searchForm: {
        system: "",
        roleName: ""
      }
    };
  },
  mounted() {
    this.getSystemInfo();
    this.getRoleOption();
    this.$refs.roleTab.handleFilter();
  },
  computed: {
    ...mapState({
      roleOptions: state => state.ruleStore.roleOptions,
      systemOptions: state => state.ruleStore.systemOptions
    })
  },
  methods: {
    ...mapActions(["getRoleOption", "getSystemInfo"]),
    changeTab(tab, event) {
      if (tab.name === "role") {
        this.$refs.roleTab.handleFilter();
      } else {
        this.$refs.ruleTab.handleFilter();
      }
    },
    handleAdd() {
      if (this.activeTab === "role") {
        this.$refs.roleTab.handleAdd();
      } else {
        this.$refs.ruleTab.handleAdd();
      }
    },
    handleFilter() {
      if (this.activeTab === "role") {
        this.$refs.roleTab.handleFilter();
      } else {
        this.$refs.ruleTab.handleFilter();
      }
    }
  }
};
</script>

<style></style>
