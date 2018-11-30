<template>
  <div class="app-container">
    <el-form>
      <el-row>
        <el-col :span="6">
          <el-form-item label="所属体系">
            <el-select v-model="series" size="mini" placeholder="请选择">
              <el-option
                v-for="item in seriesOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="所属角色">
            <el-select v-model="role" size="mini" placeholder="请选择">
              <el-option
                v-for="item in roleOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="所属产品线">
            <el-select v-model="product" size="mini" placeholder="请选择">
              <el-option
                v-for="item in productOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item>
            <el-button type="primary" size="mini" @click="onSubmit">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-tabs @tab-click="handleClick" v-model="activeTab">
      <el-tab-pane label="关键角色设置" name="first">
        <role-tab></role-tab>
      </el-tab-pane>
      <el-tab-pane label="考核规则设置" name="second">
        <rule-tab></rule-tab>
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
      activeTab: "first",
      series: "",
      seriesOptions: [],
      role: "",
      roleOptions: [],
      product: "",
      productOptions: [],
      roleTab: {
        loading: false,
        tableData: []
      }
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    onSubmit() {},
    handleAdd() {}
  }
};
</script>

<style>
</style>



