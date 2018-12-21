<template>
  <div class="app-container">
    <el-tabs v-model="activeTab" @tab-click="changeTab">
      <el-tab-pane label="我的待办" name="tab1">
        <el-row type="flex" justify="end" style="margin-bottom:10px;">
          <el-button
            type="primary"
            icon="el-icon-circle-plus-outline"
            size="mini"
            @click="collpseAll"
          >全部折叠</el-button>
          <el-button
            type="primary"
            icon="el-icon-remove-outline"
            size="mini"
            @click="expandAll"
          >全部展开</el-button>
        </el-row>
        <el-collapse v-model="activeNames">
          <el-collapse-item :title="evaluateTitle" name="evaluation">
            <el-table
              :data="evaluationTable"
              border
              fit
              size="mini"
              stripe
              max-height="220"
              highlight-current-row
              style="width: 100%;"
            >
              <el-table-column
                header-align="center"
                align="center"
                :label="$t('table.id')"
                width="50"
                type="index"
              ></el-table-column>

              <el-table-column
                width="150px"
                header-align="center"
                label="评价名称"
                prop="afairName"
                sortable
              ></el-table-column>

              <el-table-column
                width="150px"
                header-align="center"
                label="所属系列"
                prop="series"
                sortable
              ></el-table-column>
              <el-table-column
                min-width="120"
                header-align="center"
                label="评价对象姓名"
                sortable
                prop="staffName"
              ></el-table-column>
              <el-table-column
                min-width="80"
                header-align="center"
                label="评价对象工号"
                sortable
                prop="staffId"
              ></el-table-column>
              <el-table-column
                min-width="110"
                header-align="center"
                label="部门名称"
                sortable
                prop="du"
              ></el-table-column>
              <el-table-column
                align="center"
                :label="$t('table.option')"
                width="50"
                header-align="center"
              >
                <template slot-scope="scope">
                  <el-button
                    type="text"
                    size="mini"
                    icon="el-icon-edit-outline"
                    title="处理"
                    @click="handleProcess(scope.row);"
                  ></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-collapse-item>
          <el-collapse-item title="我的培训" name="training"></el-collapse-item>
          <el-collapse-item title="我的考试" name="examination"></el-collapse-item>
        </el-collapse>
      </el-tab-pane>
      <el-tab-pane label="历史记录" name="tab2">
        <el-row type="flex" justify="end" style="margin-bottom:10px;">
          <el-button
            type="primary"
            icon="el-icon-circle-plus-outline"
            size="mini"
            @click="collpseHistoryAll"
          >全部折叠</el-button>
          <el-button
            type="primary"
            icon="el-icon-remove-outline"
            size="mini"
            @click="expandHistoryAll"
          >全部展开</el-button>
        </el-row>
        <el-collapse v-model="activeHistoryNames">
          <el-collapse-item :title="historyEvaluateTitle" name="evaluation">
            <el-table
              :data="historyEvaluationTable"
              border
              fit
              size="mini"
              stripe
              max-height="220"
              highlight-current-row
              style="width: 100%;"
            >
              <el-table-column
                header-align="center"
                align="center"
                :label="$t('table.id')"
                width="50"
                type="index"
              ></el-table-column>

              <el-table-column
                width="150px"
                header-align="center"
                label="评价名称"
                prop="affairName"
                sortable
              >
                <template slot-scope="scope">
                  <el-popover placement="right-end" title="考核维度评价详情" width="600" trigger="hover">
                    <el-table
                      :data="scope.row.personalAffairdimensionList"
                      border
                      fit
                      size="mini"
                      stripe
                      max-height="400"
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
                      <el-table-column
                        prop="dimensionName"
                        header-align="center"
                        label="考核维度"
                        width="150"
                      ></el-table-column>
                      <el-table-column prop="score" header-align="center" label="得分" width="100"></el-table-column>
                      <el-table-column
                        prop="evaluation"
                        header-align="center"
                        label="评价"
                        width="100"
                      ></el-table-column>
                      <el-table-column
                        prop="explanation"
                        header-align="center"
                        label="考核点说明"
                        show-overflow-tooltip
                      ></el-table-column>
                    </el-table>
                    <el-button type="text" slot="reference">{{scope.row.affairName}}</el-button>
                  </el-popover>
                </template>
              </el-table-column>

              <el-table-column
                width="150px"
                header-align="center"
                label="所属系列"
                prop="series"
                sortable
              ></el-table-column>
              <el-table-column
                min-width="120"
                header-align="center"
                label="评价对象姓名"
                sortable
                prop="staffName"
              ></el-table-column>
              <el-table-column
                min-width="80"
                header-align="center"
                label="评价对象工号"
                sortable
                prop="staffId"
              ></el-table-column>
              <el-table-column min-width="110" header-align="center" label="部门" sortable prop="du"></el-table-column>
              <el-table-column
                min-width="110"
                header-align="center"
                label="评价时间"
                sortable
                prop="changeTime"
              >
                <template slot-scope="scope">
                  <span>{{ scope.row.changeTime | formatDate }}</span>
                </template>
              </el-table-column>
              <el-table-column width="80" header-align="center" label="得分" sortable prop="sumScore"></el-table-column>
              <el-table-column
                align="center"
                :label="$t('table.option')"
                width="50"
                header-align="center"
              >
                <template slot-scope="scope">
                  <el-button
                    type="text"
                    size="mini"
                    icon="el-icon-edit"
                    title="编辑"
                    @click="handleEdit(scope.row);"
                  ></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-collapse-item>
          <el-collapse-item title="我的培训" name="training"></el-collapse-item>
          <el-collapse-item title="我的考试" name="examination"></el-collapse-item>
        </el-collapse>
      </el-tab-pane>
      <el-tab-pane label="我的成绩" name="tab3">
        <el-table
          :data="dataTable"
          border
          fit
          size="mini"
          stripe
          max-height="420"
          highlight-current-row
          style="width: 100%;"
        >
          <el-table-column
            header-align="center"
            align="center"
            :label="$t('table.id')"
            width="50"
            type="index"
          ></el-table-column>

          <el-table-column width="100px" header-align="center" label="软通工号" prop="employeeID"></el-table-column>

          <el-table-column width="100px" header-align="center" label="员工姓名" prop="employeeName"></el-table-column>
          <el-table-column width="90px" header-align="center" label="属性" sortable prop="types"></el-table-column>
          <el-table-column
            min-width="90"
            header-align="center"
            label="得分时间"
            sortable
            prop="scoreTime"
          >
            <template slot-scope="scope">
              <span>{{ scope.row.scoreTime | formatDate }}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="80"
            header-align="center"
            label="名称"
            sortable
            prop="affairName"
          ></el-table-column>
          <el-table-column width="110" header-align="center" label="所属系列" sortable prop="series"></el-table-column>
          <el-table-column width="120" header-align="center" label="所属产品线" prop="bu"></el-table-column>
          <el-table-column min-width="70" header-align="center" label="得分" sortable prop="score"></el-table-column>
          <el-table-column min-width="80" header-align="center" label="成本中心" prop="costCenter"></el-table-column>
          <el-table-column width="110" header-align="center" label="最后修改人" prop="modifier"></el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
    <el-dialog :visible.sync="dialogSetVisible" width="70%" :close-on-click-modal="false">
      <el-form :model="dimensionForm" size="mini" ref="dimensionForm">
        <el-row>{{dimensionForm.affairName}}</el-row>
        <el-row style="font-size:12px;margin:10px 0px;">
          <el-col :span="16">被评价人:{{dimensionForm.evaluator}}</el-col>
          <el-col :span="8">评价日期:{{dimensionForm.evaluateDate}}</el-col>
        </el-row>

        <el-table
          ref="multipleTable"
          :data="dimensionForm.dimensionList"
          border
          fit
          size="mini"
          stripe
          max-height="285"
          tooltip-effect="dark"
          highlight-current-row
          style="width: 100%;"
        >
          <el-table-column
            header-align="center"
            align="center"
            :label="$t('table.id')"
            width="50"
            type="index"
          ></el-table-column>
          <el-table-column prop="dimension" header-align="center" label="考核维度" width="150"></el-table-column>
          <el-table-column prop="score" header-align="center" label="得分" width="150">
            <template slot-scope="scope">
              <el-form-item :prop="'dimensionList.'+scope.$index+'.score'" :rules="rules.score">
                <el-input v-model="scope.row.score" autocomplete="off" maxlength="5"></el-input>
              </el-form-item>
            </template>
          </el-table-column>
          <el-table-column prop="evaluate" header-align="center" label="评价" min-width="120">
            <template slot-scope="scope">
              <el-form-item>
                <el-input v-model="scope.row.evaluate" autocomplete="off" maxlength="128"></el-input>
              </el-form-item>
            </template>
          </el-table-column>
          <el-table-column
            min-width="100"
            prop="explanation"
            header-align="center"
            label="考核点说明"
            show-overflow-tooltip
          ></el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSave();" size="mini">保 存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapState } from "vuex";
import { formatDate } from "@/utils/date";
export default {
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    }
  },
  data() {
    let vm = this;
    let validScore = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入得分"));
      }
      if (isNaN(value)) {
        return callback(new Error("只能输入整数和小数"));
      }
      let index = rule.field.split(".")[1] * 1;
      let maxScore = vm.dimensionForm.dimensionList[index].mark;
      if (Number(value) > maxScore || Number(value) < 0) {
        return callback(new Error("只能0~" + maxScore + "的整数和小数"));
      }
      return callback();
    };
    return {
      activeTab: "tab1",
      activeNames: [],
      activeHistoryNames: [],
      dataTable: [],
      dialogSetVisible: false,
      evaluateTitle: "待我评价",
      historyEvaluateTitle: "我的评价",
      dimensionForm: {
        dimensionList: [],
        evaluator: "",
        affairName: "",
        evaluateDate: ""
      },
      rules: {
        score: [{ required: true, validator: validScore, trigger: "blur" }]
      }
    };
  },
  mounted() {
    this.getScoreInfo();
    this.getEvaluateListData(this.employeeId);
  },
  computed: {
    ...mapGetters(["employeeId", "employeeName"]),
    ...mapState({
      evaluationTable: state => state.personalCenterStore.evaluateList,
      historyEvaluationTable: state =>
        state.personalCenterStore.historyEvaluateList
    })
  },
  watch: {
    evaluationTable(data) {
      this.evaluateTitle = "待我评价(" + data.length + ")";
    },
    historyEvaluationTable(data) {
      this.historyEvaluateTitle = "我的评价(" + data.length + ")";
    }
  },
  methods: {
    ...mapActions([
      "getEvaluateListData",
      "getHistoryEvaluateList",
      "getEvaluateCount"
    ]),
    changeTab(tab) {
      if (tab.name === "tab2") {
        this.getHistoryEvaluateList(this.employeeId);
      }
      if (tab.name === "tab1") {
        this.getEvaluateListData(this.employeeId);
      }
    },
    collpseAll() {
      this.activeNames = [];
    },
    expandAll() {
      this.activeNames = ["evaluation", "training", "examination"];
    },
    expandHistoryAll() {
      this.activeHistoryNames = ["evaluation", "training", "examination"];
    },
    collpseHistoryAll() {
      this.activeHistoryNames = [];
    },
    handleProcess(row) {
      let vm = this;
      let param = {
        affairId: row.affairId,
        employeeId: row.staffId
      };
      vm.dimensionForm.affairId = row.affairId;
      vm.dimensionForm.staffId = row.staffId;
      vm.dimensionForm.affairName = row.afairName;
      vm.dimensionForm.evaluator = row.staffName;
      vm.dimensionForm.evaluateDate = formatDate(
        new Date(),
        "yyyy-MM-dd hh:mm"
      );
      vm.$store.dispatch("getDimenssionList", param).then(res => {
        if (res.success) {
          vm.dimensionForm.dimensionList = res.data.map(item => {
            item.dimension = item.dimensionName + "(总分" + item.mark + ")";
            item.score = "";
            item.evaluate = "";
            return item;
          });
        } else {
          vm.dimensionForm.dimensionList = [];
        }
        vm.dialogSetVisible = true;
      });
    },
    handleEdit(row) {
      let vm = this;
      let param = {
        affairId: row.affairId,
        employeeId: row.staffId
      };
      vm.dimensionForm.affairId = row.affairId;
      vm.dimensionForm.staffId = row.staffId;
      vm.dimensionForm.affairName = row.afairName;
      vm.dimensionForm.evaluator = row.staffName;
      vm.dimensionForm.evaluateDate = "";
      vm.dimensionForm.dimensionList = row.personalAffairdimensionList.map(
        item => {
          if(item.mark) {
            item.dimension = item.dimensionName + "(总分" + item.mark + ")";
          }else{
            item.dimension = item.dimensionName;
          }
          item.score = item.score;
          item.evaluate = item.evaluation;
          return item;
        }
      );
      vm.dialogSetVisible = true;
    },
    getScoreInfo() {
      let vm = this;
      let param = {
        employeeID: vm.employeeId,
        pageParam: {
          currPage: 1,
          pageSize: 9999
        }
      };
      vm.$store
        .dispatch("getScoreInfoById", param)
        .then(res => {
          if (res.success) {
            vm.dataTable = res.data.list;
          } else {
            vm.dataTable = [];
          }
        })
        .catch(() => {
          vm.dataTable = [];
        });
    },
    handleSave() {
      let vm = this;
      vm.$refs.dimensionForm.validate(valid => {
        if (valid) {
          let param = {
            staffId: vm.dimensionForm.staffId,
            evaluatorId: vm.employeeId,
            affairId: vm.dimensionForm.affairId,
            personalAffairdimensionList: vm.dimensionForm.dimensionList.map(
              item => {
                return {
                  employeeId: vm.employeeId,
                  transactionDimensionId: item.transactionDimensionId,
                  evaluation: item.evaluate,
                  score: item.score
                };
              }
            )
          };
          vm.$store.dispatch("saveEvaluateInfo", param).then(res => {
            if (res.success) {
              vm.$message.success("操作成功");
              vm.getEvaluateListData(vm.employeeId);
              vm.getEvaluateCount(vm.employeeId);
            } else {
              vm.$message.error("操作失败");
            }
            vm.dialogSetVisible = false;
          });
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style>
</style>
