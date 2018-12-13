(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-668df8a0"],{"656c":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{staticClass:"search-condition",attrs:{inline:!0,model:e.searchForm}},[a("el-row",[a("el-col",{attrs:{span:4}},[a("el-form-item",[a("el-select",{attrs:{size:"mini",clearable:"",placeholder:"产品线"},on:{change:e.productChange},model:{value:e.searchForm.product,callback:function(t){e.$set(e.searchForm,"product",t)},expression:"searchForm.product"}},e._l(e.searchForm.productOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",[a("el-select",{attrs:{size:"mini",clearable:"",placeholder:"DU"},on:{change:e.changeDU},model:{value:e.searchForm.du,callback:function(t){e.$set(e.searchForm,"du",t)},expression:"searchForm.du"}},e._l(e.searchForm.duOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",[a("el-select",{attrs:{size:"mini",clearable:"",placeholder:"PDU"},model:{value:e.searchForm.pdu,callback:function(t){e.$set(e.searchForm,"pdu",t)},expression:"searchForm.pdu"}},e._l(e.searchForm.pduOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",[a("el-input",{attrs:{size:"mini",clearable:"",placeholder:"项目组名称"},model:{value:e.searchForm.teamName,callback:function(t){e.$set(e.searchForm,"teamName",t)},expression:"searchForm.teamName"}})],1)],1),a("el-col",{attrs:{span:4}},[a("el-form-item",[a("el-input",{attrs:{size:"mini",clearable:"",placeholder:"PM"},model:{value:e.searchForm.pm,callback:function(t){e.$set(e.searchForm,"pm",t)},expression:"searchForm.pm"}})],1)],1),a("el-col",{attrs:{span:2}},[a("el-form-item",[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:e.onSearchForm}},[e._v(e._s(e.$t("table.search")))])],1)],1),a("el-col",{attrs:{span:2}},[a("el-form-item",[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.onCreate}},[e._v(e._s(e.$t("table.add")))])],1)],1)],1)],1),a("div",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticStyle:{width:"100%"},attrs:{data:e.dataTable,border:"",fit:"",size:"mini",stripe:"","highlight-current-row":"","max-height":"420"}},[a("el-table-column",{attrs:{"header-align":"center",align:"center",type:"index",width:"80",sortable:"true",label:e.$t("table.id")}}),a("el-table-column",{attrs:{"min-width":"120px","header-align":"center",label:e.$t("projectGroup.product"),sortable:"",prop:"bu"}}),a("el-table-column",{attrs:{"min-width":"120px","header-align":"center",prop:"du",label:"DU",sortable:""}}),a("el-table-column",{attrs:{"min-width":"150px","header-align":"center",prop:"pdu",label:"PDU",sortable:""}}),a("el-table-column",{attrs:{label:e.$t("projectGroup.teamName"),"header-align":"center","min-width":"150",sortable:"",prop:"projectName"}}),a("el-table-column",{attrs:{label:"PM","header-align":"center","min-width":"110",sortable:"",prop:"pmName"}}),a("el-table-column",{attrs:{align:"center",label:e.$t("table.option"),width:"80","header-align":"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-setting",title:"团队设置"},on:{click:function(a){e.teamSettings(t.row.projectID)}}}),e._e(),a("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-delete",title:"删除"},on:{click:function(a){e.projectDelete(t.row.projectID)}}})]}}])})],1)],1),a("el-row",{attrs:{type:"flex",justify:"end"}},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"total, slot, prev, pager, next",total:e.totalRecord,"prev-text":"上一页","next-text":"下一页"},on:{"current-change":e.handleCurrentChange}})],1),a("el-dialog",{attrs:{title:e.dialogTitle,width:"30%",visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-form",{ref:"newForm",staticClass:"pg-form-wrap",attrs:{model:e.newForm,rules:e.rules,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"产品线",prop:"product"}},[a("el-select",{staticStyle:{width:"220px"},attrs:{size:"mini",placeholder:"产品线"},on:{change:e.newProductChange},model:{value:e.newForm.product,callback:function(t){e.$set(e.newForm,"product",t)},expression:"newForm.product"}},e._l(e.newForm.productOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),a("el-form-item",{attrs:{label:"DU",prop:"du"}},[a("el-select",{staticStyle:{width:"220px"},attrs:{size:"mini",placeholder:"DU"},on:{change:e.newDUChange},model:{value:e.newForm.du,callback:function(t){e.$set(e.newForm,"du",t)},expression:"newForm.du"}},e._l(e.newForm.duOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),a("el-form-item",{attrs:{label:"PDU",prop:"pdu"}},[a("el-select",{staticStyle:{width:"220px"},attrs:{size:"mini",placeholder:"PDU"},on:{change:e.newPDUChange},model:{value:e.newForm.pdu,callback:function(t){e.$set(e.newForm,"pdu",t)},expression:"newForm.pdu"}},e._l(e.newForm.pduOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),a("el-form-item",{attrs:{label:"项目组名称",prop:"teamName"}},[a("el-input",{staticStyle:{width:"220px"},attrs:{size:"mini",placeholder:"项目组名称"},model:{value:e.newForm.teamName,callback:function(t){e.$set(e.newForm,"teamName",t)},expression:"newForm.teamName"}})],1),a("el-form-item",{attrs:{label:"PM",prop:"employeeId"}},[a("el-select",{staticStyle:{width:"220px"},attrs:{size:"mini",placeholder:"PM"},model:{value:e.newForm.employeeId,callback:function(t){e.$set(e.newForm,"employeeId",t)},expression:"newForm.employeeId"}},e._l(e.newForm.employeeOptions,function(t){return a("el-option",{key:t.staffID,attrs:{label:t.staffName,value:t.staffID}},[a("span",{staticStyle:{float:"left"}},[e._v(e._s(t.staffName))]),a("span",{staticStyle:{float:"right",color:"#8492a6","font-size":"13px"}},[e._v(e._s(t.staffID))])])}))],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){e.onSave()}}},[e._v("确 定")])],1)],1)],1)},o=[],n=(a("7514"),a("be94")),l=(a("cadf"),a("551c"),a("097d"),a("2f62")),i={data:function(){return{listLoading:!1,dialogTitle:"",dialogVisible:!1,pageSize:10,dataTable:[],totalRecord:0,currentPage:1,activeTab:"PO",projectID:"",rules:{product:[{required:!0,message:"请选择产品线",trigger:"change"}],du:[{required:!0,message:"请选择DU",trigger:"change"}],pdu:[{required:!0,message:"请选择PDU",trigger:"change"}],employeeId:[{required:!0,message:"请选择PM",trigger:"change"}],teamName:[{required:!0,message:"请输入项目组名称",trigger:"blur"},{min:3,max:50,message:"长度在 3 到 50 个字符",trigger:"blur"}]}}},mounted:function(){var e=this;this.getPGProductInfo(),this.getNewFormProductInfo(),this.getProjectGroupInfo({pageSize:e.pageSize}).then(function(){e.listLoading=!1}).catch(function(){e.listLoading=!1})},computed:Object(n["a"])({},Object(l["e"])({dataSource:function(e){return e.projectGroupStore.dataSource},searchForm:function(e){return e.projectGroupStore.searchForm},newForm:function(e){return e.projectGroupStore.newForm}})),watch:{dataSource:function(e){this.dataTable=e.datas,this.totalRecord=e.totleSize}},methods:Object(n["a"])({},Object(l["b"])(["getPGProductInfo","getPGDU","getPGPDUList","getProjectGroupInfo","getNewFormProductInfo","getNewFormDU","getNewFormPDUList","queryProjectManagers"]),{onSearchForm:function(e,t){var a=this;a.listLoading=!0,a.currentPage=t||1,this.getProjectGroupInfo({pageNo:a.currentPage,pageSize:a.pageSize}).then(function(){a.listLoading=!1})},onCreate:function(){this.newForm.product="",this.newForm.du="",this.newForm.pdu="",this.newForm.teamName="",this.newForm.employeeName="",this.newForm.employeeId="",this.dialogTitle="添加项目组",this.dialogVisible=!0},productChange:function(){this.getPGDU()},changeDU:function(){this.getPGPDUList()},newProductChange:function(){this.getNewFormDU()},newDUChange:function(){this.getNewFormPDUList()},newPDUChange:function(){this.queryProjectManagers()},handleCurrentChange:function(e){var t=this;t.currentPage=e,t.onSearchForm(null,e)},teamSettings:function(e){console.log(this.$store.getters.visitedViews),this.$router.push({path:"teamSettings/"+e})},projectEdit:function(e){this.newForm.product=e.product,this.newForm.du=e.du,this.newForm.pdu=e.pdu,this.newForm=e.teamName,this.newForm=e.pm,this.dialogTitle="编辑项目组",this.dialogVisible=!0},projectDelete:function(e){var t=this;t.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$store.dispatch("delProjectInfo",e).then(function(a){if(a.success){t.$message.success("项目删除成功"),t.onSearchForm(null,t.currentPage);var r=t.$store.getters.visitedViews.find(function(t){return t.path.indexOf("projectManagement/teamSettings/"+e)>=0});t.$store.dispatch("delView",r)}else t.$message.error("项目删除失败")})})},onSave:function(){var e=this;e.$refs.newForm.validate(function(t){if(!t)return!1;var a={pmID:e.newForm.employeeId,pmName:e.newForm.employeeOptions.find(function(t){return t.staffID===e.newForm.employeeId}).staffName,bu:e.newForm.product,du:e.newForm.du,pdu:e.newForm.pdu,projectName:e.newForm.teamName};e.$store.dispatch("addProjectInfo",a).then(function(t){t.success?(e.$message.success("项目组添加成功"),e.onSearchForm(null,e.currentPage)):e.$message.error("项目组添加失败"),e.dialogVisible=!1})})}})},s=i,c=(a("f3f3"),a("2877")),u=Object(c["a"])(s,r,o,!1,null,null,null);u.options.__file="projectGroupConfig.vue";t["default"]=u.exports},b175:function(e,t,a){},f3f3:function(e,t,a){"use strict";var r=a("b175"),o=a.n(r);o.a}}]);
//# sourceMappingURL=chunk-668df8a0.b02ce5f1.js.map