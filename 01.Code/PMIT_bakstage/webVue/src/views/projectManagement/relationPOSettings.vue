<template>
    <div>
        立项时间：
        <el-date-picker
            v-model="projectApprovalDate"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
        </el-date-picker>
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
            <el-table-column fixed type="index" width="60"></el-table-column>
            <el-table-column prop="relationPO" label="关联PO"></el-table-column>
            <el-table-column prop="relationPO" label="状态" ></el-table-column>
            <el-table-column prop="relationPO" label="立项时间" ></el-table-column>
            <el-table-column prop="relationPO" label="结项时间" ></el-table-column>
            <el-table-column prop="relationPO" label="操作" width="120">
                <template>
                </template>
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
            >
            </el-pagination>
        </el-row>
    </div>
</template>

<script>
export default {
    data(){
        return {
            listLoading:false,
            list:[],
            projectApprovalDate:''
        }
    },
    computed:{
        startDate(){

        },
        endDate(){

        }
    },
    methods:{
        handleSizeChange(){

        }
    }
}
</script>

<style>

</style>
