<template>
  <div>
    <div class="filter-container">
      <el-date-picker
        v-model="projectApprovalDate"
        type="daterange"
        size="mini"
        range-separator="至"
        start-placeholder="立项开始日期"
        end-placeholder="立项结束日期"
      ></el-date-picker>
      <el-button
        type="primary"
        size="mini"
        style="margin-left: 10px;"
        icon="el-icon-search"
        @click="handleFilter"
      >{{ $t("table.search") }}</el-button>
      <el-button
        style="margin-left: 10px;"
        type="primary"
        size="mini"
        icon="el-icon-plus"
        @click="handleCreate"
      >{{ $t("table.add") }}</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      border
      fit
      size="mini"
      stripe
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column
        type="index"
        width="60"
        header-align="center"
        align="center"
        :label="$t('table.id')"
      ></el-table-column>
      <el-table-column prop="relationPO" label="项目组名称" header-align="center"></el-table-column>
      <el-table-column prop="relationPO" label="PM" header-align="center"></el-table-column>
      <el-table-column prop="relationPO" label="关联PO名称" header-align="center"></el-table-column>
      <el-table-column prop="relationPO" label="状态" header-align="center"></el-table-column>
      <el-table-column prop="relationPO" label="立项时间" header-align="center"></el-table-column>
      <el-table-column prop="relationPO" label="结项时间" header-align="center"></el-table-column>
      <el-table-column prop="relationPO" label="操作" header-align="center" width="120">
        <template></template>
      </el-table-column>
    </el-table>
    <el-row type="flex" justify="end">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleSizeChange"
        :current-page.sync="currentPage"
        :page-size="10"
        layout="total, slot,prev, pager, next"
        :total="total"
        prev-text="上一页"
        next-text="下一页"
      ></el-pagination>
    </el-row>
    <el-dialog :title="dialogBaseTitle" :visible.sync="dialogBaseVisible" width="30%">
      <div class="filter-container">
        <el-input
          v-model="searchForm.poID"
          size="mini"
          clearable
          class="filter-item"
          style="width: 130px"
          placeholder="PO编号"
        ></el-input>
        <el-input
          v-model="searchForm.poName"
          size="mini"
          clearable
          class="filter-item"
          style="width: 130px"
          placeholder="PO名称"
        ></el-input>
        <el-button
          type="primary"
          size="mini"
          style="margin-left: 10px;"
          icon="el-icon-search"
          @click="handlePOFilter"
        >{{ $t("table.search") }}</el-button>
      </div>
      <!-- <el-table
        ref="multipleTable"
        :data="poList"
        border
        fit
        size="mini"
        stripe
        max-height="185"
        highlight-current-row
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="selection"
          width="55"
        ></el-table-column>
        <el-table-column prop="relationPO" label="产品线" header-align="center"></el-table-column>
        <el-table-column prop="relationPO" label="DU" header-align="center"></el-table-column>
        <el-table-column prop="relationPO" label="PDU" header-align="center"></el-table-column>
        <el-table-column prop="relationPO" label="PO名称" header-align="center"></el-table-column>
        <el-table-column prop="relationPO" label="立项时间" header-align="center"></el-table-column>
        <el-table-column prop="relationPO" label="结项时间" header-align="center"></el-table-column>
      </el-table> -->
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogBaseVisible = false;" size="mini">取 消</el-button>
        <el-button type="primary" @click="submtForm();" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      listLoading: false,
      list: [],
      poLoading: false,
      poList: [],
      projectApprovalDate: "",
      dialogBaseTitle: "选择PO",
      dialogBaseVisible: false,
      selectedIds: [],
      searchForm: {
        poID: "",
        poName: ""
      }
    };
  },
  computed: {
    startDate() {},
    endDate() {}
  },
  methods: {
    handleSizeChange() {},
    handleFilter() {},
    handleCreate() {
      this.dialogBaseVisible = true;
    },
    handlePOFilter() {

    },
    handleSelectionChange(selection) {
      this.selectedIds = selection.map(item => item.id);
    }
  }
};
</script>

<style>
</style>
