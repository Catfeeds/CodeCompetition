<template>
  <div class="app-container">
    <el-form :inline="true" :model="form" class="project-group-form">
      <el-row>
        <el-col :span="4">
          <el-form-item label="产品线">
            <el-select v-model="form.product" size="mini" placeholder="请选择" @change="changeProduct">
              <el-option
                v-for="item in productOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="DU">
            <el-select v-model="form.du" size="mini" placeholder="请选择" @change="changeDU">
              <el-option
                v-for="item in duOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="PDU">
            <el-select v-model="form.pdu" size="mini" placeholder="请选择">
              <el-option
                v-for="item in pduOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="项目组名称">
            <el-input v-model="form.teamName" size="mini" placeholder="请输入"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="PM">
            <el-input v-model="form.pm" size="mini" placeholder="请输入"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item>
            <el-button type="primary" size="mini" @click="onSubmit">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
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
      <el-table-column header-align="center" :label="$t('table.id')" width="80" sortable="true">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column
        min-width="150px"
        header-align="center"
        :label="$t('projectGroup.product')"
        sortable
      >
        <template slot-scope="scope">
          <span>{{ scope.row.product }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" label="DU" sortable>
        <template slot-scope="scope">
          <span>{{ scope.row.du }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" label="PDU" sortable>
        <template slot-scope="scope">
          <span>{{ scope.row.pdu }}</span>
        </template>
      </el-table-column>

      <el-table-column
        :label="$t('projectGroup.teamName')"
        header-align="center"
        min-width="110"
        sortable
      >
        <template slot-scope="scope">
          <span>{{scope.row.teamName}}</span>
        </template>
      </el-table-column>

      <el-table-column width="150px" label="PM" header-align="center">
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input v-model="scope.row.title" class="edit-input" size="small"/>
            <el-button
              class="cancel-btn"
              size="small"
              icon="el-icon-refresh"
              type="warning"
              @click="cancelEdit(scope.row)"
            >cancel</el-button>
          </template>
          <span v-else>{{ scope.row.pm }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" :label="$t('table.option')" width="120" header-align="center">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.edit"
            type="success"
            size="small"
            icon="el-icon-circle-check-outline"
            @click="confirmEdit(scope.row)"
          >Ok</el-button>
          <el-button
            v-else
            type="primary"
            size="small"
            icon="el-icon-edit"
            @click="scope.row.edit=!scope.row.edit"
          >Edit</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      productOptions: [],
      duOptions: [],
      pduOptions: [],
      form: {
        product: "",
        du: "",
        pdu: "",
        teamName: "",
        pm: ""
      },
      list: null,
      listLoading: false,
      listQuery: {
        page: 1,
        limit: 10
      }
    };
  },
  mounted() {
    let vm = this;
    vm.$store.dispatch("getProductInfo").then(() => {
      const data = vm.$store.getters.productList;
      if (data) {
        vm.productOptions = data;
        vm.form.product = data[0] && data[0].value;
        vm.changeProduct(vm.form.product);
      } else {
        vm.productOptions = [];
      }
    });
  },
  methods: {
    onSubmit() {
      let vm = this;
      vm.listLoading = true;
      vm.$store
        .dispatch("getProjectGroupInfo", vm.form)
        .then(data => {
          vm.list = data;
          vm.listLoading = false;
        })
        .catch(() => {
          vm.list = [];
          vm.listLoading = false;
        });
    },
    changeProduct(value) {
      let vm = this;
      vm.$store.dispatch("getDUInfo", value).then(() => {
        const data = vm.$store.getters.duList;
        if (data) {
          vm.duOptions = data;
          vm.form.du = data[0] && data[0].value;
          vm.changeDU(vm.form.du);
        } else {
          vm.duOptions = [];
        }
      });
    },
    changeDU(value) {
      let vm = this;
      vm.$store.dispatch("getPDUInfo", vm.form.product, value).then(() => {
        const data = vm.$store.getters.pduList;
        if (data) {
          vm.pduOptions = data;
          vm.form.pdu = data[0] && data[0].value;
        } else {
          vm.pduOptions = [];
        }
      });
    }
  }
};
</script>
<style rel="stylesheet/scss" lang="scss">
.project-group-container {
  margin: 15px;
  // .el-form-item__content{width: 60%}
}
</style>
