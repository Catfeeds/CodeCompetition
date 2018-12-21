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
      <el-table-column min-width="100px" header-align="center" label="所属角色" prop="roleId" sortable></el-table-column>
      <el-table-column min-width="100px" header-align="center" label="职级" prop="roleLevel" sortable></el-table-column>
      <el-table-column
        min-width="100px"
        header-align="center"
        label="创建人"
        prop="creatorName"
        sortable
      ></el-table-column>
      <el-table-column
        min-width="150px"
        header-align="center"
        label="最后更新人"
        prop="updateStaffName"
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
      <el-table-column align="center" width="80" header-align="center" :label="$t('table.option')">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="mini"
            icon="el-icon-view"
            title="查看"
            @click="handleView(scope.row.ruleId);"
          ></el-button>
          <el-button
            type="text"
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
      title="添加考核规则"
      :visible.sync="dialogBaseVisible"
      width="80%"
      top="30px"
      custom-class="rule-setting"
      :close-on-click-modal="false"
    >
      <el-steps :active="active" finish-status="success" simple>
        <el-step title="基本信息"></el-step>
        <el-step title="选择课程和事务"></el-step>
        <el-step title="生成结果"></el-step>
      </el-steps>
      <div v-if="isBaseInfo" style="margin-top:10px;">
        <el-form
          :model="ruleForm"
          size="mini"
          label-width="100px"
          ref="ruleForm"
          :rules="rules"
          inline
        >
          <el-form-item label="职级" prop="rank">
            <el-select
              v-model="ruleForm.rank"
              filterable
              allow-create
              placeholder="请选择/输入"
              style="width:130px;"
            >
              <el-option v-for="item in rankOptions" :key="item" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="考核角色" prop="role">
            <div style="height:280px; width:300px; border: 1px solid #dcdfe6;">
              <el-scrollbar>
                <el-tree
                  style="300px"
                  aria-required
                  :data="roleTreeData"
                  show-checkbox
                  node-key="id"
                  default-expand-all
                  :props="defaultProps"
                  :default-checked-keys="selectedNodes"
                  ref="tree"
                ></el-tree>
              </el-scrollbar>
            </div>
          </el-form-item>
        </el-form>
      </div>
      <div v-if="isSelect">
        <el-row :gutter="12" style="margin-top:5px;">
          <el-col :span="14">
            <el-card shadow="never">
              <div slot="header" class="clearfix">待选课程列表</div>
              <div class="filter-container" style="padding-bottom:0px;">
                <el-input
                  v-model="searchForm.trainName"
                  size="mini"
                  clearable
                  class="filter-item"
                  style="width: 130px"
                  placeholder="培训名称"
                ></el-input>
                <el-select
                  size="mini"
                  v-model="searchForm.series"
                  clearable
                  class="filter-item"
                  style="width: 130px"
                  placeholder="所属系列"
                >
                  <el-option v-for="item in seriesOptions" :key="item" :label="item" :value="item"></el-option>
                </el-select>
                <el-button
                  class="filter-item"
                  type="primary"
                  size="mini"
                  icon="el-icon-search"
                  @click="handleTrainFilter"
                >{{ $t("table.search") }}</el-button>
              </div>
              <el-table
                ref="trainTable"
                :data="trainTableData"
                border
                fit
                size="mini"
                stripe
                height="215"
                tooltip-effect="dark"
                @selection-change="handleTrainSelectionChange"
                style="width: 100%;"
              >
                <el-table-column type="selection" header-align="center" align="center" width="45"></el-table-column>
                <el-table-column
                  prop="trainingName"
                  header-align="center"
                  label="培训名称"
                  min-width="130"
                ></el-table-column>
                <el-table-column prop="sort" header-align="center" label="所属体系" width="110"></el-table-column>
                <el-table-column prop="series" header-align="center" label="所属系列" width="100"></el-table-column>
                <el-table-column prop="classType" header-align="center" label="课程类型" width="90"></el-table-column>
                <el-table-column prop="type" header-align="center" label="培训类型" width="90"></el-table-column>
              </el-table>
            </el-card>
          </el-col>
          <el-col :span="10">
            <el-card shadow="never">
              <div slot="header" class="clearfix">其他考核事务列表</div>
              <div class="filter-container" style="padding-bottom:0px;">
                <el-input
                  v-model="searchForm.affairsName"
                  size="mini"
                  clearable
                  class="filter-item"
                  style="width: 130px"
                  placeholder="事务名称"
                ></el-input>
                <el-select
                  size="mini"
                  v-model="searchForm.affairSeries"
                  clearable
                  class="filter-item"
                  style="width: 130px"
                  placeholder="所属系列"
                >
                  <el-option v-for="item in seriesOptions" :key="item" :label="item" :value="item"></el-option>
                </el-select>
                <el-button
                  class="filter-item"
                  type="primary"
                  size="mini"
                  icon="el-icon-search"
                  @click="handleAffairFilter"
                >{{ $t("table.search") }}</el-button>
              </div>
              <el-table
                ref="affairTable"
                :data="affairTableData"
                border
                fit
                size="mini"
                stripe
                height="215"
                tooltip-effect="dark"
                @selection-change="handleAffairSelectionChange"
                style="width: 100%;"
              >
                <el-table-column type="selection" header-align="center" align="center" width="45"></el-table-column>
                <el-table-column
                  prop="affairName"
                  header-align="center"
                  label="事务名称"
                  min-width="130"
                ></el-table-column>
                <el-table-column prop="system" header-align="center" label="所属体系" width="100"></el-table-column>
                <el-table-column prop="series" header-align="center" label="所属系列" width="110"></el-table-column>
              </el-table>
            </el-card>
          </el-col>
        </el-row>
      </div>
      <div v-if="isResult" class="score">
        <el-form :model="tableForm" ref="tableForm" inline-message size="mini">
          <el-row :gutter="12" style="margin-top:10px;">
            <el-col :span="12">
              <el-card shadow="never">
                <div slot="header" class="clearfix">单项得分校验规则设置</div>
                <el-table
                  :data="tableForm.ruleDataSource"
                  border
                  fit
                  size="mini"
                  stripe
                  height="255"
                  tooltip-effect="dark"
                  style="width: 100%;"
                >
                  <el-table-column
                    header-align="center"
                    align="center"
                    :label="$t('table.id')"
                    width="50"
                    type="index"
                  ></el-table-column>
                  <el-table-column prop="trainName" header-align="center" label="培训名称" width="150"></el-table-column>
                  <el-table-column prop="credit" header-align="center" label="最高得分" min-width="130">
                    <template slot-scope="scope">
                      <el-form-item style="margin-bottom:0px;" v-if="scope.row.trainId<0">
                        <el-input
                          v-model="scope.row.credit"
                          size="mini"
                          disabled
                          style="width: 130px"
                        ></el-input>
                      </el-form-item>
                      <el-form-item
                        :prop="'ruleDataSource.'+scope.$index+'.credit'"
                        :rules="rules.score"
                        style="margin-bottom:0px;"
                        v-else
                      >
                        <el-input
                          v-model="scope.row.credit"
                          size="mini"
                          clearable
                          style="width: 130px"
                          placeholder="请输入最高得分"
                        ></el-input>
                      </el-form-item>
                    </template>
                  </el-table-column>
                </el-table>
              </el-card>
            </el-col>
            <el-col :span="12">
              <el-card shadow="never">
                <div slot="header" class="clearfix">总分规则设置</div>
                <el-table
                  :data="tableForm.sumDataSource"
                  border
                  fit
                  size="mini"
                  stripe
                  height="255"
                  tooltip-effect="dark"
                  style="width: 100%;"
                >
                  <el-table-column
                    prop="trainName"
                    header-align="center"
                    align="center"
                    label="考核项"
                    width="100"
                  ></el-table-column>
                  <el-table-column prop="itemType" header-align="center" label="条件" width="130">
                    <template slot-scope="scope">
                      <el-form-item style="margin-bottom:0px;">
                        <el-select
                          v-model="scope.row.itemType"
                          v-if="scope.$index<1"
                          style="width:100px;"
                          @change="changeCondition(scope.row.itemType)"
                        >
                          <el-option
                            v-for="item in passOptions"
                            :key="item"
                            :label="item"
                            :value="item"
                          ></el-option>
                        </el-select>
                        <el-select v-model="scope.row.itemType" v-else style="width:100px;">
                          <el-option
                            v-for="item in distinctionOptions"
                            :key="item"
                            :label="item"
                            :value="item"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </template>
                  </el-table-column>
                  <el-table-column prop="credit" header-align="center" label="分数" min-width="150">
                    <template slot-scope="scope">
                      <el-form-item
                        :prop="'sumDataSource.'+scope.$index+'.credit'"
                        :rules="rules.sumScore"
                        style="margin-bottom:0px;"
                      >
                        <el-input
                          v-model="scope.row.credit"
                          size="mini"
                          clearable
                          style="width: 120px"
                          placeholder="请输入分数"
                        ></el-input>
                      </el-form-item>
                    </template>
                  </el-table-column>
                </el-table>
              </el-card>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogBaseVisible = false;" size="mini">取 消</el-button>
        <el-button type="primary" @click="prev" v-if="isSelect||isResult" size="mini">上一步</el-button>
        <el-button type="primary" @click="next" v-if="isBaseInfo || isSelect" size="mini">下一步</el-button>
        <el-button type="primary" @click="handleSubmit();" v-if="isResult" size="mini">完 成</el-button>
      </div>
    </el-dialog>
    <el-dialog title="查看考核规则" :visible.sync="dialogViewVisible" width="50%">
      <el-table
        :data="ruleList"
        border
        fit
        size="mini"
        stripe
        max-height="185"
        tooltip-effect="dark"
        highlight-current-row
        style="width: 100%;"
      >
        <el-table-column
          header-align="center"
          align="center"
          :label="$t('table.id')"
          width="80"
          type="index"
        ></el-table-column>
        <el-table-column prop="itemName" header-align="center" label="培训名称" width="150"></el-table-column>
        <el-table-column prop="credit" header-align="center" label="学分" width="100"></el-table-column>
        <el-table-column prop="pass" header-align="center" label="及格分" show-overflow-tooltip></el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogViewVisible = false;" size="mini">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { mapState, mapActions, mapGetters } from "vuex";
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
    let vm = this;
    let validScore = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入最高得分"));
      }
      if (isNaN(value)) {
        return callback(new Error("只能输入整数和小数"));
      }
      if (Number(value) > 100 || Number(value) < 0) {
        return callback(new Error("只能0~100的整数和小数"));
      }
      if (rule.field.indexOf(".credit") >= 0) {
        let sum = 0;
        let lth = vm.tableForm.ruleDataSource.length - 1;
        vm.tableForm.ruleDataSource.map((item, index) => {
          if (index !== lth && !isNaN(item.credit)) {
            sum += Number(item.credit);
          }
        });
        vm.tableForm.ruleDataSource[lth].credit = sum;
      }
      return callback();
    };
    let validSumScore = (rule, value, callback) => {
      if (
        vm.tableForm.sumDataSource.filter(item => !!item.credit).length <= 0
      ) {
        return callback(new Error("至少输入一项考核分数"));
      } else {
        return callback();
      }
      if (isNaN(value)) {
        return callback(new Error("只能输入整数和小数"));
      }
      if (Number(value) > 100 || Number(value) < 0) {
        return callback(new Error("只能0~100的整数和小数"));
      }
    };
    let validMenuTree = (rule, value, callback) => {
      vm.ruleForm.role = vm.$refs.tree
        .getCheckedNodes(false, true)
        .filter(item => !item.children)
        .map(item => item.id);
      vm.ruleForm.system = vm.$refs.tree
        .getCheckedNodes(false, true)
        .filter(item => !!item.children)
        .map(item => item.name);
      if (vm.ruleForm.role.length <= 0) {
        callback(new Error(rule.message));
      } else {
        callback();
      }
    };
    return {
      active: 0,
      isBaseInfo: true,
      isSelect: false,
      isResult: false,
      isInit: true,
      loading: false,
      tableData: [],
      initList: [],
      ruleList: [],
      trainTableData: [],
      affairTableData: [],
      rankOptions: [],
      selectedTrain: [],
      selectedNodes: [],
      selectedAffair: [],
      passOptions: ["<", "≤", ">", "≥"],
      distinctionOptions: ["<", "≤", ">", "≥"],
      dialogBaseVisible: false,
      dialogViewVisible: false,
      defaultProps: {
        children: "children",
        label: "name"
      },
      page: {
        pageNum: 1,
        pageSize: 10,
        totalRecord: 0
      },
      ruleForm: {
        system: "",
        role: "",
        rank: ""
      },
      searchForm: {
        affairsName: "",
        series: "",
        trainName: "",
        affairSeries: ""
      },
      tableForm: {
        ruleDataSource: [],
        sumDataSource: []
      },
      rules: {
        role: [
          {
            required: true,
            message: "请选择需要考核的角色",
            validator: validMenuTree,
            trigger: "change"
          }
        ],
        rank: [
          { required: true, message: "请选择/输入职级", trigger: "change" }
        ],
        score: [{ required: true, validator: validScore, trigger: "blur" }],
        sumScore: [{ validator: validSumScore, trigger: "blur" }]
      }
    };
  },
  watch: {
    trainDataSource(data) {
      if (data) {
        this.trainTableData = data.filter(
          item => this.ruleForm.system.indexOf(item.sort) >= 0
        );
      } else {
        this.trainTableData = [];
      }
    },
    affairDataSource(data) {
      if (data) {
        this.affairTableData = data.filter(
          item => this.ruleForm.system.indexOf(item.system) >= 0
        );
      } else {
        this.affairTableData = [];
      }
    }
  },
  computed: {
    ...mapGetters(["employeeId", "employeeName"]),
    ...mapState({
      roleTreeData: state => state.ruleStore.roleTreeData,
      seriesOptions: state => state.ruleStore.seriesOptions,
      trainDataSource: state => state.ruleStore.trainDataSource,
      affairDataSource: state => state.ruleStore.affairDataSource
    })
  },
  methods: {
    ...mapActions([
      "getAllRole",
      "getSeriesInfo",
      "getAllTrain",
      "getAllAffair"
    ]),
    next() {
      let vm = this;
      if (vm.isBaseInfo) {
        vm.getTrainInfo();
        vm.getAffairInfo();
        vm.$refs.ruleForm.validate(valid => {
          if (valid) {
            vm.active++;
            vm.isSelect = vm.active === 1;
            vm.isResult = vm.active === 2;
            vm.isBaseInfo = false;
          } else {
            return false;
          }
        });
      } else if (vm.isSelect) {
        if (vm.selectedTrain.length <= 0 && vm.selectedAffair.length <= 0) {
          vm.$message.warning("培训课程和考核事务没有选择");
          return;
        }
        vm.active++;
        vm.isSelect = vm.active === 1;
        vm.isResult = vm.active === 2;
        vm.isBaseInfo = false;
      }
      if (vm.isResult) {
        vm.tableForm.ruleDataSource = vm.selectedTrain
          .concat(vm.selectedAffair)
          .map(item => {
            return {
              trainId: item.trainId,
              trainName: item.trainName,
              itemType: item.itemType,
              credit: ""
            };
          });
        vm.tableForm.ruleDataSource.push({
          trainId: -1,
          trainName: "总分",
          itemType: -1,
          credit: ""
        });
        vm.tableForm.sumDataSource = [
          {
            trainId: 1,
            trainName: "及格",
            itemType: vm.passOptions[3],
            credit: ""
          },
          {
            trainId: 2,
            trainName: "卓越",
            itemType: vm.distinctionOptions[3],
            credit: ""
          }
        ];
      }
      if (vm.isSelect) {
        setTimeout(() => {
          if (vm.selectedTrain.length > 0) {
            vm.toggleSelection(vm.selectedTrain, "trainTable");
          }
          if (vm.selectedAffair.length > 0) {
            vm.toggleSelection(vm.selectedAffair, "affairTable");
          }
        }, 200);
      }
    },
    prev() {
      let vm = this;
      vm.active--;
      vm.isBaseInfo = vm.active === 0;
      vm.isSelect = vm.active === 1;
      vm.isResult = false;
      if (vm.isSelect) {
        setTimeout(() => {
          if (vm.selectedTrain.length > 0) {
            vm.toggleSelection(vm.selectedTrain, "trainTable");
          }
          if (vm.selectedAffair.length > 0) {
            vm.toggleSelection(vm.selectedAffair, "affairTable");
          }
        }, 200);
      }
      if (vm.isBaseInfo) {
        vm.selectedNodes = vm.ruleForm.role;
      }
    },
    toggleSelection(rows, tableName) {
      let vm = this;
      if (rows) {
        rows.forEach(row => {
          vm.$refs[tableName].toggleRowSelection(row);
        });
      }
    },
    getTrainInfo() {
      this.getAllTrain({
        bu: "",
        series: this.searchForm.series,
        sort: "",
        trainingName: this.searchForm.trainName,
        classType: ""
      });
    },
    getAffairInfo() {
      this.getAllAffair({
        bu: "",
        series: this.searchForm.affairSeries,
        system: "",
        affairName: this.searchForm.affairsName
      });
    },
    getRuleList() {
      let vm = this;
      vm.loading = true;
      vm.$store
        .dispatch("getRuleList", vm.condition)
        .then(res => {
          if (res.success) {
            vm.initList = res.data;
            vm.rankOptions = Array.from(
              new Set(res.data.map(item => item.roleLevel))
            );
            vm.tableData = vm.initList.slice(0, vm.page.pageSize);
            vm.page.totalRecord = res.data.length;
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
    handleCurrentChange(val) {
      let vm = this;
      vm.page.currentPage = val;
      vm.tableData = vm.initList.slice(
        (val - 1) * vm.page.pageSize,
        val * vm.page.pageSize
      );
    },
    handleView(ruleId) {
      let vm = this;
      vm.dialogViewVisible = true;
      vm.$store
        .dispatch("getRuleInfoById", ruleId)
        .then(res => {
          if (res.success) {
            vm.ruleList = res.data;
          } else {
            vm.ruleList = [];
          }
        })
        .catch(() => {
          vm.ruleList = [];
        });
    },
    handleDel(ruleId) {
      let vm = this;
      vm.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        vm.$store.dispatch("delRuleInfo", ruleId).then(res => {
          if (res.success) {
            vm.$message.success("操作成功");
            vm.getRuleList();
          } else {
            vm.$message.error("操作失败");
          }
        });
      });
    },
    handleFilter() {
      this.page.currentPage = 1;
      this.getRuleList();
    },
    handleTrainFilter() {
      this.getTrainInfo();
    },
    handleAffairFilter() {
      this.getAffairInfo();
    },
    handleAdd() {
      let vm = this;
      vm.searchForm.trainName = "";
      vm.searchForm.system = "";
      vm.searchForm.series = "";
      vm.searchForm.affairsName = "";
      vm.searchForm.affairSystem = "";
      vm.ruleForm.system = "";
      vm.ruleForm.role = "";
      vm.ruleForm.rank = "";
      vm.getSeriesInfo();
      vm.active = 0;
      vm.isBaseInfo = true;
      vm.isSelect = false;
      vm.isResult = false;
      vm.isEdit = false;
      vm.dialogBaseVisible = true;
    },
    handleSubmit() {
      let vm = this;
      vm.$refs.tableForm.validate(valid => {
        if (valid) {
          if (vm.active > 0) {
            vm.active = 3;
          }
          let condition = {
            ">": 0,
            "≥": 1,
            "<": 2,
            "≤": 3
          };
          let formData = {
            system: vm.ruleForm.system[0],
            oneRule: {
              ruleId: -1,
              roleId: vm.ruleForm.role[0],
              roleLevel: vm.ruleForm.rank,
              creatorId: vm.employeeId,
              creatorName: vm.employeeName
            },
            singleRuleInfo: vm.tableForm.ruleDataSource
              .filter(item => {
                return !(item.trainId < 0);
              })
              .map(item => {
                return {
                  itemId: item.trainId,
                  itemType: item.itemType,
                  itemName: item.trainName,
                  credit: item.credit * 1
                };
              })
          };
          if (!!vm.tableForm.sumDataSource[0].credit) {
            formData.oneRule.passScore =
              vm.tableForm.sumDataSource[0].credit * 1;
            formData.oneRule.passRule =
              condition[vm.tableForm.sumDataSource[0].itemType];
          }
          if (!!vm.tableForm.sumDataSource[1].credit) {
            formData.oneRule.excellentScore =
              vm.tableForm.sumDataSource[1].credit * 1;
            formData.oneRule.excellentRule =
              condition[vm.tableForm.sumDataSource[1].itemType];
          }
          vm.$store.dispatch("addRuleInfo", formData).then(res => {
            if (res.success) {
              vm.$message.success("规则添加成功");
              vm.getRuleList();
            } else {
              vm.$message.success("规则添加失败");
            }
            vm.selectedTrain = [];
            vm.selectedAffair = [];
            vm.dialogBaseVisible = false;
          });
        } else {
          return false;
        }
      });
    },
    handleTrainSelectionChange(selection) {
      this.isInit = false;
      this.selectedTrain = selection.map(item => {
        item.trainId = item.trainingId;
        item.trainName = item.trainingName;
        item.itemType = 0;
        return item;
      });
    },
    handleAffairSelectionChange(selection) {
      this.isInit = false;
      this.selectedAffair = selection.map(item => {
        item.trainId = item.affairID;
        item.trainName = item.affairName;
        item.itemType = 1;
        return item;
      });
    },
    changeCondition(condition) {
      if (["<", "≤"].indexOf(condition) >= 0) {
        this.distinctionOptions = ["<", "≤"];
      } else {
        this.distinctionOptions = [">", "≥"];
      }
      this.tableForm.sumDataSource[1].itemType = this.distinctionOptions[0];
    }
  }
};
</script>
<style rel="stylesheet/scss" lang="scss">
.rule-setting {
  .el-dialog__body {
    height: 400px;
    .ruleForm-condition {
      .el-form-item--mini.el-form-item {
        margin-bottom: 10px;
      }
    }
  }
}
</style>

