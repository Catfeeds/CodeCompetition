(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7e57fec7"],{"140c":function(e,t,a){},6869:function(e,t,a){"use strict";var o=a("140c"),r=a.n(o);r.a},7048:function(e,t,a){"use strict";var o=a("7874"),r=a.n(o);r.a},7874:function(e,t,a){},"83db":function(e,t,a){},"87b2":function(e,t,a){"use strict";var o=a("83db"),r=a.n(o);r.a},"9b25":function(e,t,a){"use strict";var o=a("b54d"),r=a.n(o);r.a},b54d:function(e,t,a){},e3cc:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container team-settings"},[a("el-tabs",{on:{"tab-click":e.changeTab},model:{value:e.activeTab,callback:function(t){e.activeTab=t},expression:"activeTab"}},[a("el-tab-pane",{attrs:{label:"人员设置",name:"employee"}},[a("emp-settings",{ref:"employeeSetting",attrs:{teamInfo:e.postForm}})],1),a("el-tab-pane",{attrs:{label:"关联PO",name:"PO"}},[a("associated-p-o",{ref:"associatedPO",attrs:{teamInfo:e.postForm}})],1),a("el-tab-pane",{attrs:{label:"关键角色",name:"keyRole"}},[a("key-role",{ref:"keyRole",attrs:{teamInfo:e.postForm}})],1)],1)],1)},r=[],n=(a("7f7f"),a("f751"),a("cadf"),a("551c"),a("097d"),function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"ztree tree-wrap-border",staticStyle:{height:"400px"},attrs:{id:"empRelationTree"}})]),a("el-col",{staticClass:"em-list tree-wrap-border",staticStyle:{width:"74%"},attrs:{span:18}},[a("el-form",{ref:"searchForm",staticClass:"em-wrap",attrs:{inline:!0,model:e.searchForm}},[a("el-form-item",{attrs:{label:""}},[a("el-input",{staticStyle:{width:"193px"},attrs:{placeholder:"PDU",size:"mini",disabled:""},model:{value:e.teamInfo.pdu,callback:function(t){e.$set(e.teamInfo,"pdu",t)},expression:"teamInfo.pdu"}})],1),a("el-form-item",{attrs:{label:""}},[a("el-select",{attrs:{size:"mini",clearable:"",placeholder:"性别"},model:{value:e.searchForm.gender,callback:function(t){e.$set(e.searchForm,"gender",t)},expression:"searchForm.gender"}},[a("el-option",{attrs:{label:"男",value:"0"}}),a("el-option",{attrs:{label:"女",value:"1"}})],1)],1),a("el-form-item",{attrs:{label:""}},[a("el-select",{attrs:{size:"mini",clearable:"",placeholder:"地域"},on:{change:e.regionChange},model:{value:e.region,callback:function(t){e.region=t},expression:"region"}},e._l(e.regionOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}))],1),a("el-form-item",{attrs:{label:""}},[a("el-select",{attrs:{size:"mini",clearable:"",placeholder:"成本中心"},model:{value:e.costCenter,callback:function(t){e.costCenter=t},expression:"costCenter"}},e._l(e.costCenterOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}))],1),a("el-form-item",{attrs:{label:""}},[a("el-input",{staticStyle:{width:"193px"},attrs:{placeholder:"姓名",size:"mini",clearable:""},model:{value:e.searchForm.employeeName,callback:function(t){e.$set(e.searchForm,"employeeName",t)},expression:"searchForm.employeeName"}})],1),a("el-form-item",{attrs:{label:""}},[a("el-input",{staticStyle:{width:"193px"},attrs:{placeholder:"工号",size:"mini",clearable:""},model:{value:e.searchForm.employeeID,callback:function(t){e.$set(e.searchForm,"employeeID",t)},expression:"searchForm.employeeID"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:function(t){e.handleFilter()}}},[e._v(e._s(e.$t("table.search")))])],1)],1),a("div",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"ztree",staticStyle:{height:"270px"},attrs:{id:"empListTree"}}),a("el-row",{attrs:{type:"flex",justify:"end"}},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"slot,prev, pager, next",total:e.total,"prev-text":"上一页","next-text":"下一页"},on:{"size-change":e.handleSizeChange,"current-change":e.handleSizeChange,"update:currentPage":function(t){e.currentPage=t}}})],1)],1)],1),a("el-row",{staticStyle:{"margin-top":"20px"},attrs:{type:"flex",justify:"end"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-circle-plus-outline",size:"mini"},on:{click:e.handleSave}},[e._v("保 存")])],1)],1)}),l=[],i=a("be94"),s=(a("2654"),a("2f62")),c={props:["teamInfo"],data:function(){return{loading:!1,currentTreeId:"",currentPage:1,total:0,pageSize:100,employeeDataSource:[],employees:[],searchForm:{gender:"",employeeName:"",employeeID:""},relationSetting:{view:{showIcon:!1},edit:{enable:!0,showRemoveBtn:!1,showRenameBtn:!1,drag:{prev:!1,next:!1,inner:!0}},data:{simpleData:{enable:!0}},callback:{beforeDrag:this.beforeDrag1,beforeDrop:this.beforeDrop1}},empListSetting:{edit:{enable:!0,showRemoveBtn:!1,showRenameBtn:!1},data:{simpleData:{enable:!0}},callback:{beforeDrag:this.beforeDrag2,beforeDrop:this.beforeDrop2}}}},computed:Object(i["a"])({},Object(s["e"])({regionOptions:function(e){return e.eSettingsStore.regionData},costCenterOptions:function(e){return e.eSettingsStore.costCenterData}}),{region:{get:function(){return this.$store.state.eSettingsStore.region},set:function(e){this.$store.commit("updateTMRegino",e)}},costCenter:{get:function(){return this.$store.state.eSettingsStore.costCenter},set:function(e){this.$store.commit("updateTMCostCenter",e)}}}),mounted:function(){this.teamInfo&&this.renderEmpRelationTree(this.teamInfo)},watch:{teamInfo:function(e){this.renderEmpRelationTree(e)},employees:function(e){$.fn.zTree.init($("#empListTree"),this.empListSetting,e.map(function(e){return{id:e.staffId,employeeName:e.staffName,name:e.staffName+" ("+e.staffId+")"}}))}},methods:Object(i["a"])({},Object(s["b"])(["getAreaAndCuBycondition","getTMEmployees","getEmployeesByTeam","saveTeamInfos"]),{beforeDrag1:function(e,t){return this.currentTreeId=e,!t[0].disDrop},beforeDrop1:function(e,t,a,o){return!a&&this.currentTreeId!==e},beforeDrag2:function(e,t){return this.currentTreeId=e,!0},beforeDrop2:function(e,t,a,o){return a.level>0?(this.$message.warning("只能拖动到项目组名称(一级节点)上面"),!1):!!a&&this.currentTreeId!==e},handleSizeChange:function(e){this.total=this.employeeDataSource.length,this.employees=this.employeeDataSource.slice(100*(this.currentPage-1),100*this.currentPage+1)},regionChange:function(){this.$store.commit("updateCostCenterData")},handleFilter:function(){var e=this,t=$.fn.zTree.getZTreeObj("empRelationTree"),a=t.getNodesByFilter(function(e){return e.level>0});e.currentPage=1,e.searchForm.pdu=e.teamInfo.pdu,e.loading=!0,e.getTMEmployees(e.searchForm).then(function(t){t.success?e.employeeDataSource=t.data.filter(function(e){return!_.contains(_.pluck(a,"id"),e.staffId)}):e.employeeDataSource=[],e.loading=!1,e.handleSizeChange()})},handleSave:function(){var e=this,t=$.fn.zTree.getZTreeObj("empRelationTree");if(t){var a=t.getNodesByFilter(function(e){return e.level>0}),o=a.map(function(t){return{teamId:e.teamInfo.projectID+"",teamName:e.teamInfo.projectName,staffId:t.id+"",staffName:t.employeeName}});e.saveTeamInfos(o).then(function(t){t.success?e.$message.success("人员设置保存成功"):e.$message.error("人员设置保存失败")})}},renderEmpRelationTree:function(e){var t=this;t.zNodes=[],t.zNodes.push({id:1,pId:0,open:!0,disDrop:!0,name:(e.projectName||"项目组名称")+"（"+(e.pmName||"PM姓名")+"）"}),t.getEmployeesByTeam(e.projectID).then(function(e){e.success&&(t.zNodes[0].children=e.data.map(function(e){return{id:e.staffId,employeeName:e.staffName,name:e.staffName+" ("+e.staffId+")"}})),$.fn.zTree.init($("#empRelationTree"),t.relationSetting,t.zNodes),t.handleFilter()})}})},d=c,u=(a("6869"),a("2877")),p=Object(u["a"])(d,n,l,!1,null,null,null);p.options.__file="employeeSettings.vue";var m=p.exports,f=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-form",{attrs:{inline:!0}},[a("el-form-item",[a("el-date-picker",{attrs:{type:"daterange","range-separator":"至",size:"mini","start-placeholder":"立项开始日期","end-placeholder":"立项结束日期"},model:{value:e.projectDate,callback:function(t){e.projectDate=t},expression:"projectDate"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleFilter}},[e._v(e._s(e.$t("table.search")))]),a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.handleAdd}},[e._v(e._s(e.$t("table.add")))])],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:e.tableData,"max-height":"250",border:"",fit:"",size:"mini",stripe:"","highlight-current-row":""}},[a("el-table-column",{attrs:{fixed:"",type:"index",sortable:"","header-align":"center",align:"center",width:"60",label:e.$t("table.id")}}),a("el-table-column",{attrs:{prop:"bu",sortable:"","header-align":"center",width:"120",label:"产品线"}}),a("el-table-column",{attrs:{prop:"du",sortable:"","header-align":"center",width:"120",label:"DU"}}),a("el-table-column",{attrs:{prop:"pdu",sortable:"","header-align":"center",width:"120",label:"PDU"}}),a("el-table-column",{attrs:{prop:"teamName",sortable:"","header-align":"center",width:"120",label:"项目组名称"}}),a("el-table-column",{attrs:{prop:"pmName",sortable:"","header-align":"center",label:"PM"}}),a("el-table-column",{attrs:{prop:"projectName",sortable:"","header-align":"center","min-width":"150",label:"关联PO名称"}}),a("el-table-column",{attrs:{prop:"knotTime","header-align":"center",sortable:"",width:"80",label:"状态"}}),a("el-table-column",{attrs:{prop:"startTime",sortable:"","header-align":"center",width:"100",label:"立项时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("formatDate")(t.row.startTime)))])]}}])}),a("el-table-column",{attrs:{prop:"endTime",sortable:"","header-align":"center",width:"100",label:"结项时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("formatDate")(t.row.endTime)))])]}}])}),a("el-table-column",{attrs:{"header-align":"center",align:"center",label:e.$t("table.option"),width:"50"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-delete",title:"删除"},on:{click:function(a){e.handleDel(t.row.projectId,t.row.teamId)}}})]}}])})],1),a("el-row",{attrs:{type:"flex",justify:"end"}},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"total, slot,prev, pager, next",total:e.total,"prev-text":"上一页","next-text":"下一页"},on:{"current-change":e.handleSizeChange,"update:currentPage":function(t){e.currentPage=t}}})],1),a("el-dialog",{attrs:{title:"选择PO信息",visible:e.createVisible,width:"60%"},on:{"update:visible":function(t){e.createVisible=t}}},[a("el-form",{ref:"searchForm",attrs:{model:e.searchForm,inline:"",size:"mini"}},[a("el-form-item",{attrs:{prop:"product"}},[a("el-input",{staticStyle:{width:"100px"},attrs:{disabled:""},model:{value:e.teamInfo.bu,callback:function(t){e.$set(e.teamInfo,"bu",t)},expression:"teamInfo.bu"}})],1),a("el-form-item",{attrs:{prop:"du"}},[a("el-select",{staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"DU"},on:{change:e.changeDU},model:{value:e.searchForm.du,callback:function(t){e.$set(e.searchForm,"du",t)},expression:"searchForm.du"}},e._l(e.searchForm.duOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),a("el-form-item",{attrs:{prop:"pdu"}},[a("el-select",{staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"PDU"},model:{value:e.searchForm.pdu,callback:function(t){e.$set(e.searchForm,"pdu",t)},expression:"searchForm.pdu"}},e._l(e.searchForm.pduOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),a("el-form-item",{attrs:{prop:"po"}},[a("el-input",{staticStyle:{width:"130px"},attrs:{placeholder:"合同编号"},model:{value:e.searchForm.poID,callback:function(t){e.$set(e.searchForm,"poID",t)},expression:"searchForm.poID"}})],1),a("el-form-item",{attrs:{prop:"poName"}},[a("el-input",{staticStyle:{width:"130px"},attrs:{placeholder:"合同名称"},model:{value:e.searchForm.poName,callback:function(t){e.$set(e.searchForm,"poName",t)},expression:"searchForm.poName"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handlePOFilter}},[e._v(e._s(e.$t("table.search")))])],1)],1),a("el-table",{ref:"multiTable",staticStyle:{width:"100%"},attrs:{data:e.PODataSource,border:"",fit:"",size:"mini",stripe:"",height:"260","tooltip-effect":"dark"},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"45"}}),a("el-table-column",{attrs:{prop:"du","header-align":"center",label:"DU",width:"120"}}),a("el-table-column",{attrs:{prop:"pdu","header-align":"center",label:"PDU",width:"120"}}),a("el-table-column",{attrs:{prop:"projectName","header-align":"center",label:"PO名称","min-width":"130"}}),a("el-table-column",{attrs:{prop:"startDate","header-align":"center",label:"立项时间",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("formatDate")(t.row.startDate)))])]}}])}),a("el-table-column",{attrs:{prop:"endDate","header-align":"center",label:"结项时间",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("formatDate")(t.row.endDate)))])]}}])})],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){e.createVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){e.handleConfirm()}}},[e._v("确 定")])],1)],1)],1)},h=[],b=a("c466"),g={props:["teamInfo"],filters:{formatDate:function(e){var t=new Date(e);return Object(b["a"])(t,"yyyy-MM-dd")}},data:function(){return{projectDate:"",tableData:[],dataSource:[],PODataSource:[],pageSize:100,currentPage:1,loading:!1,total:0,createVisible:!1,selectIds:[]}},computed:Object(i["a"])({},Object(s["e"])({searchForm:function(e){return e.associatedPOStore.searchForm}})),methods:Object(i["a"])({},Object(s["b"])(["getAllPOInfo","getPrjectRelatedPO","getPOFormDU","getPOFormPDUList"]),{handleFilter:function(e,t){var a=this;a.loading=!0;var o={teamId:t?t.projectID+"":a.teamInfo.projectID+"",startDate:"",endDate:""};a.projectDate&&(o.startDate=Object(b["a"])(a.projectDate[0],"yyyy-MM-dd"),o.endDate=Object(b["a"])(a.projectDate[1],"yyyy-MM-dd")),a.getPrjectRelatedPO(o).then(function(e){e.success?a.dataSource=e.data:a.dataSource=[],a.loading=!1,a.handleSizeChange()})},handleSizeChange:function(){var e=this;e.total=e.dataSource.length,e.tableData=e.dataSource.slice((e.currentPage-1)*e.pageSize,e.currentPage*e.pageSize)},handlePOFilter:function(){var e=this;this.selectIds=[],this.getAllPOInfo().then(function(t){t.success?e.PODataSource=t.data:e.PODataSource=[]}).catch(function(){e.PODataSource=[]})},handleAdd:function(){this.searchForm.poID="",this.searchForm.poName="",this.searchForm.product=this.teamInfo.bu,this.searchForm.du=this.teamInfo.du,this.searchForm.pdu=this.teamInfo.pdu,this.createVisible=!0,this.getPOFormDU(),this.handlePOFilter()},handleDel:function(e,t){var a=this;a.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){a.$store.dispatch("delTeamRelatedPO",{teamId:t,projectId:e}).then(function(e){e.success?(a.$message.success("操作成功"),a.handleFilter()):a.$message.error("操作失败")})})},handleProjectApproval:function(e,t){},handleConfirm:function(){var e=this;this.selectIds.length<=0?this.$message.waring("请选择要关联的PO信息"):this.$store.dispatch("teamRelatedPO",{teamId:this.teamInfo.projectID+"",projectId:this.selectIds}).then(function(t){t.success?(e.$message.success("项目关联PO成功"),e.handleFilter()):e.$message.error("项目关联PO失败"),e.createVisible=!1})},changeDU:function(){this.searchForm.pdu="",this.getPOFormPDUList()},handleSelectionChange:function(e){this.selectIds=e.map(function(e){return e.projectId})}})},y=g,I=(a("87b2"),Object(u["a"])(y,f,h,!1,null,null,null));I.options.__file="associatedPO.vue";var v=I.exports,S=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-form",{attrs:{inline:!0}},[a("el-row",[a("el-col",{attrs:{span:4}},[a("el-form-item",[a("el-select",{attrs:{filterable:"",size:"mini",placeholder:"PO"},on:{change:e.changePO},model:{value:e.searchForm.poId,callback:function(t){e.$set(e.searchForm,"poId",t)},expression:"searchForm.poId"}},e._l(e.searchForm.poOptions,function(e){return a("el-option",{key:e.projectId,attrs:{label:e.projectName,value:e.projectId}})}))],1)],1),a("el-col",{attrs:{span:7}},[a("el-form-item",[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.onCreate}},[e._v(e._s(e.$t("table.add")))])],1)],1)],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",fix:"",size:"mini",stripe:"","span-method":e.arraySpanMethod}},[a("el-table-column",{attrs:{fixed:"",type:"index",sortable:"","header-align":"center",align:"center",width:"60",label:e.$t("table.id")}}),a("el-table-column",{attrs:{prop:"poRoleName","header-align":"center",label:"关键角色"}}),a("el-table-column",{attrs:{prop:"amount","header-align":"center",label:"数量"}}),a("el-table-column",{attrs:{prop:"staffIdAndName","header-align":"center",label:"角色员工"}}),a("el-table-column",{attrs:{prop:"backupStaffNameAndId","header-align":"center",label:"备份员工"}}),a("el-table-column",{attrs:{prop:"option","header-align":"center",align:"center",label:e.$t("table.option"),width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-edit",title:"编辑"},on:{click:function(a){e.handleEdit(t.row)}}}),a("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete",title:"删除"},on:{click:function(a){e.handleDelete(t.row)}}})]}}])})],1),a("el-dialog",{attrs:{title:e.dialogTitle,visible:e.dialogVisible,width:"70%","custom-class":"key-role-setting"},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-row",{attrs:{gutter:12}},[a("el-col",{attrs:{span:10}},[a("el-card",{attrs:{shadow:"never"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[e._v("关键角色设置")]),e.isAddRow&&!e.isEdit?a("el-button",{staticStyle:{float:"right",padding:"0px"},attrs:{type:"text",size:"mini",icon:"el-icon-plus"},on:{click:e.handleAddRoleNum}},[e._v(e._s(e.$t("table.add")))]):e._e(),e.isAddRow?e._e():a("el-button",{staticStyle:{float:"right",padding:"0px"},attrs:{type:"text",size:"mini",icon:"el-icon-circle-plus-outline"},on:{click:e.handleSaveRoleNum}},[e._v(e._s(e.$t("table.save")))]),e.isAddRow?e._e():a("el-button",{staticStyle:{float:"right",padding:"0px","margin-right":"10px"},attrs:{type:"text",size:"mini",icon:"el-icon-remove-outline"},on:{click:e.handleCancelRoleNum}},[e._v(e._s(e.$t("table.cancel")))])],1),a("el-table",{ref:"trainTable",staticStyle:{width:"100%"},attrs:{data:e.roleDataSource,border:"",fit:"",size:"mini",stripe:"",height:"215","tooltip-effect":"dark"}},[a("el-table-column",{attrs:{type:"index",sortable:"","header-align":"center",align:"center",width:"50",label:e.$t("table.id")}}),a("el-table-column",{attrs:{"header-align":"center",label:"关键角色","min-width":"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.isAdd&&!e.isEdit?a("el-select",{attrs:{size:"mini",placeholder:"角色"},model:{value:t.row.roleId,callback:function(a){e.$set(t.row,"roleId",a)},expression:"scope.row.roleId"}},e._l(e.searchForm.keyRoleOptions,function(e){return a("el-option",{key:e.poRoleId,attrs:{label:e.poRoleName,value:e.poRoleId}})})):a("span",[e._v(e._s(t.row.roleName))])]}}])}),a("el-table-column",{attrs:{"header-align":"center",label:"数量",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.isAdd?a("el-input-number",{staticStyle:{width:"80px"},attrs:{size:"mini","controls-position":"right",min:1,max:100,step:1},model:{value:t.row.roleNum,callback:function(a){e.$set(t.row,"roleNum",a)},expression:"scope.row.roleNum"}}):a("span",[e._v(e._s(t.row.roleNum))])]}}])}),a("el-table-column",{attrs:{"header-align":"center",width:"80",align:"center",label:e.$t("table.option")},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.isAdd?e._e():a("el-button",{attrs:{type:"text",size:"mini",title:"编辑",icon:"el-icon-edit"},on:{click:function(a){e.handleRoleRowEdit(t.row)}}}),e.isEdit?e._e():a("el-button",{attrs:{size:"mini",type:"text",title:"删除",icon:"el-icon-delete"},on:{click:function(a){e.handleRoleRowDelete(t.$index,t.row)}}})]}}])})],1)],1)],1),a("el-col",{attrs:{span:14}},[a("el-card",{attrs:{shadow:"never"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[e._v("关键角色关联员工")]),a("el-table",{ref:"affairTable",staticStyle:{width:"100%"},attrs:{data:e.empDataSource,border:"",fit:"",size:"mini",stripe:"",height:"215","tooltip-effect":"dark"}},[a("el-table-column",{attrs:{fixed:"",type:"index",sortable:"","header-align":"center",align:"center",width:"60",label:e.$t("table.id")}}),a("el-table-column",{attrs:{prop:"roleName","header-align":"center",label:"关键角色",width:"100"}}),a("el-table-column",{attrs:{"header-align":"center",label:"角色员工","min-width":"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-select",{attrs:{filterable:"",size:"mini",placeholder:"请选择"},model:{value:t.row.staffId,callback:function(a){e.$set(t.row,"staffId",a)},expression:"scope.row.staffId"}},e._l(e.searchForm.teamStaffOptions,function(e){return a("el-option",{key:e.staffName,attrs:{label:e.staffName,value:e.staffId}})}))]}}])}),a("el-table-column",{attrs:{"header-align":"center",label:"备份员工","min-width":"110"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-select",{attrs:{filterable:"",size:"mini",placeholder:"请选择"},model:{value:t.row.backupStaffId,callback:function(a){e.$set(t.row,"backupStaffId",a)},expression:"scope.row.backupStaffId"}},e._l(e.searchForm.teamStaffOptions,function(e){return a("el-option",{key:e.staffName,attrs:{label:e.staffName,value:e.staffId}})}))]}}])}),a("el-table-column",{attrs:{"header-align":"center",width:"80",align:"center",label:e.$t("table.option")},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"text",title:"删除",icon:"el-icon-delete"},on:{click:function(a){e.handleEmpRowDelete(t.$index,t.row)}}})]}}])})],1)],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){e.handleConfirm()}}},[e._v("确 定")])],1)],1)],1)},w=[],D=(a("7514"),{name:"keyRole",props:["teamInfo"],data:function(){return{isEdit:!1,isAddRow:!0,isEditRow:!1,loading:!1,tableData:[],dialogTitle:"",dialogVisible:!1,roleDataSource:[],empDataSource:[],selectedStaff:[],editRowData:null}},computed:Object(i["a"])({},Object(s["e"])({searchForm:function(e){return e.keyRoleStore.searchForm},keyRoleDataSource:function(e){return e.keyRoleStore.searchForm.keyRoleDataSource}})),watch:{keyRoleDataSource:function(e){var t=this;this.selectedStaff=[];var a={};this.tableData=e.map(function(o){return a[o.poRoleId]?o.amount=0:(a[o.poRoleId]=1,o.amount=e.filter(function(e){return e.poRoleId===o.poRoleId}).length),o.staffName&&o.staffId?o.staffIdAndName=o.staffName+"("+o.staffId+")":o.staffIdAndName="",o.backupStaffName&&o.backupStaffId?o.backupStaffNameAndId=o.backupStaffName+"("+o.backupStaffId+")":o.backupStaffNameAndId="",t.selectedStaff.push(o.staffId,o.backupStaffId),o})}},methods:Object(i["a"])({},Object(s["b"])(["getPOList","getKeyRoleTableData","getKeyRoleList","getEmployeeByTeam"]),{init:function(e){var t=this;this.getPOList({teamId:e,startDate:"",endDate:""}).then(function(){t.changePO()})},onCreate:function(){this.isEdit=!1,this.dialogTitle="添加关键角色信息",this.dialogVisible=!0,this.getKeyRoleList(),this.getEmployeeByTeam(this.teamInfo.projectID+""),this.roleDataSource=[],this.empDataSource=[]},arraySpanMethod:function(e){var t=e.row,a=e.column;e.rowIndex,e.columnIndex;if("poRoleName"===a.property||"option"===a.property||"amount"===a.property)return{rowspan:t.amount,colspan:1}},handleEdit:function(e){var t=this;t.isEditRow=!0,t.isAddRow=!1,t.isEdit=!0,t.dialogTitle="编辑关键角色信息",t.getKeyRoleList(),t.getEmployeeByTeam(t.teamInfo.projectID+""),t.$store.dispatch("getKeyRoleInfo",e).then(function(e){e.success&&(t.dialogVisible=!0,t.empDataSource=e.data.map(function(e){return{staffId:e.staffId,roleId:e.poRoleId,roleName:e.poRoleName,backupStaffId:e.backupStaffId}}),t.editRowData={roleId:e.data[0].poRoleId,roleName:e.data[0].poRoleName,roleNum:e.data.length},t.roleDataSource=[{roleId:e.data[0].poRoleId,roleName:e.data[0].poRoleName,isAdd:!0,roleNum:e.data.length}])})},handleDelete:function(e){var t=this;t.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$store.dispatch("delKeyRoleInfo",e).then(function(e){e.success?(t.$message.success("关键角色删除成功"),t.changePO()):t.$message.error("关键角色删除失败")})})},handleConfirm:function(){var e=this,t={projectRoles:e.empDataSource.map(function(t){return{teamId:e.teamInfo.projectID,projectId:e.searchForm.poId,projectName:e.searchForm.poOptions.find(function(t){return t.projectId===e.searchForm.poId}).projectName,staffId:t.staffId,poRoleId:t.roleId,backupStaffId:t.backupStaffId||""}})};e.$store.dispatch("saveProjectKeyRole",t).then(function(t){t.success?(e.$message.success("关键角色保存成功"),e.getKeyRoleTableData()):(e.$message.error("关键角色保存失败"),e.keyRoleDataSource=[]),e.dialogVisible=!1}).catch(function(){e.$message.error("关键角色保存失败"),e.keyRoleDataSource=[],e.dialogVisible=!1})},changePO:function(){var e=this;this.loading=!0,this.getKeyRoleTableData().then(function(){e.loading=!1}).catch(function(){e.loading=!1})},handleAddRoleNum:function(){this.isEditRow=!1,this.isAddRow=!1;var e=_.first(this.searchForm.keyRoleOptions),t={roleId:e&&e.poRoleId||"",roleName:e&&e.poRoleName||"",roleNum:1,isAdd:!0};this.roleDataSource.unshift(t)},handleSaveRoleNum:function(){var e=this,t=this.roleDataSource.find(function(e){return e.isAdd});if(this.roleDataSource.find(function(e){return e.roleId===t.roleId&&!e.isAdd}))this.$message.warning("需要配置的关键角色已存在，请重新选择关键角色");else{var a=this.empDataSource.filter(function(e){return e.roleId===t.roleId});if(a.length<=t.roleNum){for(var o=0;o<t.roleNum-a.length;o++){var r={roleId:t.roleId,roleName:_.find(this.searchForm.keyRoleOptions,{poRoleId:1*t.roleId}).poRoleName,staffId:"",staffName:"",backupStaffId:"",backupStaffName:""};this.empDataSource.unshift(r)}this.roleDataSource.map(function(t){return t.isAdd&&(t.roleName=_.find(e.searchForm.keyRoleOptions,{poRoleId:1*t.roleId}).poRoleName,t.isAdd=!1),t}),this.isAddRow=!0}else this.$message.warning("需要配置的关键角色数量小于该角色已经配置的员工数量，请手动从关联员工表中删除")}},handleCancelRoleNum:function(){var e=this;e.isEditRow?e.roleDataSource.map(function(t){return t.isAdd&&(t.roleId=e.editRowData.roleId,t.roleName=e.editRowData.roleName,t.roleNum=e.editRowData.roleNum,t.isAdd=!1),t}):this.roleDataSource=this.roleDataSource.filter(function(e){return!e.isAdd}),this.isAddRow=!0},handleRoleRowEdit:function(e){var t=this;t.roleDataSource.find(function(e){return e.isAdd})?t.$message.warning("存在正在编辑的行，请先保存"):(t.editRowData=_.clone(e),e.isAdd=!0,t.isAddRow=!1,t.isEditRow=!0)},handleRoleRowDelete:function(e,t){var a=this;if(t.isAdd)return a.roleDataSource=a.roleDataSource.filter(function(e){return!e.isAdd}),void(a.isAddRow=!0);a.roleDataSource.find(function(e){return e.isAdd})?a.$message.warning("存在正在编辑的行，请先保存"):a.empDataSource.find(function(e){return e.roleId===t.roleId})&&a.$confirm("该角色已经配置了员工, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){a.roleDataSource.splice(e,1),a.empDataSource=a.empDataSource.filter(function(e){return e.roleId!==t.roleId})})},handleEmpRowDelete:function(e,t){var a=this;a.roleDataSource.find(function(e){return e.isAdd})?a.$message.warning("关键角色表存在正在编辑的行，请先保存"):(a.roleDataSource.map(function(e){return e.roleId===t.roleId&&e.roleNum--,e}),a.empDataSource.splice(e,1))}})}),k=D,R=(a("9b25"),Object(u["a"])(k,S,w,!1,null,null,null));R.options.__file="keyRole.vue";var N=R.exports,x={name:"teamSettings",components:{empSettings:m,associatedPO:v,keyRole:N},created:function(){var e=this.$route.params&&this.$route.params.id;this.fetchData(e),this.tempRoute=Object.assign({},this.$route)},data:function(){return{postForm:"",activeTab:"employee"}},methods:{fetchData:function(e){var t=this,a=this;a.$store.dispatch("getProjectGroupInfoById",e).then(function(e){e.success&&(t.postForm=e.data.datas[0],t.setTagsViewTitle(),t.$refs.employeeSetting.getAreaAndCuBycondition(t.postForm.pdu))}).catch(function(e){console.log(e)})},setTagsViewTitle:function(){var e="团队设置",t=Object.assign({},this.tempRoute,{title:"".concat(e,"-").concat(this.postForm.projectName)});this.$store.dispatch("updateVisitedView",t)},changeTab:function(e){"PO"===e.name&&this.$refs.associatedPO.handleFilter(null,this.teamInfo),"keyRole"===e.name&&this.$refs.keyRole.init(this.postForm.projectID)}}},F=x,O=(a("7048"),Object(u["a"])(F,o,r,!1,null,null,null));O.options.__file="teamSettings.vue";t["default"]=O.exports}}]);
//# sourceMappingURL=chunk-7e57fec7.0ef1416c.js.map