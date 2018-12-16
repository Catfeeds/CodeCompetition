<template>
  <div class="app-container">
    <el-tabs @tab-click="changeTab" v-model="activeTab">
      <el-tab-pane label="我的待办" name="tab1">
        <el-row type="flex" justify="end" style="margin-bottom:10px;">
          <el-button type="primary" icon="el-icon-circle-plus-outline" size="mini" @click="collpseAll">全部折叠</el-button>
          <el-button type="primary" icon="el-icon-remove-outline" size="mini" @click="expandAll">全部展开</el-button>
        </el-row>
        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item title="待我评价" name="evaluation">
            
          </el-collapse-item>
          <el-collapse-item title="我的培训" name="training">
            
          </el-collapse-item>
          <el-collapse-item title="我的考试" name="examination">
            
          </el-collapse-item>
        </el-collapse>
      </el-tab-pane>
      <el-tab-pane label="历史记录" name="tab2"></el-tab-pane>
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
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      activeTab: "tab1",
      activeNames: []
    };
  },
  mounted() {
    this.getScoreInfo();
  },
  computed: {
    ...mapGetters(["employeeId", "employeeName"])
  },
  methods: {
    changeTab() {},
    collpseAll() {
      this.activeNames = [];
    },
    expandAll() {
      this.activeNames = ["evaluation", "training", "examination"]
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
    }
  }
};
</script>

<style>
</style>
