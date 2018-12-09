<template>
    <div>
        <el-form :inline="true" :model="form" class="project-group-form">
          <el-form-item label="">
            <el-select
              v-model="form.region"
              size="mini"
              clearable
              placeholder="地域"
              @change="regionChange"
            >
              <el-option
                v-for="item in form.regionOptions"
                :key="item"
                :label="item"
                :value="item"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="">
            <el-select
              v-model="form.costCenter"
              size="mini"
              placeholder="成本中心"
              @change="changeDU"
            >
              <el-option
                v-for="item in form.costCenterOptions"
                :key="item"
                :label="item"
                :value="item"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="">
              <el-input
                v-model="form.staffName"
                size="mini"
                placeholder="姓名"
                ></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-input
              v-model="form.staffID"
              size="mini"
              placeholder="工号"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="mini" @click="onSearchForm"
              >查询</el-button>
          </el-form-item>
      </el-form>
      <div>
          <el-tag v-for="(item,index) in dataSource" :key="index">{{item.name}}</el-tag>
      </div>
    </div>
</template>

<script>
import {mapState} from 'vuex'
export default {
    computed:{
        ...mapState({
            form:state => state.projectGroupStore.esForm,
            dataSource:state =>state.projectGroupStore.esDataSource
        })
    },
    methods:{
        onSearchForm(){
            this.$store.dispatch('searchEmployee');
        }
    }
}
</script>

<style>

</style>
