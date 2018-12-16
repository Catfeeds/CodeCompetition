(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ce743cac"],{"09db":function(e,t,a){},5060:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container",staticStyle:{"padding-bottom":"0px"}},[a("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"所属体系"},model:{value:e.searchForm.system,callback:function(t){e.$set(e.searchForm,"system",t)},expression:"searchForm.system"}},e._l(e.systemOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})})),a("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"所属角色"},model:{value:e.searchForm.roleName,callback:function(t){e.$set(e.searchForm,"roleName",t)},expression:"searchForm.roleName"}},e._l(e.roleOptions,function(e){return a("el-option",{key:e.roleName,attrs:{label:e.roleName,value:e.roleName}})})),a("el-button",{staticClass:"filter-item",attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.$t("table.search")))]),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.handleAdd}},[e._v(e._s(e.$t("table.add")))])],1),a("el-tabs",{on:{"tab-click":e.changeTab},model:{value:e.activeTab,callback:function(t){e.activeTab=t},expression:"activeTab"}},[a("el-tab-pane",{attrs:{label:"关键角色设置",name:"role"}},[a("role-tab",{ref:"roleTab",attrs:{condition:e.searchForm}})],1),a("el-tab-pane",{attrs:{label:"考核规则设置",name:"rule"}},[a("rule-tab",{ref:"ruleTab",attrs:{condition:e.searchForm}})],1)],1)],1)},i=[],l=(a("7f7f"),a("be94")),s=a("2f62"),n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"role-container"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",fit:"",size:"mini",stripe:"","max-height":"375","highlight-current-row":""}},[a("el-table-column",{attrs:{"header-align":"center",align:"center",label:e.$t("table.id"),type:"index",width:"80"}}),a("el-table-column",{attrs:{"min-width":"150px","header-align":"center",label:"所属体系",prop:"system",sortable:""}}),a("el-table-column",{attrs:{"min-width":"150px","header-align":"center",label:"角色名称",prop:"roleName",sortable:""}}),a("el-table-column",{attrs:{"min-width":"100px","header-align":"center",label:"创建人",prop:"creatorName",sortable:""}}),a("el-table-column",{attrs:{"min-width":"150px","header-align":"center",label:"最后更新人",prop:"updateStaffName",sortable:""}}),a("el-table-column",{attrs:{"min-width":"150px","header-align":"center",label:"最后更新时间",prop:"updateTime",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("formatDate")(t.row.updateTime)))])]}}])}),a("el-table-column",{attrs:{align:"center",width:"80","header-align":"center",label:e.$t("table.option")},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-edit",title:"编辑"},on:{click:function(a){e.handleEdit(t.row)}}}),a("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-delete",title:"删除"},on:{click:function(a){e.handleDel(t.row.roleId)}}})]}}])})],1),a("el-row",{attrs:{type:"flex",justify:"end"}},[a("el-pagination",{attrs:{"current-page":e.page.currentPage,"page-size":e.page.pageSize,layout:"total, slot, prev, pager, next",total:e.page.totalRecord,"prev-text":"上一页","next-text":"下一页"},on:{"current-change":e.handleCurrentChange}})],1),a("el-dialog",{attrs:{title:e.dialogBaseTitle,visible:e.dialogBaseVisible,width:"30%"},on:{"update:visible":function(t){e.dialogBaseVisible=t}}},[a("el-form",{ref:"roleForm",attrs:{model:e.roleForm,size:"mini","label-width":"120px",rules:e.rules}},[a("el-form-item",{attrs:{label:"角色名称",prop:"roleName"}},[a("el-input",{attrs:{autocomplete:"off",required:"",maxlength:"64"},model:{value:e.roleForm.roleName,callback:function(t){e.$set(e.roleForm,"roleName",t)},expression:"roleForm.roleName"}})],1),a("el-form-item",{attrs:{label:"所属体系",prop:"system"}},[a("el-select",{attrs:{filterable:"","allow-create":"","default-first-option":"",placeholder:"请选择"},model:{value:e.roleForm.system,callback:function(t){e.$set(e.roleForm,"system",t)},expression:"roleForm.system"}},e._l(e.systemOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}))],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){e.dialogBaseVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){e.handleSubmit()}}},[e._v("确 定")])],1)],1)],1)},o=[],c=a("c466"),d={filters:{formatDate:function(e){var t=new Date(e);return Object(c["a"])(t,"yyyy-MM-dd hh:mm:ss")}},props:["condition"],computed:Object(l["a"])({},Object(s["c"])(["employeeId","employeeName"]),Object(s["e"])({systemOptions:function(e){return e.ruleStore.systemOptions}})),mounted:function(){this.getSystemInfo()},data:function(){return{loading:!1,tableData:[],initList:[],dialogBaseTitle:"添加关键角色",dialogBaseVisible:!1,isEdit:!1,page:{pageNum:1,pageSize:10,totalRecord:0},rules:{roleName:[{required:!0,message:"请输入角色名称",trigger:"blur"}],system:[{required:!0,message:"请选择所属体系",trigger:"change"}],product:[{required:!0,message:"请选择产品线",trigger:"change"}]},roleForm:{roleName:"",system:"",product:""}}},methods:Object(l["a"])({},Object(s["b"])(["getRoleInfo","getAllRole","getSystemInfo"]),{getRoleList:function(){var e=this;e.loading=!0,e.getRoleInfo(e.condition).then(function(t){t.success?(e.initList=t.data,e.tableData=e.initList.slice(0,e.page.pageSize),e.page.totalRecord=t.data.length):(e.initList=[],e.tableData=[],e.page.totalRecord=0),e.loading=!1}).catch(function(){e.initList=[],e.tableData=[],e.page.totalRecord=0,e.loading=!1})},handleCurrentChange:function(e){var t=this;t.page.currentPage=e,t.tableData=t.initList.slice((e-1)*t.page.pageSize,e*t.page.pageSize)},handleEdit:function(e){this.isEdit=!0,this.roleForm.roleId=e.roleId,this.roleForm.roleName=e.roleName,this.roleForm.system=e.system,this.dialogBaseTitle="编辑关键角色",this.dialogBaseVisible=!0},handleDel:function(e){var t=this;t.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$store.dispatch("delRoleInfo",e).then(function(e){e.success?(t.$message.success(e.message),t.getRoleList(),t.getAllRole()):t.$message.error(e.message)})})},handleFilter:function(){this.page.currentPage=1,this.getRoleList()},handleAdd:function(){this.roleForm.roleName="",this.roleForm.system="",this.isEdit=!1,this.dialogBaseTitle="添加关键角色",this.dialogBaseVisible=!0},handleSubmit:function(){var e=this;e.$refs.roleForm.validate(function(t){if(!t)return!1;var a={roleName:e.roleForm.roleName,system:e.roleForm.system,creatorId:e.employeeId};e.isEdit?(a.roleId=e.roleForm.roleId,a.updateStaffId=e.employeeId,a.updateStaffName=e.employeeName):(a.roleId=-1,a.creatorName=e.employeeName),e.$store.dispatch("saveRoleInfo",a).then(function(t){t.success?(e.$message.success(t.message),e.getRoleList(),e.getAllRole()):e.$message.error(t.message),e.dialogBaseVisible=!1})})}})},u=d,m=(a("9e37"),a("2877")),p=Object(m["a"])(u,n,o,!1,null,null,null);p.options.__file="role.vue";var f=p.exports,h=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",fit:"",size:"mini",stripe:"","max-height":"375","highlight-current-row":""}},[a("el-table-column",{attrs:{"header-align":"center",align:"center",label:e.$t("table.id"),type:"index",width:"80"}}),a("el-table-column",{attrs:{"min-width":"150px","header-align":"center",label:"所属体系",prop:"system",sortable:""}}),a("el-table-column",{attrs:{"min-width":"100px","header-align":"center",label:"所属角色",prop:"roleId",sortable:""}}),a("el-table-column",{attrs:{"min-width":"100px","header-align":"center",label:"职级",prop:"roleLevel",sortable:""}}),a("el-table-column",{attrs:{"min-width":"100px","header-align":"center",label:"创建人",prop:"creatorName",sortable:""}}),a("el-table-column",{attrs:{"min-width":"150px","header-align":"center",label:"最后更新人",prop:"updateStaffName",sortable:""}}),a("el-table-column",{attrs:{"min-width":"150px","header-align":"center",label:"最后更新时间",prop:"updateTime",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("formatDate")(t.row.updateTime)))])]}}])}),a("el-table-column",{attrs:{align:"center",width:"80","header-align":"center",label:e.$t("table.option")},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-view",title:"查看"},on:{click:function(a){e.handleView(t.row.ruleId)}}}),a("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-delete",title:"删除"},on:{click:function(a){e.handleDel(t.row.ruleId)}}})]}}])})],1),a("el-row",{attrs:{type:"flex",justify:"end"}},[a("el-pagination",{attrs:{"current-page":e.page.currentPage,"page-size":e.page.pageSize,layout:"total, slot, prev, pager, next",total:e.page.totalRecord,"prev-text":"上一页","next-text":"下一页"},on:{"current-change":e.handleCurrentChange}})],1),a("el-dialog",{attrs:{title:"添加考核规则",visible:e.dialogBaseVisible,width:"80%",top:"30px","custom-class":"rule-setting","close-on-click-modal":!1},on:{"update:visible":function(t){e.dialogBaseVisible=t}}},[a("el-steps",{attrs:{active:e.active,"finish-status":"success",simple:""}},[a("el-step",{attrs:{title:"参数设置"}}),a("el-step",{attrs:{title:"生成规则"}})],1),e.isSet?a("div",[a("el-form",{ref:"ruleForm",staticClass:"ruleForm-condition",staticStyle:{"margin-top":"10px"},attrs:{model:e.ruleForm,size:"mini","label-width":"100px",rules:e.rules,inline:"","inline-message":""}},[a("el-form-item",{attrs:{label:"所属体系",prop:"system"}},[a("el-select",{staticStyle:{width:"130px"},attrs:{"inline-message":"",placeholder:"请选择"},model:{value:e.ruleForm.system,callback:function(t){e.$set(e.ruleForm,"system",t)},expression:"ruleForm.system"}},e._l(e.systemOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}))],1),a("el-form-item",{attrs:{label:"所属角色",prop:"role"}},[a("el-select",{staticStyle:{width:"130px"},attrs:{placeholder:"请选择"},model:{value:e.ruleForm.role,callback:function(t){e.$set(e.ruleForm,"role",t)},expression:"ruleForm.role"}},e._l(e.roleOptions,function(e){return a("el-option",{key:e.roleId,attrs:{label:e.roleName,value:e.roleId}})}))],1),a("el-form-item",{attrs:{label:"职级",prop:"rank"}},[a("el-select",{staticStyle:{width:"130px"},attrs:{filterable:"","allow-create":"",placeholder:"请选择/输入"},model:{value:e.ruleForm.rank,callback:function(t){e.$set(e.ruleForm,"rank",t)},expression:"ruleForm.rank"}},e._l(e.rankOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}))],1)],1),a("el-row",{attrs:{gutter:12}},[a("el-col",{attrs:{span:14}},[a("el-card",{attrs:{shadow:"never"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[e._v("待选课程列表")]),a("div",{staticClass:"filter-container",staticStyle:{"padding-bottom":"0px"}},[a("el-input",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"培训名称"},model:{value:e.searchForm.trainName,callback:function(t){e.$set(e.searchForm,"trainName",t)},expression:"searchForm.trainName"}}),a("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"所属体系"},model:{value:e.searchForm.system,callback:function(t){e.$set(e.searchForm,"system",t)},expression:"searchForm.system"}},e._l(e.systemOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})})),a("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"所属系列"},model:{value:e.searchForm.series,callback:function(t){e.$set(e.searchForm,"series",t)},expression:"searchForm.series"}},e._l(e.seriesOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})})),a("el-button",{staticClass:"filter-item",attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:e.handleTrainFilter}},[e._v(e._s(e.$t("table.search")))])],1),a("el-table",{ref:"trainTable",staticStyle:{width:"100%"},attrs:{data:e.trainDataSource,border:"",fit:"",size:"mini",stripe:"",height:"187","tooltip-effect":"dark"},on:{"selection-change":e.handleTrainSelectionChange}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"45"}}),a("el-table-column",{attrs:{prop:"trainingName","header-align":"center",label:"培训名称","min-width":"130"}}),a("el-table-column",{attrs:{prop:"sort","header-align":"center",label:"所属体系",width:"110"}}),a("el-table-column",{attrs:{prop:"series","header-align":"center",label:"所属系列",width:"100"}}),a("el-table-column",{attrs:{prop:"classType","header-align":"center",label:"课程类型",width:"90"}}),a("el-table-column",{attrs:{prop:"type","header-align":"center",label:"培训类型",width:"90"}})],1)],1)],1),a("el-col",{attrs:{span:10}},[a("el-card",{attrs:{shadow:"never"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[e._v("其他考核事务列表")]),a("div",{staticClass:"filter-container",staticStyle:{"padding-bottom":"0px"}},[a("el-input",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"事务名称"},model:{value:e.searchForm.affairsName,callback:function(t){e.$set(e.searchForm,"affairsName",t)},expression:"searchForm.affairsName"}}),a("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"所属体系"},model:{value:e.searchForm.affairSystem,callback:function(t){e.$set(e.searchForm,"affairSystem",t)},expression:"searchForm.affairSystem"}},e._l(e.systemOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})})),a("el-button",{staticClass:"filter-item",attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:e.handleAffairFilter}},[e._v(e._s(e.$t("table.search")))])],1),a("el-table",{ref:"affairTable",staticStyle:{width:"100%"},attrs:{data:e.affairDataSource,border:"",fit:"",size:"mini",stripe:"",height:"187","tooltip-effect":"dark"},on:{"selection-change":e.handleAffairSelectionChange}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"45"}}),a("el-table-column",{attrs:{prop:"affairName","header-align":"center",label:"事务名称","min-width":"130"}}),a("el-table-column",{attrs:{prop:"system","header-align":"center",label:"所属体系",width:"100"}}),a("el-table-column",{attrs:{prop:"series","header-align":"center",label:"所属系列",width:"110"}})],1)],1)],1)],1)],1):e._e(),e.isResult?a("div",{staticClass:"score"},[a("el-row",{staticStyle:{"margin-top":"10px"},attrs:{gutter:12}},[a("el-col",{attrs:{span:14}},[a("el-card",{attrs:{shadow:"never"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[e._v("单项得分校验规则设置")]),a("el-form",{ref:"tableForm",attrs:{model:e.tableForm,"inline-message":"",size:"mini"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableForm.ruleDataSource,border:"",fit:"",size:"mini",stripe:"",height:"255","tooltip-effect":"dark"}},[a("el-table-column",{attrs:{"header-align":"center",align:"center",label:e.$t("table.id"),width:"50",type:"index"}}),a("el-table-column",{attrs:{prop:"trainName","header-align":"center",label:"培训名称",width:"200"}}),a("el-table-column",{attrs:{prop:"credit","header-align":"center",label:"最高得分","min-width":"130"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-form-item",{staticStyle:{"margin-bottom":"0px"},attrs:{prop:"ruleDataSource."+t.$index+".credit",rules:t.row.trainId>=0&&e.rules.score}},[a("el-input",{staticStyle:{width:"130px"},attrs:{disabled:t.row.trainId<0,size:"mini",clearable:"",placeholder:"请输入最高得分"},model:{value:t.row.credit,callback:function(a){e.$set(t.row,"credit",a)},expression:"scope.row.credit"}})],1)]}}])})],1)],1)],1)],1),a("el-col",{attrs:{span:10}},[a("el-card",{attrs:{shadow:"never"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[e._v("总分规则设置")]),a("el-form",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableForm.ruleDataSource,border:"",fit:"",size:"mini",stripe:"",height:"255","tooltip-effect":"dark"}},[a("el-table-column",{attrs:{"header-align":"center",align:"center",label:e.$t("table.id"),width:"50",type:"index"}}),a("el-table-column",{attrs:{prop:"trainName","header-align":"center",label:"培训名称",width:"200"}}),a("el-table-column",{attrs:{prop:"credit","header-align":"center",label:"最高得分","min-width":"130"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.trainId<0?a("el-form-item",{staticStyle:{"margin-bottom":"0px"},attrs:{prop:"ruleDataSource."+t.$index+".credit",rules:e.rules.score}},[a("el-input",{staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"请输入学分"},model:{value:t.row.credit,callback:function(a){e.$set(t.row,"credit",a)},expression:"scope.row.credit"}})],1):a("el-form-item",{staticStyle:{"margin-bottom":"0px"},attrs:{prop:"ruleDataSource."+t.$index+".credit"}},[a("el-input",{staticStyle:{width:"130px"},attrs:{size:"mini",disabled:""},model:{value:t.row.credit,callback:function(a){e.$set(t.row,"credit",a)},expression:"scope.row.credit"}})],1)]}}])})],1)],1)],1)],1)],1)],1):e._e(),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){e.dialogBaseVisible=!1}}},[e._v("取 消")]),e.isResult?a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.prev}},[e._v("上一步")]):e._e(),e.isSet?a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.next}},[e._v("下一步")]):e._e(),e.isResult?a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){e.handleSubmit()}}},[e._v("完 成")]):e._e()],1)],1),a("el-dialog",{attrs:{title:"查看考核规则",visible:e.dialogViewVisible,width:"50%"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.ruleList,border:"",fit:"",size:"mini",stripe:"","max-height":"185","tooltip-effect":"dark","highlight-current-row":""}},[a("el-table-column",{attrs:{"header-align":"center",align:"center",label:e.$t("table.id"),width:"80",type:"index"}}),a("el-table-column",{attrs:{prop:"itemName","header-align":"center",label:"培训名称",width:"150"}}),a("el-table-column",{attrs:{prop:"credit","header-align":"center",label:"学分",width:"100"}}),a("el-table-column",{attrs:{prop:"pass","header-align":"center",label:"及格分","show-overflow-tooltip":""}})],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){e.dialogViewVisible=!1}}},[e._v("关 闭")])],1)],1)],1)},b=[],g=(a("4f7f"),a("5df3"),a("1c4c"),a("ac6a"),a("c5f6"),{filters:{formatDate:function(e){var t=new Date(e);return Object(c["a"])(t,"yyyy-MM-dd hh:mm:ss")}},props:["condition"],data:function(){var e=this,t=function(t,a,r){if(!a)return r(new Error("请输入最高得分"));if(isNaN(a))return r(new Error("只能输入整数和小数"));if(Number(a)>100||Number(a)<0)return r(new Error("只能0~100的整数和小数"));if(t.field.indexOf(".credit")>=0){var i=0,l=e.tableForm.ruleDataSource.length-1;e.tableForm.ruleDataSource.map(function(e,t){t===l||isNaN(e.credit)||(i+=Number(e.credit))}),e.tableForm.ruleDataSource[l].credit=i}return r()};return{active:0,isSet:!0,isResult:!1,isInit:!0,loading:!1,tableData:[],initList:[],ruleList:[],rankOptions:[],selectedTrain:[],selectedAffair:[],dialogBaseVisible:!1,dialogViewVisible:!1,page:{pageNum:1,pageSize:10,totalRecord:0},ruleForm:{system:"",role:"",rank:""},searchForm:{affairsName:"",series:"",system:"",trainName:"",affairSystem:""},tableForm:{ruleDataSource:[]},rules:{system:[{required:!0,message:"请选择所属体系",trigger:"change"}],role:[{required:!0,message:"请选择所属角色",trigger:"change"}],rank:[{required:!0,message:"请选择/输入职级",trigger:"change"}],score:[{required:!0,validator:t,trigger:"blur"}]}}},computed:Object(l["a"])({},Object(s["c"])(["employeeId","employeeName"]),Object(s["e"])({roleOptions:function(e){return e.ruleStore.roleOptions},systemOptions:function(e){return e.ruleStore.systemOptions},seriesOptions:function(e){return e.ruleStore.seriesOptions},trainDataSource:function(e){return e.ruleStore.trainDataSource},affairDataSource:function(e){return e.ruleStore.affairDataSource}})),methods:Object(l["a"])({},Object(s["b"])(["getAllRole","getSystemInfo","getSeriesInfo","getAllTrain","getAllAffair"]),{next:function(){var e=this;e.isSet&&e.$refs.ruleForm.validate(function(t){if(!t)return!1;e.selectedTrain.length<=0&&e.selectedAffair.length<=0?e.$message.warning("培训课程和考核事务没有选择"):(e.active++,e.isSet=0===e.active,e.isResult=1===e.active)}),e.isResult&&(e.tableForm.ruleDataSource=e.selectedTrain.concat(e.selectedAffair).map(function(e){return{trainId:e.trainId,trainName:e.trainName,itemType:e.itemType,credit:"",passing:""}}),e.tableForm.ruleDataSource.push({trainId:-1,trainName:"总分",itemType:-1,credit:"",passing:""})),e.isSet&&setTimeout(function(){e.selectedTrain.length>0&&e.toggleSelection(e.selectedTrain,"trainTable"),e.selectedAffair.length>0&&e.toggleSelection(e.selectedAffair,"affairTable")},200)},prev:function(){var e=this;e.active--,e.isSet=0===e.active,e.isResult=!1,e.isSet&&setTimeout(function(){e.selectedTrain.length>0&&e.toggleSelection(e.selectedTrain,"trainTable"),e.selectedAffair.length>0&&e.toggleSelection(e.selectedAffair,"affairTable")},200)},toggleSelection:function(e,t){var a=this;e&&e.forEach(function(e){a.$refs[t].toggleRowSelection(e)})},getTrainInfo:function(){this.getAllTrain({bu:"",series:this.searchForm.series,sort:this.searchForm.system,trainingName:this.searchForm.trainName,classType:""})},getAffairInfo:function(){this.getAllAffair({du:"",series:"",system:this.searchForm.affairSystem,affairName:this.searchForm.affairsName})},getRuleList:function(){var e=this;e.loading=!0,e.$store.dispatch("getRuleList",e.condition).then(function(t){t.success?(e.initList=t.data,e.rankOptions=Array.from(new Set(t.data.map(function(e){return e.roleLevel}))),e.tableData=e.initList.slice(0,e.page.pageSize),e.page.totalRecord=t.data.length):(e.tableData=[],e.page.totalRecord=0),e.loading=!1}).catch(function(){e.tableData=[],e.loading=!1})},handleCurrentChange:function(e){var t=this;t.page.currentPage=e,t.tableData=t.initList.slice((e-1)*t.page.pageSize,e*t.page.pageSize)},handleView:function(e){var t=this;t.dialogViewVisible=!0,t.$store.dispatch("getRuleInfoById",e).then(function(e){e.success?t.ruleList=e.data:t.ruleList=[]}).catch(function(){t.ruleList=[]})},handleDel:function(e){var t=this;t.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$store.dispatch("delRuleInfo",e).then(function(e){e.success?(t.$message.success("操作成功"),t.getRuleList()):t.$message.error("操作失败")})})},handleFilter:function(){this.page.currentPage=1,this.getRuleList()},handleTrainFilter:function(){this.getTrainInfo()},handleAffairFilter:function(){this.getAffairInfo()},handleAdd:function(){var e=this;e.searchForm.trainName="",e.searchForm.system="",e.searchForm.series="",e.searchForm.affairsName="",e.searchForm.affairSystem="",e.ruleForm.system="",e.ruleForm.role="",e.ruleForm.rank="",e.getTrainInfo(),e.getAffairInfo(),e.getSystemInfo(),e.getSeriesInfo(),e.active=0,e.isSet=!0,e.isResult=!1,e.isEdit=!1,e.dialogBaseVisible=!0},handleSubmit:function(){var e=this;e.$refs.tableForm.validate(function(t){if(!t)return!1;e.active>0&&(e.active=3);var a={system:e.ruleForm.system,oneRule:{ruleId:-1,roleId:1*e.ruleForm.role,roleLevel:e.ruleForm.rank,creatorId:e.employeeId,creatorName:e.employeeName},singleRuleInfo:e.tableForm.ruleDataSource.filter(function(e){return e.trainId>0}).map(function(e){return{itemId:e.trainId,itemType:e.itemType,itemName:e.trainName,credit:1*e.credit,pass:1*e.passing}})};e.$store.dispatch("addRuleInfo",a).then(function(t){t.success?(e.$message.success("规则添加成功"),e.getRuleList()):e.$message.success("规则添加失败"),e.selectedTrain=[],e.selectedAffair=[],e.dialogBaseVisible=!1})})},handleTrainSelectionChange:function(e){this.isInit=!1,this.selectedTrain=e.map(function(e){return e.trainId=e.trainingId,e.trainName=e.trainingName,e.itemType=0,e})},handleAffairSelectionChange:function(e){this.isInit=!1,this.selectedAffair=e.map(function(e){return e.trainId=e.affairID,e.trainName=e.affairName,e.itemType=1,e})}})}),y=g,v=(a("7951"),Object(m["a"])(y,h,b,!1,null,null,null));v.options.__file="rule.vue";var w=v.exports,S={name:"AssessmentRules",components:{RoleTab:f,RuleTab:w},data:function(){return{activeTab:"role",searchForm:{system:"",roleName:""}}},mounted:function(){this.getSystemInfo(),this.getAllRole(),this.$refs.roleTab.handleFilter()},computed:Object(l["a"])({},Object(s["e"])({roleOptions:function(e){return e.ruleStore.roleOptions},systemOptions:function(e){return e.ruleStore.systemOptions}})),methods:Object(l["a"])({},Object(s["b"])(["getAllRole","getSystemInfo"]),{changeTab:function(e,t){"role"===e.name?this.$refs.roleTab.handleFilter():this.$refs.ruleTab.handleFilter()},handleAdd:function(){"role"===this.activeTab?this.$refs.roleTab.handleAdd():this.$refs.ruleTab.handleAdd()},handleFilter:function(){"role"===this.activeTab?this.$refs.roleTab.handleFilter():this.$refs.ruleTab.handleFilter()}})},F=S,x=(a("9d90"),Object(m["a"])(F,r,i,!1,null,null,null));x.options.__file="rulesSetting.vue";t["default"]=x.exports},7951:function(e,t,a){"use strict";var r=a("9d40"),i=a.n(r);i.a},"929d":function(e,t,a){},"9d40":function(e,t,a){},"9d90":function(e,t,a){"use strict";var r=a("09db"),i=a.n(r);i.a},"9e37":function(e,t,a){"use strict";var r=a("929d"),i=a.n(r);i.a}}]);
//# sourceMappingURL=chunk-ce743cac.fef44c6b.js.map