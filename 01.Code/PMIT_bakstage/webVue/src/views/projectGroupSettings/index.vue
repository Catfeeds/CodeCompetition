<template>
  <div class="project-group-container">
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

      <el-table-column min-width="150px" header-align="center" :label="$t('projectGroup.product')" sortable="true">
        <template slot-scope="scope">
          <span>{{ scope.row.timestamp | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" label="DU" sortable="true">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" label="PDU" sortable="true">
        <template slot-scope="scope">
          <svg-icon
            v-for="n in +scope.row.importance"
            :key="n"
            icon-class="star"
            class="meta-item__icon"
          />
        </template>
      </el-table-column>

      <el-table-column :label="$t('projectGroup.teamName')" header-align="center" min-width="110" sortable="true">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column width="150px" label="PM" header-align="center" sortable="true">
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
          <span v-else>{{ scope.row.title }}</span>
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
    this.$store.dispatch("GetProductInfo").then(() => {
      const data = this.$store.getters.product;
      if (data) {
        this.productOptions = data.map(function(item) {
          return {
            label: item.name,
            value: item.id
          };
        });
        this.form.product =
          this.productOptions[0] && this.productOptions[0].value;
        this.changeProduct(this.form.product);
      } else {
        this.productOptions = [];
      }
    });
  },
  methods: {
    onSubmit() {
      this.listLoading = true;
      this.$store
        .dispatch("GetProjectGroupInfo", this.form)
        .then(() => {
          const data = this.$store.getters.projectGroup;
        })
        .catch(() => {
          this.listLoading = false;
        });
    },
    changeProduct(value) {
      this.$store.dispatch("GetDUInfo", value).then(() => {
        const data = this.$store.getters.du;
        if (data) {
          this.duOptions = data.map(function(item) {
            return {
              label: item.name,
              value: item.id
            };
          });
          this.form.du = this.duOptions[0] && this.duOptions[0].value;
          this.changeDU(this.form.du);
        } else {
          this.duOptions = [];
        }
      });
    },
    changeDU(value) {
      this.$store.dispatch("GetPDUInfo", this.form.product, value).then(() => {
        const data = this.$store.getters.pdu;
        if (data) {
          this.pduOptions = data.map(function(item) {
            return {
              label: item.name,
              value: item.id
            };
          });
          this.form.pdu = this.pduOptions[0] && this.pduOptions[0].value;
        } else {
          this.pduOptions = [];
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
