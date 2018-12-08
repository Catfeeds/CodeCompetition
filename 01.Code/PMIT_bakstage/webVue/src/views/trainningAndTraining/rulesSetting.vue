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
        <el-option
          v-for="item in systemOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
      <el-select
        size="mini"
        v-model="searchForm.role"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="所属角色"
      >
        <el-option
          v-for="item in roleOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
      <el-button
        class="filter-item"
        type="primary"
        size="mini"
        icon="el-icon-search"
        @click="handleFilter"
        >{{ $t("table.search") }}</el-button
      >
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        size="mini"
        icon="el-icon-plus"
        @click="handleAdd"
        >{{ $t("table.add") }}</el-button
      >
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
import RoleTab from "./components/role.vue";
import RuleTab from "./components/rule.vue";
export default {
  name: "AssessmentRules",
  components: { RoleTab, RuleTab },
  data() {
    return {
      activeTab: "role",
      systemOptions: [],
      roleOptions: [],
      productOptions: [],
      searchForm: {
        system: "",
        role: ""
      }
    };
  },
  mounted() {
    this.getSystem();
    this.$refs.roleTab.handleFilter();
  },
  methods: {
    getSystem() {
      let vm = this;
      vm.$store.dispatch("querySystem").then(res => {
        if (res.data) {
          vm.systemOptions = res.data.map(item => {
            return {
              label: item,
              value: item
            };
          });
        } else {
          vm.systemOptions = [];
        }
      });
    },
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
