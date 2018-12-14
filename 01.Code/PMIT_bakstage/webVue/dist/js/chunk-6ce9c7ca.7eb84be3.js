(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6ce9c7ca"],{"038f":function(e,t,i){"use strict";i.r(t);var r=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"app-container"},[i("div",{staticClass:"filter-container"},[i("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"所属体系"},model:{value:e.searchForm.system,callback:function(t){e.$set(e.searchForm,"system",t)},expression:"searchForm.system"}},e._l(e.systemOptions,function(e){return i("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),i("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{clearable:"",size:"mini",placeholder:"课程类型"},model:{value:e.searchForm.courseType,callback:function(t){e.$set(e.searchForm,"courseType",t)},expression:"searchForm.courseType"}},e._l(e.courseTypeOptions,function(e){return i("el-option",{key:e,attrs:{label:e,value:e}})})),i("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{clearable:"",size:"mini",placeholder:"所属产品线"},model:{value:e.searchForm.product,callback:function(t){e.$set(e.searchForm,"product",t)},expression:"searchForm.product"}},e._l(e.productOptions,function(e){return i("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),i("el-input",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"培训名称"},model:{value:e.searchForm.trainName,callback:function(t){e.$set(e.searchForm,"trainName",t)},expression:"searchForm.trainName"}}),i("el-input",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"所属系列"},model:{value:e.searchForm.series,callback:function(t){e.$set(e.searchForm,"series",t)},expression:"searchForm.series"}}),i("el-button",{staticClass:"filter-item",attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.$t("table.search")))]),i("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.handleCreate}},[e._v(e._s(e.$t("table.add")))])],1),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticStyle:{width:"100%"},attrs:{data:e.list,border:"",fit:"",size:"mini",stripe:"","max-height":"420","highlight-current-row":""}},[i("el-table-column",{attrs:{"header-align":"center",align:"center",label:e.$t("table.id"),width:"50",type:"index"}}),i("el-table-column",{attrs:{width:"100px","header-align":"center",label:"培训编号",sortable:"",prop:"trainingId"}}),i("el-table-column",{attrs:{"min-width":"100px","header-align":"center",label:"培训名称",sortable:"",prop:"trainingName"}}),i("el-table-column",{attrs:{"min-width":"100px","header-align":"center",label:"所属系列",sortable:"",prop:"series"}}),i("el-table-column",{attrs:{"min-width":"100px","header-align":"center",label:"所属体系",sortable:"",prop:"sort"}}),i("el-table-column",{attrs:{"min-width":"100","header-align":"center",label:"课程类型",sortable:"",prop:"type"}}),i("el-table-column",{attrs:{"min-width":"110","header-align":"center",label:"所属产品线",sortable:"",prop:"bu"}}),i("el-table-column",{attrs:{width:"100","header-align":"center",label:"创建人",sortable:"",prop:"creatorName"}}),i("el-table-column",{attrs:{"min-width":"110","header-align":"center",label:"最后更新人",sortable:"",prop:"updateName"}}),i("el-table-column",{attrs:{"min-width":"140","header-align":"center",label:"最后更新时间",sortable:"",prop:"createTime"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(e._f("formatDate")(t.row.createTime)))])]}}])}),i("el-table-column",{attrs:{align:"center",label:e.$t("table.option"),width:"110","header-align":"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-edit",title:"编辑"},on:{click:function(i){e.handleEdit(t.row)}}}),i("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-delete",title:"删除"},on:{click:function(i){e.handleDel(t.row.trainingId)}}}),i("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-view",title:"查看"},on:{click:function(i){e.handleDetail(t.row)}}})]}}])})],1),i("el-row",{attrs:{type:"flex",justify:"end"}},[i("el-pagination",{attrs:{"current-page":e.page.currentPage,"page-size":e.page.pageSize,layout:"total, slot, prev, pager, next",total:e.page.totalRecord,"prev-text":"上一页","next-text":"下一页"},on:{"current-change":e.handleCurrentChange}})],1),i("el-dialog",{attrs:{title:e.dialogTitle,visible:e.dialogVisible,width:"45%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[i("el-form",{ref:"trainForm",attrs:{model:e.trainForm,size:"mini","label-width":"120px",rules:e.rules}},[e.isEdit||e.isDetail?i("el-row",[i("el-col",[i("el-form-item",{attrs:{label:"培训编号",prop:"trainId"}},[i("el-input",{attrs:{autocomplete:"off",disabled:""},model:{value:e.trainForm.trainId,callback:function(t){e.$set(e.trainForm,"trainId",t)},expression:"trainForm.trainId"}})],1)],1)],1):e._e(),i("el-row",[i("el-col",[i("el-form-item",{attrs:{label:"培训名称",prop:"trainName"}},[i("el-input",{attrs:{autocomplete:"off",required:"",maxlength:"64",disabled:e.isDetail},model:{value:e.trainForm.trainName,callback:function(t){e.$set(e.trainForm,"trainName",t)},expression:"trainForm.trainName"}})],1)],1)],1),i("el-row",[i("el-col",{attrs:{span:12}},[i("el-form-item",{attrs:{label:"所属系列",prop:"series"}},[e.isDetail?i("el-input",{attrs:{autocomplete:"off",required:"",maxlength:"64",disabled:""},model:{value:e.trainForm.series,callback:function(t){e.$set(e.trainForm,"series",t)},expression:"trainForm.series"}}):i("el-select",{attrs:{filterable:"","allow-create":"","default-first-option":"",placeholder:"请选择"},model:{value:e.trainForm.series,callback:function(t){e.$set(e.trainForm,"series",t)},expression:"trainForm.series"}},e._l(e.seriesOptions,function(e){return i("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1)],1),i("el-col",{attrs:{span:12}},[i("el-form-item",{attrs:{label:"所属体系",prop:"system"}},[e.isDetail?i("el-input",{attrs:{autocomplete:"off",required:"",maxlength:"64",disabled:""},model:{value:e.trainForm.system,callback:function(t){e.$set(e.trainForm,"system",t)},expression:"trainForm.system"}}):i("el-select",{attrs:{filterable:"","allow-create":"","default-first-option":"",placeholder:"请选择"},model:{value:e.trainForm.system,callback:function(t){e.$set(e.trainForm,"system",t)},expression:"trainForm.system"}},e._l(e.systemOptions,function(e){return i("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1)],1)],1),i("el-row",[i("el-col",{attrs:{span:12}},[i("el-form-item",{attrs:{label:"课程类型",prop:"courseType"}},[e.isDetail?i("el-input",{attrs:{autocomplete:"off",required:"",maxlength:"64",disabled:""},model:{value:e.trainForm.courseType,callback:function(t){e.$set(e.trainForm,"courseType",t)},expression:"trainForm.courseType"}}):i("el-select",{attrs:{placeholder:"请选择"},model:{value:e.trainForm.courseType,callback:function(t){e.$set(e.trainForm,"courseType",t)},expression:"trainForm.courseType"}},e._l(e.courseTypeOptions,function(e){return i("el-option",{key:e,attrs:{label:e,value:e}})}))],1)],1),i("el-col",{attrs:{span:12}},[i("el-form-item",{attrs:{label:"培训类型",prop:"trainType"}},[e.isDetail?i("el-input",{attrs:{autocomplete:"off",required:"",maxlength:"64",disabled:""},model:{value:e.trainForm.trainType,callback:function(t){e.$set(e.trainForm,"trainType",t)},expression:"trainForm.trainType"}}):i("el-select",{attrs:{placeholder:"请选择"},model:{value:e.trainForm.trainType,callback:function(t){e.$set(e.trainForm,"trainType",t)},expression:"trainForm.trainType"}},e._l(e.trainTypeOptions,function(e){return i("el-option",{key:e,attrs:{label:e,value:e}})}))],1)],1)],1),i("el-row",[i("el-col",{attrs:{span:12}},[i("el-form-item",{attrs:{label:"培训时长(小时)",prop:"trainTime"}},[i("el-input",{attrs:{autocomplete:"off",disabled:e.isDetail,maxlength:"4"},model:{value:e.trainForm.trainTime,callback:function(t){e.$set(e.trainForm,"trainTime",t)},expression:"trainForm.trainTime"}})],1)],1),i("el-col",{attrs:{span:12}},[i("el-form-item",{attrs:{label:"所属产品线",prop:"product"}},[e.isDetail?i("el-input",{attrs:{autocomplete:"off",required:"",maxlength:"64",disabled:""},model:{value:e.trainForm.product,callback:function(t){e.$set(e.trainForm,"product",t)},expression:"trainForm.product"}}):i("el-select",{attrs:{placeholder:"请选择"},model:{value:e.trainForm.product,callback:function(t){e.$set(e.trainForm,"product",t)},expression:"trainForm.product"}},e._l(e.productOptions,function(e){return i("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1)],1)],1),i("el-form-item",{attrs:{label:"培训简介",prop:"description"}},[i("el-input",{attrs:{disabled:e.isDetail,type:"textarea",autosize:{minRows:2,maxRows:4},resize:"none"},model:{value:e.trainForm.description,callback:function(t){e.$set(e.trainForm,"description",t)},expression:"trainForm.description"}})],1)],1),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e.isDetail?e._e():i("el-button",{attrs:{size:"mini"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),e.isDetail?i("el-button",{attrs:{size:"mini"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("关 闭")]):e._e(),e.isDetail?e._e():i("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){e.submtForm("trainForm")}}},[e._v("确 定")])],1)],1)],1)},a=[],s=(i("55dd"),i("be94")),n=(i("cadf"),i("551c"),i("097d"),i("2f62")),l=i("c466"),o=i("60fe"),c={filters:{formatDate:function(e){var t=new Date(e);return Object(l["a"])(t,"yyyy-MM-dd hh:mm:ss")}},computed:Object(s["a"])({},Object(n["c"])(["employeeId","employeeName"])),data:function(){var e=function(e,t,i){return t?isNaN(t)?i(new Error("只能输入整数和小数")):i():i(new Error("请输入培训时长"))};return{systemOptions:[],courseTypeOptions:o["a"].COURSE_TYPE,productOptions:[],trainTypeOptions:o["a"].TRAIN_TYPE,seriesOptions:[],searchForm:{system:"",courseType:"",product:"",trainName:"",series:""},trainForm:{trainId:"",trainName:"",description:"",series:"",system:"",courseType:"",trainType:"",trainTime:"",product:""},list:null,initList:[],listLoading:!1,page:{pageNum:1,pageSize:10,totalRecord:0},dialogTitle:"添加培训信息",dialogVisible:!1,isEdit:!1,isDetail:!1,rules:{trainName:[{required:!0,message:"请输入培训名称",trigger:"blur"}],description:[{required:!0,message:"请输入培训简介",trigger:"blur"}],series:[{required:!0,message:"请选择/输入所属系列",trigger:"change"}],system:[{required:!0,message:"请选择/输入所属体系",trigger:"change"}],courseType:[{required:!0,message:"请选择课程类型",trigger:"change"}],trainType:[{required:!0,message:"请选择培训类型",trigger:"change"}],trainTime:[{trigger:"blur",validator:e,required:!0}],product:[{required:!0,message:"请选择产品线",trigger:"change"}]}}},mounted:function(){this.getProductInfo(),this.getSeries(),this.getSystem(),this.getTrainingList()},methods:{handleCurrentChange:function(e){var t=this;t.page.currentPage=e,t.list=t.initList.slice((e-1)*t.page.pageSize,e*t.page.pageSize)},handleFilter:function(){this.page.currentPage=1,this.getTrainingList()},getTrainingList:function(){var e=this,t={bu:e.searchForm.product,series:e.searchForm.series,sort:e.searchForm.system,trainingName:e.searchForm.trainName,classType:e.searchForm.courseType};e.listLoading=!0,e.$store.dispatch("getTrainingList",t).then(function(t){t.success?(e.initList=t.data,e.list=e.initList.slice(0,e.page.pageSize),e.page.totalRecord=t.data.length):(e.list=[],e.page.totalRecord=0),e.listLoading=!1}).catch(function(){e.list=[],e.listLoading=!1})},handleCreate:function(){this.isEdit=!1,this.isDetail=!1,this.trainForm.trainId="",this.trainForm.trainName="",this.trainForm.description="",this.trainForm.series="",this.trainForm.system="",this.trainForm.courseType="",this.trainForm.trainType="",this.trainForm.trainTime="",this.trainForm.product="",this.dialogTitle="添加培训信息",this.dialogVisible=!0,this.clearValidate(),this.getProductInfo(),this.getSeries(),this.getSystem()},submtForm:function(){var e=this;e.$refs.trainForm.validate(function(t){if(!t)return!1;var i={bu:e.trainForm.product,series:e.trainForm.series,sort:e.trainForm.system,trainingName:e.trainForm.trainName,trainingDes:e.trainForm.description,trainingDuration:e.trainForm.trainTime,type:e.trainForm.trainType,classType:e.trainForm.courseType};e.isEdit?(i.trainingId=e.trainForm.trainId,i.updaterId=e.employeeId):i.creatorId=e.employeeId,e.$store.dispatch("saveTrainingInfo",i).then(function(t){t.success?(e.$message.success("操作成功"),e.dialogVisible=!1,e.getTrainingList(),e.getSeries(),e.getSystem()):"DuplicateName"===t.message?e.$message.error("名称已存在"):e.$message.error("操作失败")})})},clearValidate:function(){this.$refs.trainForm&&this.$refs.trainForm.clearValidate()},getProductInfo:function(){var e=this;e.$store.dispatch("getProductInfo").then(function(t){e.productOptions=t||[]})},getSeries:function(){var e=this;e.$store.dispatch("querySeries").then(function(t){t.data?e.seriesOptions=t.data.map(function(e){return{label:e,value:e}}):e.seriesOptions=[]})},getSystem:function(){var e=this;e.$store.dispatch("querySystem").then(function(t){t.success?e.systemOptions=t.data.map(function(e){return{label:e,value:e}}):e.systemOptions=[]})},handleDel:function(e){var t=this;t.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$store.dispatch("delTraining",e).then(function(e){e.success?(t.$message.success("操作成功"),t.getTrainingList()):t.$message.error("操作失败")})})},handleEdit:function(e){this.isDetail=!1,this.isEdit=!0,this.getProductInfo(),this.getSeries(),this.getSystem(),this.trainForm.trainId=e.trainingId,this.trainForm.trainName=e.trainingName,this.trainForm.description=e.trainingDes,this.trainForm.series=e.series,this.trainForm.system=e.sort,this.trainForm.courseType=e.classType,this.trainForm.trainType=e.type,this.trainForm.trainTime=e.trainingDuration,this.trainForm.product=e.bu,this.dialogTitle="修改培训信息",this.dialogVisible=!0,this.clearValidate()},handleDetail:function(e){this.trainForm.trainId=e.trainingId,this.trainForm.trainName=e.trainingName,this.trainForm.description=e.trainingDesc,this.trainForm.series=e.series,this.trainForm.system=e.sort,this.trainForm.courseType=e.classType,this.trainForm.trainType=e.type,this.trainForm.trainTime=e.trainingDuration,this.trainForm.product=e.bu,this.dialogTitle="查看培训信息",this.dialogVisible=!0,this.isEdit=!1,this.isDetail=!0,this.clearValidate()}}},m=c,u=(i("6766"),i("2877")),p=Object(u["a"])(m,r,a,!1,null,null,null);p.options.__file="trainSetting.vue";t["default"]=p.exports},"2f21":function(e,t,i){"use strict";var r=i("79e5");e.exports=function(e,t){return!!e&&r(function(){t?e.call(null,function(){},1):e.call(null)})}},"55dd":function(e,t,i){"use strict";var r=i("5ca1"),a=i("d8e8"),s=i("4bf8"),n=i("79e5"),l=[].sort,o=[1,2,3];r(r.P+r.F*(n(function(){o.sort(void 0)})||!n(function(){o.sort(null)})||!i("2f21")(l)),"Array",{sort:function(e){return void 0===e?l.call(s(this)):l.call(s(this),a(e))}})},6766:function(e,t,i){"use strict";var r=i("a970"),a=i.n(r);a.a},a970:function(e,t,i){}}]);
//# sourceMappingURL=chunk-6ce9c7ca.7eb84be3.js.map