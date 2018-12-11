<template>
    <div>
        <el-form :inline="true">
            <el-row>
                <el-col :span="3">
                    <el-form-item>
                        <el-select
                            v-model="product"
                            size="mini"
                            clearable
                            placeholder="产品线"
                            @change="productChange"
                            >
                            <el-option
                                v-for="item in productOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="3">
                    <el-form-item>
                        <el-select
                        v-model="du"
                        size="mini"
                        clearable
                        placeholder="DU"
                        @change="changeDU"
                        >
                        <el-option
                            v-for="item in duOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                        ></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="3">
                    <el-form-item>
                        <el-select v-model="pdu" size="mini" clearable placeholder="PDU">
                            <el-option
                                v-for="item in pduOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="3">
                    <el-form-item>
                         <el-select v-model="po" size="mini" clearable placeholder="PO">
                            <el-option
                                v-for="item in poOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="7">
                     <el-form-item>
                         <el-button type="primary" @click="onSearch();" size="mini">查 询</el-button>
                         <el-button v-show="!showPrePeriod" type="primary" size="mini" @click="onCreate" icon="el-icon-plus">{{ $t("table.add") }}</el-button>
                         <el-button v-show="showPrePeriod" type="primary" size="mini" @click="showPrePeriod=!showPrePeriod">保 存</el-button>
                         <el-button v-show="showPrePeriod" type="primary" size="mini" @click="showPrePeriod=!showPrePeriod">取 消</el-button>
                         <el-button v-show="!showPrePeriod" type="primary" @click="onExtendPrePeriod" size="mini">继承上一期</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
       <el-table v-show="!showPrePeriod" :data="tableData" border fix size="mini" stripe :span-method="arraySpanMethod" style="width: 100%">
            <el-table-column type="index" label="序号" width="80"></el-table-column>
            <el-table-column prop="name" label="关键角色"></el-table-column>
            <el-table-column prop="amount" sortable label="数量"></el-table-column>
            <el-table-column prop="roleStaff" sortable label="角色员工"></el-table-column>
            <el-table-column prop="reserveStaff" sortable label="备份员工"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="添 加" :visible.sync="createVisible" width="500px">
            <el-form  :model="formObj" label-width="150px" :rules="rules"  ref="keyRoleForm">
                <el-form-item label="关键角色" prop="role">
                    <el-input placeholder="关键角色" size="small" v-model="formObj.role" clearable style="width:215px"></el-input>
                </el-form-item>
                <el-form-item label="PM" prop="amount">
                    <el-input placeholder="数量" type="number" size="small" v-model="formObj.amount" clearable style="width:215px"></el-input>
                </el-form-item>
                <el-form-item label="角色员工" prop="staffID">
                    <el-select v-model="formObj.staffID" reserve-keyword remote filterable size="small" 
                        :loading="loading" clearable placeholder="请输入员工工号或姓名搜索" :remote-method="remoteMethod">
                        <el-option
                            v-for="item in formObj.staffOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="备份员工" prop="reserveStaffID">
                    <el-select v-model="formObj.reserveStaffID" reserve-keyword remote filterable size="small" 
                        :loading="loading" clearable placeholder="请输入员工工号或姓名搜索" :remote-method="remoteMethod">
                        <el-option
                            v-for="item in formObj.reserveStaffOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button  type="primary" size="mini" @click="handleConfirm">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name:'keyRole',    
    data(){
        return {
            rules:[],
            loading:false,
            showPrePeriod:false,
            tableData:[],
            productOptions:[],
            duOptions:[],
            pduOptions:[],
            poOptions:[],
            createVisible:false,
            loading:false,
            roleDialogTitle:'',
            product:'',
            du:'',
            pdu:'',
            po:'',
            formObj:{
                role:'',
                amount:'',
                staffID:'',
                reserveStaffID:'',
                staffOptions:[],
                reserveStaffOptions:[]
            }
        }
    },
    methods:{
        productChange(){

        },
        changeDU(){
            
        },
        onCreate(){
            this.roleDialogTitle='添 加';
            this.createVisible = true;
        },
        arraySpanMethod(){

        },
        handleEdit(index,row){
            this.roleDialogTitle='编 辑';
            this.createVisible=true;
        },
        handleDelete(){

        },
        handleConfirm(){

        },
        onExtendPrePeriod(){
            this.showPrePeriod=true;
        },
        remoteMethod(){}
    }
}
</script>

<style>

</style>
