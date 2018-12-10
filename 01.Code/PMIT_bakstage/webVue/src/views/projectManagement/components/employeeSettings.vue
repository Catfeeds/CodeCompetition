<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <div id="empRelationTree" class="ztree tree-wrap-border" style="height:400px;"></div>
      </el-col>
      <el-col :span="18" class="em-list tree-wrap-border" style="width:74%">
        <el-form :inline="true" class="em-wrap" :model="searchForm" ref="searchForm">
          <el-form-item label>
            <el-input
              placeholder="PDU"
              size="mini"
              v-model="teamInfo.pdu"
              style="width:193px"
              disabled
            ></el-input>
          </el-form-item>
          <el-form-item label>
            <el-select v-model="searchForm.gender" size="mini" clearable placeholder="性别">
              <el-option label="男" value="0"></el-option>
              <el-option label="女" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label>
            <el-select
              v-model="region"
              size="mini"
              clearable
              placeholder="地域"
              @change="regionChange"
            >
              <el-option v-for="item in regionOptions" :key="item" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label>
            <el-select v-model="costCenter" size="mini" clearable placeholder="成本中心">
              <el-option v-for="item in costCenterOptions" :key="item" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label>
            <el-input
              placeholder="姓名"
              size="mini"
              v-model="searchForm.employeeName"
              clearable
              style="width:193px"
            ></el-input>
          </el-form-item>
          <el-form-item label>
            <el-input
              placeholder="工号"
              size="mini"
              v-model="searchForm.employeeID"
              clearable
              style="width:193px"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="handleFilter();"
              icon="el-icon-search"
              size="mini"
            >{{ $t("table.search") }}</el-button>
          </el-form-item>
        </el-form>
        <div id="empListTree" class="ztree" style="height:270px;"></div>
        <el-row type="flex" justify="end">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleSizeChange"
            :current-page.sync="currentPage"
            :page-size="pageSize"
            layout="slot,prev, pager, next"
            :total="total"
            prev-text="上一页"
            next-text="下一页"
          ></el-pagination>
        </el-row>
      </el-col>
    </el-row>
    <el-row type="flex" justify="end" style="margin-top:20px">
      <el-button
        type="primary"
        @click="handleSave"
        icon="el-icon-circle-plus-outline"
        size="mini"
      >保 存</el-button>
    </el-row>
  </div>
</template>

<script>
import "ztree";
import { mapState, mapMutations, mapActions } from "vuex";
export default {
  props: ["teamInfo"],
  data() {
    let vm = this;
    return {
      currentTreeId: "",
      currentPage: 1,
      total: 0,
      pageSize: 100,
      employeeDataSource: [],
      employees: [],
      searchForm: {
        gender: "",
        employeeName: "",
        employeeID: ""
      },
      relationSetting: {
        view: {
          showIcon: false
        },
        edit: {
          enable: true,
          showRemoveBtn: false,
          showRenameBtn: false,
          drag: {
            prev: false,
            next: false,
            inner: true
          }
        },
        data: {
          simpleData: {
            enable: true
          }
        },
        callback: {
          beforeDrag: this.beforeDrag1,
          beforeDrop: this.beforeDrop1
        }
      },
      empListSetting: {
        edit: {
          enable: true,
          showRemoveBtn: false,
          showRenameBtn: false
        },
        data: {
          simpleData: {
            enable: true
          }
        },
        callback: {
          beforeDrag: this.beforeDrag2,
          beforeDrop: this.beforeDrop2
        }
      }
    };
  },
  computed: {
    ...mapState({
      regionOptions: state => state.eSettingsStore.regionData,
      costCenterOptions: state => state.eSettingsStore.costCenterData
    }),
    region: {
      get() {
        return this.$store.state.eSettingsStore.region;
      },
      set(val) {
        this.$store.commit("updateTMRegino", val);
      }
    },
    costCenter: {
      get() {
        return this.$store.state.eSettingsStore.costCenter;
      },
      set(val) {
        this.$store.commit("updateTMCostCenter", val);
      }
    }
  },
  mounted() {
    if (this.teamInfo) {
      this.renderEmpRelationTree(this.teamInfo);
    }
  },
  watch: {
    teamInfo(data) {
      this.renderEmpRelationTree(data);
    },
    employees(data) {
      $.fn.zTree.init(
        $("#empListTree"),
        this.empListSetting,
        data.map(item => {
          return {
            id: item.staffId,
            employeeName: item.staffName,
            name: item.staffName + " (" + item.staffId + ")"
          };
        })
      );
    }
  },
  methods: {
    ...mapActions([
      "getAreaAndCuBycondition",
      "getTMEmployees",
      "getEmployeesByTeam",
      "saveTeamInfos"
    ]),
    beforeDrag1(treeId, treeNodes) {
      this.currentTreeId = treeId;
      return !treeNodes[0].disDrop;
    },
    beforeDrop1(treeId, treeNodes, targetNode, moveType) {
      return !targetNode && this.currentTreeId !== treeId;
    },
    beforeDrag2(treeId, treeNodes) {
      this.currentTreeId = treeId;
      return true;
    },
    beforeDrop2(treeId, treeNodes, targetNode, moveType) {
      if (targetNode.level > 0) {
        this.$message.warning("只能拖动到项目组名称(一级节点)上面");
        return false;
      }
      return !!targetNode && this.currentTreeId !== treeId;
    },
    handleSizeChange(arg) {
      this.total = this.employeeDataSource.length;
      this.employees = this.employeeDataSource.slice(
        (this.currentPage - 1) * 100,
        this.currentPage * 100 + 1
      );
    },
    regionChange() {
      this.$store.commit("updateCostCenterData");
    },
    handleFilter() {
      let vm = this;
      let ztreeObj = $.fn.zTree.getZTreeObj("empRelationTree");
      let nodes = ztreeObj.getNodesByFilter(item => {
        return item.level > 0;
      });
      vm.currentPage = 1;
      vm.searchForm.pdu = vm.teamInfo.pdu;
      vm.getTMEmployees(vm.searchForm).then(res => {
        if (res.success) {
          vm.employeeDataSource = res.data.filter(item => {
            return !_.contains(_.pluck(nodes, "id"), item.staffId);
          });
        } else {
          vm.employeeDataSource = [];
        }
        vm.handleSizeChange();
      });
    },
    handleSave() {
      let vm = this;
      let ztreeObj = $.fn.zTree.getZTreeObj("empRelationTree");
      if (!ztreeObj) {
        return;
      }
      let nodes = ztreeObj.getNodesByFilter(item => {
        return item.level > 0;
      });
      let param = nodes.map(item => {
        return {
          teamId: vm.teamInfo.projectID + "",
          teamName: vm.teamInfo.projectName,
          staffId: item.id + "",
          staffName: item.employeeName
        };
      });

      vm.saveTeamInfos(param).then(res => {
        if (res.success) {
          vm.$message.success("人员设置保存成功");
        } else {
          vm.$message.error("人员设置保存失败");
        }
      });
    },
    renderEmpRelationTree(data) {
      let vm = this;
      vm.zNodes = [];
      vm.zNodes.push({
        id: 1,
        pId: 0,
        open: true,
        disDrop: true,
        name:
          (data.projectName || "项目组名称") +
          "（" +
          (data.pmName || "PM姓名") +
          "）"
      });
      vm.getEmployeesByTeam(data.projectID).then(res => {
        if (res.success) {
          vm.zNodes[0].children = res.data.map(item => {
            return {
              id: item.staffId,
              employeeName: item.staffName,
              name: item.staffName + " (" + item.staffId + ")"
            };
          });
        }
        $.fn.zTree.init($("#empRelationTree"), vm.relationSetting, vm.zNodes);
        vm.handleFilter();
      });
    }
  }
};
</script>

<style>
.ztree {
  overflow: auto;
}
.tree-wrap-border {
  border: 1px solid #dcdfe6;
}
.em-list .el-tag {
  margin-right: 10px;
}
#empListTree li {
  display: inline-block;
  margin-bottom: 10px;
}
#empListTree li .button {
  display: none;
}
#empListTree a {
  margin-right: 10px;
  background-color: rgba(64, 158, 255, 0.1);
  display: inline-block;
  padding: 0 10px;
  height: 32px;
  line-height: 30px;
  font-size: 12px;
  color: #409eff;
  border-radius: 4px;
  box-sizing: border-box;
  border: 1px solid rgba(64, 158, 255, 0.2);
  white-space: nowrap;
}
.em-wrap .el-form-item {
  margin-bottom: 7px;
}
</style>
