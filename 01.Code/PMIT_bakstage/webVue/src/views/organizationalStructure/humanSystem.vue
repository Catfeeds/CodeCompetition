<template>
  <div class="app-container">
    界面完善中。。。
    <!-- <el-form :inline="true" :model="formInline">
      <el-form-item label="产品线">
        <el-select
          v-model="product"
          placeholder="产品线"
          @change="productChange"
        >
          <el-option label="2012" value="2012"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="区域">
        <el-select v-model="area" placeholder="区域">
          <el-option label="西安" value="xian"></el-option>
          <el-option label="北京" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-table
        ref="singleTable"
        :data="relationShipList"
        highlight-current-row
        style="width: 100%"
      >
        <el-table-column type="index" width="80"> </el-table-column>
        <el-table-column property="product" label="产品线"> </el-table-column>
        <el-table-column property="area" label="地域"> </el-table-column>
        <el-table-column property="name" label="关系树名称"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row);"
              >编辑</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row);"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div> -->
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  mounted() {
    var vm = this;
    vm.getRSProductInfo.then(function(data) {
      vm.productList = data;
    });
  },
  data() {
    return {
      productList: [],
      areaList: [],
      relationShipList: []
    };
  },
  computed: {
    product: {
      get() {
        return this.$store.state.relationShip.selectedProduct;
      },
      set(val) {
        this.$store.commit("setSelectedProduct", val);
      }
    },
    area: {
      get() {
        return this.$store.state.relationShip.selectedArea;
      },
      set(val) {
        this.$store.commit("setSelectedArea", val);
      }
    }
  },
  methods: {
    ...mapActions(["getRSProductInfo", "getRSArea", "getRelationShips"]),
    productChange() {
      var vm = this;
      if (!vm.product) {
        vm.areaList = [];
        return;
      }
      vm.getRSArea(this.product).then(function(data) {
        vm.areaList = data;
      });
    },
    onSubmit() {
      this.getRelationShips().then(res => {
        this.relationShipList = (res && res.data) || [];
      });
    },
    handleEdit() {},
    handleDelete() {}
  }
};
</script>

<style></style>
