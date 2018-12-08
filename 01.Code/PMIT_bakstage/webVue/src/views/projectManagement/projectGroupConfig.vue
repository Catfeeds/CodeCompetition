<template>
  <div class="app-container">
    <el-form :inline="true" :model="form" class="project-group-form">
      <el-row>
        <el-col :span="4">
          <el-form-item label="">
            <el-select
              v-model="form.product"
              size="mini"
              clearable
              placeholder="产品线"
              @change="productChange"
            >
              <el-option
                v-for="item in form.productOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="">
            <el-select
              v-model="form.du"
              size="mini"
              placeholder="DU"
              @change="changeDU"
            >
              <el-option
                v-for="item in form.duOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="">
            <el-select v-model="form.pdu" size="mini" placeholder="PDU">
              <el-option
                v-for="item in form.pduOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="">
            <el-input
              v-model="form.teamName"
              size="mini"
              placeholder="项目组名称"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="">
            <el-input
              v-model="form.pm"
              size="mini"
              placeholder="PM"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="2">
          <el-form-item>
            <el-button type="primary" size="mini" @click="onSearchForm"
              >查询</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="2">
          <el-form-item>
            <el-button type="primary" size="mini" @click="onCreate"
              >新增</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="dataTable"
      border
      fit
      size="mini"
      stripe
      highlight-current-row
      max-height="400"
      style="width: 100%"
    >
      <el-table-column header-align="center" type="index" width="80" sortable="true">
      </el-table-column>

      <el-table-column
        min-width="150px"
        header-align="center"
        :label="$t('projectGroup.product')"
        sortable
        prop="bu"
      >
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" prop="du" label="DU" sortable>
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" prop="pdu" label="PDU" sortable>
      </el-table-column>

      <el-table-column
        :label="$t('projectGroup.teamName')" header-align="center"  min-width="110" sortable prop="projectName"
      ></el-table-column>

      <el-table-column label="PM" header-align="center" min-width="110" sortable prop="pmName">
      </el-table-column>

      <el-table-column align="center" :label="$t('table.option')" width="320" header-align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="small"
            icon="el-icon-setting"
            @click="teamSettings(scope.row)"
          >团队设置</el-button>
          <el-button
            type="primary"
            size="small"
            icon="el-icon-edit"
            @click="projectEdit(scope.row)"
          >编辑</el-button>
          <el-button
            type="primary"
            size="small"
            icon="el-icon-remove"
            @click="projectDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row type="flex" justify="end">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleSizeChange"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, slot,prev, pager, next"
        :total="total"
        prev-text="上一页"
        next-text="下一页"
      >
      </el-pagination>
    </el-row>
    <el-dialog title="新增" width="580px" :visible.sync="craeteVisible">
        <el-form :model="newForm" ref="newFormName" :rules="rules" label-width="140px" class="pg-form-wrap">
              <el-form-item label="产品线" prop="product">
                <el-select style="width:300px"
                  v-model="newForm.product"
                  size="small"
                  clearable
                  placeholder="产品线"
                  @change="newProductChange"
                >
                  <el-option v-for="item in newForm.productOptions"
                    :key="item" :label="item"  :value="item" ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="DU" prop="du">
                <el-select style="width:300px"
                  v-model="newForm.du"
                  size="small"
                  placeholder="DU"
                  @change="newDUChange"
                >
                  <el-option
                    v-for="item in newForm.duOptions"
                    :key="item"
                    :label="item"
                    :value="item"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="PDU" prop="pdu">
                <el-select  style="width:300px" v-model="newForm.pdu" size="small" placeholder="PDU">
                  <el-option
                    v-for="item in newForm.pduOptions"
                    :key="item"
                    :label="item"
                    :value="item"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="项目组名称" prop="teamName">
                <el-input style="width:300px"
                  v-model="newForm.teamName"
                  size="small"
                  placeholder="项目组名称"
                ></el-input>
              </el-form-item>
              <el-form-item label="PM" prop="pm">
                <el-select style="width:300px" v-model="newForm.pm" size="small" placeholder="PM">
                  <el-option
                    v-for="item in newForm.pduOptions"
                    :key="item"
                    :label="item"
                    :value="item"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" size="small" @click="onSave"
                  > 保 存 </el-button>
              </el-form-item>
        </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {mapState,mapMutations,mapActions} from 'vuex'
export default {
  data() {
    return {
      listLoading: false,
      craeteVisible:false,
      pageSize:100,
      dataTable:[],
      total:0,
      newFormName:"newForm",
      currentPage:1,
      rules: {
          product: [
            { required: true, message: '请选择产品线', trigger: 'change' }
          ],
          du: [
            { required: true, message: '请选择DU', trigger: 'change' }
          ],
          pdu: [
            { required: true, message: '请选择PDU', trigger: 'change' }
          ],
          pm: [
            { required: true, message: '请选择PM', trigger: 'change' }
          ],
          teamName: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
            { min: 3, max: 50, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ]
      }
    };
  },
  mounted() {
    var vm = this;
    this.getPGProductInfo();
    this.getNewFormProductInfo();
    this.getProjectGroupInfo().then(()=>{
        vm.listLoading = false;
    }).catch(()=>{
        vm.listLoading = false;
    });
  },
  computed:{
    ...mapState({
      dataSource:state => state.projectGroupStore.dataSource,
      form:state => state.projectGroupStore.searchForm,
      newForm:state => state.projectGroupStore.newForm
    })
  },
  watch:{
    dataSource(data){
      this.dataTable = data.datas
      this.total = data.totalSize;
    }
  },
  methods: {
    ...mapActions(['getPGProductInfo','getPGDU','getPGPDUList','getProjectGroupInfo',
      'getNewFormProductInfo','getNewFormDU','getNewFormPDUList']),
    onSearchForm(arg,curPage) {
      let vm = this;
      vm.listLoading = true;
      vm.currentPage = curPage || 1;
      this.getProjectGroupInfo({
        pageNo:vm.currentPage
      }).then(()=>{
        vm.listLoading = false;
      });
    },
    onCreate(){
      this.newForm.product='';
      this.newForm.du='';
      this.newForm.pdu='';
      this.newForm='';
      this.newForm='';
      this.craeteVisible = true;
    },
    productChange() {
      this.getPGDU();
    },
    changeDU() {
      this.getPGPDUList();
    },
    newProductChange(){
      this.getNewFormDU();
    },
    newDUChange(){
      this.getNewFormPDUList();
    },
    handleSizeChange(){
      this.onSearchForm(null,this.currentPage);
    },
    teamSettings(row){
    },
    projectEdit(row){
      this.newForm.product=row.product;
      this.newForm.du=row.du;
      this.newForm.pdu=row.pdu;
      this.newForm=row.teamName;
      this.newForm=row.pm;
      this.craeteVisible = true;
    },
    projectDelete(row){
      //删除
    },
    onSave(){
      this.$refs[this.newFormName].validate((valid) => {
          if (valid) {
           // vm.
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
.pg-form-wrap .el-form-item{
  margin-bottom: 15px;
}
</style>
