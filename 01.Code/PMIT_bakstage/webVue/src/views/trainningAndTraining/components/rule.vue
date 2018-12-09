<template>
  <div>
    <el-table
      v-loading="loading"
      :data="tableData"
      border
      fit
      size="mini"
      stripe
      max-height="375"
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column
        header-align="center"
        align="center"
        :label="$t('table.id')"
        type="index"
        width="80"
      ></el-table-column>

      <el-table-column min-width="150px" header-align="center" label="所属体系" prop="system" sortable></el-table-column>
      <el-table-column min-width="100px" header-align="center" label="所属角色" prop="role" sortable></el-table-column>
      <el-table-column min-width="100px" header-align="center" label="职级" prop="rank" sortable></el-table-column>
      <el-table-column min-width="100px" header-align="center" label="创建人" prop="creator" sortable></el-table-column>
      <el-table-column
        min-width="150px"
        header-align="center"
        label="最后更新人"
        prop="updator"
        sortable
      ></el-table-column>
      <el-table-column
        min-width="150px"
        header-align="center"
        label="最后更新时间"
        prop="updateTime"
        sortable
      >
        <template slot-scope="scope">
          <span>{{ scope.row.updateTime | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" width="130" header-align="center" :label="$t('table.option')">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-search"
            title="查看"
            @click="handleEdit(scope.row);"
          ></el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-delete"
            title="删除"
            @click="handleDel(scope.row.ruleId);"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row type="flex" justify="end">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="page.currentPage"
        :page-size="page.pageSize"
        layout="total, slot, prev, pager, next"
        :total="page.totalRecord"
        prev-text="上一页"
        next-text="下一页"
      ></el-pagination>
    </el-row>
    <el-dialog
      :title="dialogBaseTitle"
      :visible.sync="dialogBaseVisible"
      width="70%"
      top="30px"
      :close-on-click-modal="false"
    >
      <el-steps :active="active" finish-status="success" simple>
        <el-step title="基本信息"></el-step>
        <el-step title="选择课程和事务"></el-step>
        <el-step title="生成结果"></el-step>
      </el-steps>
      <div v-if="isBaseInfo">
        <el-form :model="ruleForm" size="mini" label-width="100px" ref="ruleForm" :rules="rules">
          <el-form-item label="所属体系" prop="system">
              <el-select
                v-model="ruleForm.system"
                filterable
                allow-create
                default-first-option
                placeholder="请选择"
              >
                <el-option
                  v-for="item in systemOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="所属角色" prop="role">
              <el-select
                v-model="ruleForm.system"
                filterable
                allow-create
                default-first-option
                placeholder="请选择"
              >
                <el-option
                  v-for="item in systemOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="职级" prop="rank">
              <el-select
                v-model="ruleForm.system"
                filterable
                allow-create
                default-first-option
                placeholder="请选择"
              >
                <el-option
                  v-for="item in systemOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
        </el-form>
      </div>
      <div v-if="isSelect">
        <el-row :gutter="12" style="margin-top:18px;">
          <el-col :span="12">
            <el-card shadow="never">
              <div slot="header" class="clearfix">待选课程列表</div>
              <div class="filter-container">
                <el-input
                  v-model="searchForm.affairsName"
                  size="mini"
                  clearable
                  class="filter-item"
                  style="width: 130px"
                  placeholder="培训名称"
                ></el-input>
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
                  v-model="searchForm.system"
                  clearable
                  class="filter-item"
                  style="width: 130px"
                  placeholder="所属系列"
                >
                  <el-option
                    v-for="item in systemOptions"
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
                >{{ $t("table.search") }}</el-button>
              </div>
              <el-table></el-table>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="never">
              <div slot="header" class="clearfix">其他考核事务列表</div>
              <div class="filter-container">
                <el-input
                  v-model="searchForm.affairsName"
                  size="mini"
                  clearable
                  class="filter-item"
                  style="width: 130px"
                  placeholder="事务名称"
                ></el-input>
                <el-button
                  class="filter-item"
                  type="primary"
                  size="mini"
                  icon="el-icon-search"
                  @click="handleFilter"
                >{{ $t("table.search") }}</el-button>
              </div>
              <el-table></el-table>
            </el-card>
          </el-col>
        </el-row>
      </div>
      <div v-if="isResult">
        <el-row :gutter="12" v-if="isNext" style="margin-top:18px;">
          <el-col :span="12">
            <el-card shadow="never">
              <div slot="header" class="clearfix">单项规则设置</div>
              <el-row></el-row>
              <el-table></el-table>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="never">
              <div slot="header" class="clearfix">总分规则设置</div>
              <el-row></el-row>
              <el-table></el-table>
            </el-card>
          </el-col>
        </el-row>
      </div>  
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogBaseVisible = false;" size="mini">取 消</el-button>
        <el-button type="primary" @click="prev" size="mini">上一步</el-button>
        <el-button type="primary" @click="next" size="mini">下一步</el-button>
        <el-button type="primary" @click="handleSubmit();" size="mini">完 成</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { formatDate } from "@/utils/date";
export default {
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    }
  },
  props: ["condition"],
  data() {
    return {
      active: 0,
      isBaseInfo: true,
      isSelect: false,
      isResult: false,
      loading: false,
      tableData: [],
      initList: [],
      systemOptions: [],
      productOptions: [],
      dialogBaseTitle: "",
      dialogBaseVisible: false,
      page: {
        pageNum: 1,
        pageSize: 10,
        totalRecord: 0
      },
      ruleForm: {},
      searchForm: {},
      rules: {}
    };
  },
  methods: {
    next() {
      this.active++;
      if(this.active === 1) {
        this.isBaseInfo = false;
        this.isSelect = true;
        this.isResult = false;
      }
      if(this.active === 2) {
        this.isBaseInfo = false;
        this.isSelect = false;
        this.isResult = true;
      }
    },
    prev() {
      this.active--;
      this.isPrev = true;
      this.isNext = false;
    },
    getRuleList() {
      let vm = this;
      vm.loading = true;
      vm.$store
        .dispatch("getRuleList", vm.condition)
        .then(res => {
          if (res) {
            vm.initList = res;
            vm.tableData = vm.initList.slice(0, vm.page.pageSize);
            vm.page.totalRecord = res.length;
          } else {
            vm.tableData = [];
            vm.page.totalRecord = 0;
          }
          vm.loading = false;
        })
        .catch(() => {
          vm.tableData = [];
          vm.loading = false;
        });
    },
    getProductInfo() {
      let vm = this;
      vm.$store.dispatch("getProductInfo").then(data => {
        if (data) {
          vm.productOptions = data;
        } else {
          vm.productOptions = [];
        }
      });
    },
    getSystem() {
      let vm = this;
      vm.$store.dispatch("querySystem").then(res => {
        if (res.data) {
          vm.systemOptions = res.data.map(item => {
            return {
              label: item.system,
              value: item.system
            };
          });
        } else {
          vm.systemOptions = [];
        }
      });
    },
    handleCurrentChange(val) {
      let vm = this;
      vm.page.currentPage = val;
      vm.tableData = vm.initList.slice(
        (val - 1) * vm.page.pageSize,
        val * vm.page.pageSize
      );
    },
    handleEdit(rowData) {
      this.getProductInfo();
      this.getSystem();
      this.isEdit = true;
      this.dialogBaseTitle = "编辑考核规则";
      this.dialogBaseVisible = true;
    },
    handleDel(ruleId) {},
    handleFilter() {
      this.page.currentPage = 1;
      this.getRuleList();
    },
    handleAdd() {
      this.active = 0;
      this.getProductInfo();
      this.getSystem();
      this.isEdit = false;
      this.dialogBaseTitle = "添加考核规则";
      this.dialogBaseVisible = true;
    },
    handleSubmit() {
      if (this.active > 0) {
        this.active = 2;
      }
    }
  }
};
</script>
