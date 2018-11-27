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
            <el-select v-model="form.du" size="mini" placeholder="请选择">
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
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="Date">
        <template slot-scope="scope">
          <span>{{ scope.row.timestamp | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="Author">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" label="Importance">
        <template slot-scope="scope">
          <svg-icon
            v-for="n in +scope.row.importance"
            :key="n"
            icon-class="star"
            class="meta-item__icon"
          />
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="Status" width="110">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column min-width="300px" label="Title">
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

      <el-table-column align="center" label="Actions" width="120">
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
      } else {
        this.productOptions = [];
      }
    });
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    },
    changeProduct(value) {
      console.log(value);
    }
  }
};
</script>
<style rel="stylesheet/scss" lang="scss">
// .project-group-container {
//   .el-form-item__content{width: 60%}
// }
</style>
