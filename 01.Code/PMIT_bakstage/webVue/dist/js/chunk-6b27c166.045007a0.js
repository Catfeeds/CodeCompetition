(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6b27c166"],{"0bfb":function(e,t,a){"use strict";var n=a("cb7c");e.exports=function(){var e=n(this),t="";return e.global&&(t+="g"),e.ignoreCase&&(t+="i"),e.multiline&&(t+="m"),e.unicode&&(t+="u"),e.sticky&&(t+="y"),t}},"3b2b":function(e,t,a){var n=a("7726"),r=a("5dbc"),o=a("86cc").f,i=a("9093").f,s=a("aae3"),l=a("0bfb"),c=n.RegExp,u=c,p=c.prototype,m=/a/g,d=/a/g,f=new c(m)!==m;if(a("9e1e")&&(!f||a("79e5")(function(){return d[a("2b4c")("match")]=!1,c(m)!=m||c(d)==d||"/a/i"!=c(m,"i")}))){c=function(e,t){var a=this instanceof c,n=s(e),o=void 0===t;return!a&&n&&e.constructor===c&&o?e:r(f?new u(n&&!o?e.source:e,t):u((n=e instanceof c)?e.source:e,n&&o?l.call(e):t),a?this:p,c)};for(var h=function(e){e in c||o(c,e,{configurable:!0,get:function(){return u[e]},set:function(t){u[e]=t}})},g=i(u),b=0;g.length>b;)h(g[b++]);p.constructor=c,c.prototype=p,a("2aba")(n,"RegExp",c)}a("7a56")("RegExp")},a255:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container affairs-container"},[a("div",{staticClass:"filter-container"},[a("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"所属产品线"},on:{change:e.changeProduct},model:{value:e.searchForm.product,callback:function(t){e.$set(e.searchForm,"product",t)},expression:"searchForm.product"}},e._l(e.productOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),a("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"所属DU"},on:{change:e.changeDU},model:{value:e.searchForm.du,callback:function(t){e.$set(e.searchForm,"du",t)},expression:"searchForm.du"}},e._l(e.duOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),a("el-select",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"所属PDU"},model:{value:e.searchForm.pdu,callback:function(t){e.$set(e.searchForm,"pdu",t)},expression:"searchForm.pdu"}},e._l(e.pduOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),a("el-input",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"员工编号"},model:{value:e.searchForm.empolyeeId,callback:function(t){e.$set(e.searchForm,"empolyeeId",t)},expression:"searchForm.empolyeeId"}}),a("el-input",{staticClass:"filter-item",staticStyle:{width:"130px"},attrs:{size:"mini",clearable:"",placeholder:"员工姓名"},model:{value:e.searchForm.empolyeeName,callback:function(t){e.$set(e.searchForm,"empolyeeName",t)},expression:"searchForm.empolyeeName"}}),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"8px"},attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.$t("table.search")))]),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"8px"},attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.handleCreate}},[e._v(e._s(e.$t("button.add")))]),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"8px"},attrs:{type:"primary",size:"mini",icon:"el-icon-download"},on:{click:e.handleCreate}},[e._v(e._s(e.$t("button.export")))]),a("el-upload",{staticClass:"filter-item",attrs:{action:"system/exceloperation/importPersonalInfo","before-upload":e.handleUpload,"on-success":e.handleSuccess,"on-error":e.handleError,"show-file-list":!1,accept:"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"}},[a("el-button",{staticStyle:{"margin-left":"8px"},attrs:{size:"mini",icon:"el-icon-upload2",type:"primary"}},[e._v(e._s(e.$t("button.import")))])],1),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"8px"},attrs:{type:"primary",size:"mini",icon:"el-icon-download"},on:{click:e.handleTemplate}},[e._v(e._s(e.$t("button.template")))])],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticStyle:{width:"100%"},attrs:{data:e.list,border:"",fit:"",size:"mini",stripe:"","max-height":"420","highlight-current-row":""},on:{"sort-change":e.handleSort}},[a("el-table-column",{attrs:{"header-align":"center",align:"center",label:e.$t("table.id"),width:"50",fixed:"left",type:"index"}}),a("el-table-column",{attrs:{"min-width":"100px","header-align":"center",label:"员工编号",sortable:"custom",fixed:"left",prop:"employeeID"}}),a("el-table-column",{attrs:{"min-width":"120px","header-align":"center",label:"员工姓名",sortable:"custom",fixed:"left",prop:"employeeName"}}),a("el-table-column",{attrs:{width:"80","header-align":"center",label:"性别",sortable:"custom",prop:"sex"}}),a("el-table-column",{attrs:{"min-width":"100","header-align":"center",label:"入职日期",sortable:"custom",prop:"employmentDate"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("formatDate")(t.row.employmentDate)))])]}}])}),a("el-table-column",{attrs:{"min-width":"100","header-align":"center",label:"是否骨干",sortable:"custom",prop:"ifBackBone"}}),a("el-table-column",{attrs:{"min-width":"100","header-align":"center",label:"合作模式",sortable:"custom",prop:"cooperationMode"}}),a("el-table-column",{attrs:{"min-width":"100","header-align":"center",label:"产品线",sortable:"custom",prop:"bu"}}),a("el-table-column",{attrs:{"min-width":"120","header-align":"center",label:"DU",sortable:"custom",prop:"du"}}),a("el-table-column",{attrs:{"min-width":"230","header-align":"center",label:"PDU",sortable:"custom",prop:"pdu"}}),a("el-table-column",{attrs:{"min-width":"80","header-align":"center",label:"地域",sortable:"custom",prop:"workPlaceArea"}}),a("el-table-column",{attrs:{"min-width":"100","header-align":"center",label:"岗位",sortable:"custom",prop:"position"}}),a("el-table-column",{attrs:{"min-width":"100","header-align":"center",label:"毕业院校",sortable:"custom",prop:"graduationSchool"}}),a("el-table-column",{attrs:{"min-width":"110","header-align":"center",label:"学历",sortable:"custom",prop:"education"}}),a("el-table-column",{attrs:{"min-width":"110","header-align":"center",label:"是否统招",sortable:"custom",prop:"ifUnderGraduates"}}),a("el-table-column",{attrs:{"min-width":"110","header-align":"center",label:"联系电话",sortable:"custom",prop:"telephone"}}),a("el-table-column",{attrs:{align:"center",label:e.$t("table.option"),width:"180","header-align":"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-edit",title:"编辑"},on:{click:function(a){e.handleEdit(t.row)}}}),a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-delete",title:"删除"},on:{click:function(a){e.handleDel(t.row.employeeID)}}}),a("router-link",{staticStyle:{"margin-left":"10px"},attrs:{to:"/employeeManagement/employeeDetail/"+t.row.employeeID}},[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-search",title:"查看详情"}})],1)]}}])})],1),a("el-row",{attrs:{type:"flex",justify:"end"}},[a("el-pagination",{attrs:{"current-page":e.page.currentPage,"page-size":e.page.pageSize,layout:"total, slot, prev, pager, next",total:e.page.totalRecord,"prev-text":"上一页","next-text":"下一页"},on:{"current-change":e.handleCurrentChange}})],1),a("div",{staticStyle:{display:"none"}},[a("form",{ref:"templateForm",attrs:{target:"downloadFrame",id:"downloadTemplate",action:"system/exceloperation/download/personalInfoTemplate",method:"post"}}),a("iframe",{attrs:{id:"downloadFrame",name:"downloadFrame"}})])],1)},r=[],o=(a("7f7f"),a("be94")),i=(a("cadf"),a("551c"),a("097d"),a("c466")),s=a("2f62"),l={filters:{formatDate:function(e){var t=new Date(e);return Object(i["a"])(t,"yyyy-MM-dd")}},data:function(){return{duOptions:[],productOptions:[],pduOptions:[],searchForm:{product:"",du:"",pdu:"",employeeId:"",employeeName:""},list:[],listLoading:!1,page:{currentPage:1,pageSize:100,totalRecord:0,sortColumn:"employeeID",sortType:"desc"}}},computed:Object(o["a"])({},Object(s["c"])(["empolyeeId","empolyeeName"])),mounted:function(){this.getProductInfo(),this.getEmployeeList()},methods:{getProductInfo:function(){var e=this;e.$store.dispatch("getProductInfo").then(function(t){e.productOptions=t})},getDUInfo:function(e){var t=this;if(!e)return t.duOptions=[],t.pduOptions=[],t.searchForm.du="",void(t.searchForm.pdu="");t.$store.dispatch("getDUInfo",{bu:e}).then(function(e){t.duOptions=e||[],t.searchForm.du="",t.searchForm.pdu=""})},getPDUInfo:function(e,t){var a=this;a.$store.dispatch("getPDUInfo",{bu:e,du:t}).then(function(e){a.pduOptions=e||[]})},getEmployeeList:function(){var e=this,t={pageInfo:{currPage:e.page.currentPage,pageSize:e.page.pageSize,sortColumn:e.page.sortColumn,sortType:e.page.sortType},baseStaffInfo:{bu:e.searchForm.product,du:e.searchForm.du,pdu:e.searchForm.pdu,employeeID:e.searchForm.employeeId,employeeName:e.searchForm.employeeName}};e.listLoading=!0,e.$store.dispatch("getEmployeeList",t).then(function(t){t.success?(e.list=t.data.baseStaffInfos,e.page.totalRecord=t.data.listSize):e.list=[],e.listLoading=!1}).catch(function(){e.list=[],e.listLoading=!1})},changeProduct:function(){this.getDUInfo(this.searchForm.product)},changeDU:function(){this.searchForm.product?this.getPDUInfo(this.searchForm.product,this.searchForm.du):this.$message.warning("请选择所属产品线")},submtForm:function(){var e=this;e.$refs.affairsForm.validate(function(t){if(!t)return!1;var a={du:e.affairsForm.product,series:e.affairsForm.series,system:e.affairsForm.system,affairName:e.affairsForm.affairsName,employeeID:e.employeeId},n="addAffairsInfo";e.isEdit&&(a.affairID=e.affairsForm.affairsId,n="editAffairsInfo"),e.$store.dispatch(n,a).then(function(t){t.success?(e.$message.success(t.message),e.dialogBaseVisible=!1,e.getAffairsList()):e.$message.error(t.message)})})},clearValidate:function(e){this.$refs[e]&&this.$refs[e].clearValidate()},handleCurrentChange:function(e){var t=this;t.page.currentPage=e,t.getEmployeeList()},handleFilter:function(){this.page.currentPage=1,this.getEmployeeList()},handleSort:function(e){e.prop&&(this.page.sortColumn=e.prop,this.page.sortType="descending"===e.order?"desc":"asc",this.getEmployeeList())},handleCreate:function(){this.$message.info("功能正在完善中。。。")},handleDel:function(e){var t=this;t.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$store.dispatch("delEmployeeInfo",e).then(function(e){e.success?(t.$message.success(e.message),t.getEmployeeList()):t.$message.error(e.message)})})},handleEdit:function(e){this.$message.info("功能正在完善中。。。")},handleView:function(e){var t=this;t.$router.push({path:"/employeeManagement/empolyeeDetail"})},handleUpload:function(e){var t=e.name.substring(e.name.lastIndexOf(".")+1),a="xls"===t||"xlsx"===t,n=e.size/1024/1024<10;return a||this.$message({message:"上传文件只能是 xls、xlsx格式!",type:"warning"}),n||this.$message({message:"上传文件大小不能超过 10MB!",type:"warning"}),a||extension2&&n},handleSuccess:function(){this.$message.success("文件导入成功"),this.page.currentPage=1,this.getEmployeeList()},handleError:function(){this.$message.error("文件导入失败,请检查文件格式是否合法")},handleTemplate:function(){this.$refs.templateForm.submit()}}},c=l,u=(a("f03e"),a("2877")),p=Object(u["a"])(c,n,r,!1,null,null,null);p.options.__file="employeeList.vue";t["default"]=p.exports},c466:function(e,t,a){"use strict";a.d(t,"a",function(){return n});a("3b2b"),a("a481");function n(e,t){/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length)));var a={"M+":e.getMonth()+1,"d+":e.getDate(),"h+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds()};for(var n in a)if(new RegExp("(".concat(n,")")).test(t)){var o=a[n]+"";t=t.replace(RegExp.$1,1===RegExp.$1.length?o:r(o))}return t}function r(e){return("00"+e).substr(e.length)}},eb44:function(e,t,a){},f03e:function(e,t,a){"use strict";var n=a("eb44"),r=a.n(n);r.a}}]);
//# sourceMappingURL=chunk-6b27c166.045007a0.js.map