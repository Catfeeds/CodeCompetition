<template>
    <div>
        <el-form :inline="true">
            <el-form-item label="立项时间">
                <el-date-picker
                    v-model="projectApprovalDate"
                    type="daterange"
                    range-separator="至"
                    size="mini"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSearch();" icon="el-icon-search" size="mini">搜 索</el-button>
                <el-button  type="primary"  size="mini" @click="createVisible=!createVisible"  icon="el-icon-plus">{{ $t("table.add") }}</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="tableData"  style="width: 100%" max-height="250" border fit size="mini" stripe highlight-current-row>
            <el-table-column fixed type="index" sortable header-align="center" align="center" width="60" :label="$t('table.id')"></el-table-column>
            <el-table-column prop="projectName" sortable header-align="center" label="项目组名称"></el-table-column>
            <el-table-column prop="pmName" sortable header-align="center" label="PM"></el-table-column>
            <el-table-column prop="associatedPO" sortable header-align="center" label="关联PO名称"></el-table-column>
            <el-table-column header-align="center" sortable label="状态">
                <template slot-scope="scope">
                    {{statusMap[scope.row.status]}}
                </template>
            </el-table-column>
            <el-table-column prop="startDate" sortable header-align="center" label="立项时间"></el-table-column>
            <el-table-column prop="endDate" sortable header-align="center" label="结项时间"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                    size="mini"
                    type="danger"
                    @click="handleProjectApproval(scope.$index, scope.row)">结项</el-button>
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
            ></el-pagination>
        </el-row>

        <el-dialog :title="dialogTitle" :visible.sync="createVisible" width="500px">
            <el-form  :model="formObj" label-width="150px" :rules="rules"  ref="poForm">
                <el-form-item label="项目组名称" prop="projectName">
                    <el-input placeholder="项目组名称" size="small" v-model="formObj.projectName" clearable style="width:215px"></el-input>
                </el-form-item>
                <el-form-item label="PM" prop="pm">
                    <el-input placeholder="PM" size="small" v-model="formObj.pmName" clearable style="width:215px"></el-input>
                </el-form-item>
                <el-form-item label="产品线" prop="product">
                    <el-select  v-model="formObj.product" size="small" placeholder="产品线" @change="productChange" >
                        <el-option
                            v-for="item in formObj.productOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="DU" prop="du">
                    <el-select v-model="formObj.du" size="small" clearable placeholder="DU" @change="changeDU" >
                        <el-option
                            v-for="item in formObj.duOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="PDU" prop="pdu">
                    <el-select v-model="formObj.pdu" size="small" clearable placeholder="PDU">
                    <el-option
                        v-for="item in formObj.pduOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="PO" prop="po">
                    <el-select v-model="formObj.po" reserve-keyword remote filterable size="small" 
                        :loading="loading" clearable placeholder="请输入PO编号或名称搜索" :remote-method="remoteMethod">
                    <el-option
                        v-for="item in formObj.poOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item  label="立项时间">
                    {{formObj.startDate||'NA / NA / NA'}}
                </el-form-item>
                <el-form-item label="结项时间">
                    {{formObj.endDate||'NA / NA / NA'}}
                </el-form-item>
                <el-form-item>
                    <el-button  type="primary" size="mini" @click="handleConfirm">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

    </div>
</template>

<script>
import {mapState,mapActions} from 'vuex'
export default {
    data(){
        return {
            projectApprovalDate:'',
            tableData:[],
            pageSize:100,
            currentPage:1,
            loading:false,
            total:0,
            createVisible:false,
            dialogTitle:'添 加',
            statusMap:{
                '0':'立项',
                '1':'在研',
                '2':'结项中',
                '3':'已结项'
            },
            rules: {
                projectName: [
                    { required: true, message: '请输入项目名称', trigger: 'blur' },
                    { min: 3, max: 5, message: '长度在 3 到 50 个字符', trigger: 'blur' }
                ],
                pm: [
                    { required: true, message: '请输入PM姓名', trigger: 'blur' },
                    { min: 3, max: 5, message: '长度在 3 到 50 个字符', trigger: 'blur' }
                ],
                product: [{ required: true, message: "请选择产品线", trigger: "change" }],
                du: [{ required: true, message: "请选择DU", trigger: "change" }],
                pdu: [{ required: true, message: "请选择PDU", trigger: "change" }],
                po: [{ required: true, message: "请选择PO", trigger: "change" }]
            }
        }
    },
    mounted(){
        console.log(this.$store.state.associatedPOStore.esForm);
    },
    computed:{
        ...mapState({
            formObj:state=>state.associatedPOStore.esForm
        })
    },
    methods:{
        ...mapActions(['getAPOProjectInfo','projectApproval',
        'getESFormProductInfo','getESFormDU','getESFormPDUList']),
        formatDate(date) {
            var Y = date.getFullYear() + '-';
            var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            var D = date.getDate() + ' ';
            return Y+M+D;
        },
        onSearch(){
            this.handleSizeChange();
        },
        handleSizeChange(){
            //接口输入参数没加分页和日期
            if(!this.projectApprovalDate){
                return;
            }
            var vm=this;
            this.getAPOProjectInfo({
                startDate:this.formatDate(this.projectApprovalDate[0]),
                endDate:this.formatDate(this.projectApprovalDate[1])
            }).then(res=>{
                vm.tableData=res && res.data && res.data.data ||[];
                vm.total =vm.tableData.length;
            })
        },
        handleEdit(index,row){
            this.dialogTitle='编辑';
            this.createVisible=true;
            this.formObj.projectName=row.projectName;
            this.formObj.pmName=row.pmName;
            this.formObj.product=row.product;
        },
        handleProjectApproval(index,row){
            //结项接口未实现
            this.projectApproval(row.projectId).then(data=>{
                //成功后禁用编辑和结项按钮
            })
        },
        remoteMethod(){
            var params = {
                startDate:this.formatDate(this.projectApprovalDate[0]),
                endDate:this.formatDate(this.projectApprovalDate[1]),
            };
            if(this.formObj.po){
                params.poName=this.formObj.po;
            }
            var vm= this;
            this.getAPOProjectInfo().then(res=>{
                vm.formObj.poOptions = (res && res.data && res.data.data ||[]).map(function(item){
                    return item.poName;
                })
            })
        },
        handleConfirm(){
            this.$refs.poForm.validate(valid => {

            });
        },
        productChange(){
            this.getESFormDU();
        },
        changeDU(){
            this.getESFormPDUList();
        }
    }
}
</script>

<style>

</style>
