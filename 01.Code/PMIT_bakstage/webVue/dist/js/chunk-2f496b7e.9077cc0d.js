(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2f496b7e"],{"289c":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{attrs:{inline:!0}},[a("el-form-item",{attrs:{label:""}},[a("el-select",{attrs:{size:"mini",clearable:"",placeholder:"产品线"},on:{change:e.productChange},model:{value:e.product,callback:function(t){e.product=t},expression:"product"}},e._l(e.productOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}))],1),a("el-form-item",{attrs:{label:""}},[a("el-select",{attrs:{size:"mini",clearable:"",placeholder:"区域"},on:{change:e.areaChange},model:{value:e.area,callback:function(t){e.area=t},expression:"area"}},e._l(e.areaOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}))],1),a("el-form-item",{attrs:{label:""}},[a("el-select",{attrs:{size:"mini",clearable:"",placeholder:"交付部"},model:{value:e.pdu,callback:function(t){e.pdu=t},expression:"pdu"}},e._l(e.pduOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}))],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.$t("table.search")))])],1),a("el-form-item",[a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"8px"},attrs:{type:"primary",size:"mini",icon:"el-icon-download"},on:{click:e.handleExport}},[e._v(e._s(e.$t("button.export")))])],1),a("el-form-item",[a("el-upload",{staticClass:"filter-item",attrs:{action:"system/exceloperation/importScore","before-upload":e.handleUpload,"on-success":e.handleSuccess,"on-error":e.handleError,"show-file-list":!1,accept:"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"}},[a("el-button",{staticStyle:{"margin-left":"8px"},attrs:{size:"mini",icon:"el-icon-upload2",type:"primary"}},[e._v(e._s(e.$t("button.import")))])],1)],1),a("el-form-item",[a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"8px"},attrs:{type:"primary",size:"mini",icon:"el-icon-download"},on:{click:e.handleTemplate}},[e._v(e._s(e.$t("button.template")))])],1)],1),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",fit:"",size:"mini",stripe:"","highlight-current-row":"","max-height":"350","cell-class-name":e.cellClassFn}},[a("el-table-column",{attrs:{fixed:"",type:"index",width:"60"}}),a("el-table-column",{attrs:{label:"编号","min-width":"80",prop:"employeeID",width:"60"}}),a("el-table-column",{attrs:{label:"姓名","min-width":"80",prop:"employeeName",width:"60"}}),e._l(e.headers,function(t,r){return a("el-table-column",{key:r,attrs:{label:t.name,prop:t.courses&&1===t.courses.length?t.courses[0].courseID:t.id,"header-align":"center",align:"center",width:"100"}},[t.courses&&t.courses.length>1?a("el-table-column",{attrs:{label:"总分","header-align":"center",align:"center",formatter:e.calcTotalScore}}):e._e(),e._l(t.courses,function(r){return t.courses&&t.courses.length>1?a("el-table-column",{key:r.courseID,attrs:{label:r.courseName,prop:r.courseID,"header-align":"center",align:"center"}}):e._e()})],2)})],2),a("el-row",{attrs:{type:"flex",justify:"end"}},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"total, slot, prev, pager, next",total:e.total,"prev-text":"上一页","next-text":"下一页"},on:{"size-change":e.handleSizeChange,"current-change":e.handleSizeChange,"update:currentPage":function(t){e.currentPage=t}}})],1),a("div",{staticStyle:{display:"none"}},[a("form",{ref:"templateForm",attrs:{target:"downloadFrame",id:"downloadTemplate",action:"system/exceloperation/download/scoreTemplate",method:"post"}}),a("iframe",{attrs:{id:"downloadFrame",name:"downloadFrame"}})])],1)},n=[],s=(a("7f7f"),a("be94")),o=a("2f62"),l={mounted:function(){this.getSDProductInfo(),this.handleFilter()},data:function(){return{headers:[],tableData:[],currentPage:1,pageSize:100,total:0}},computed:Object(s["a"])({},Object(o["c"])(["getSDParams"]),Object(o["e"])({productOptions:function(e){return e.scoreDisplayStore.productData},areaOptions:function(e){return e.scoreDisplayStore.areaData},pduOptions:function(e){return e.scoreDisplayStore.pduData},dataSource:function(e){return e.scoreDisplayStore.scoreData}}),{product:{get:function(){return this.$store.state.scoreDisplayStore.selectedProduct},set:function(e){this.$store.commit("setSDSelectedProduct",e)}},area:{get:function(){return this.$store.state.scoreDisplayStore.selectedArea},set:function(e){this.$store.commit("setSDSelectedArea",e)}},pdu:{get:function(){return this.$store.state.scoreDisplayStore.selectedPDU},set:function(e){this.$store.commit("setSDSelectedPDU",e)}}}),watch:{dataSource:function(e){this.tableData=e.allCourseScoreList,this.headers=e.columnNameMap,this.total=e.size}},methods:Object(s["a"])({},Object(o["b"])(["getSDProductInfo","getSDAreas","getSDPDUList"]),{productChange:function(){this.$store.commit("setSDAreaData",[]),this.getSDAreas(this.product)},areaChange:function(){this.$store.commit("setSDPDUData",[]),this.getSDPDUList(this.area)},handleFilter:function(e,t){this.currentPage=t||1;var a=this.getSDParams();this.$store.dispatch("getTrainingScore1",a)},handleSizeChange:function(){this.handleFilter(null,this.currentPage)},cellClassFn:function(e){return 0==e.row[e.column.property]?"cell-zero":""},handleExport:function(){this.$message.info("功能正在完善中。。。")},handleUpload:function(e){var t=e.name.substring(e.name.lastIndexOf(".")+1),a="xls"===t||"xlsx"===t,r=e.size/1024/1024<10;return a||this.$message({message:"上传文件只能是 xls、xlsx格式!",type:"warning"}),r||this.$message({message:"上传文件大小不能超过 10MB!",type:"warning"}),a||extension2&&r},handleSuccess:function(){this.$message.success("文件导入成功"),this.page.currentPage=1,this.getEmployeeList()},handleError:function(){this.$message.error("文件导入失败,请检查文件格式是否合法")},handleTemplate:function(){this.$refs.templateForm.submit()}})},i=l,c=(a("c4d1"),a("2877")),u=Object(c["a"])(i,r,n,!1,null,null,null);u.options.__file="scoreDisplay.vue";t["default"]=u.exports},9738:function(e,t,a){},c4d1:function(e,t,a){"use strict";var r=a("9738"),n=a.n(r);n.a}}]);
//# sourceMappingURL=chunk-2f496b7e.9077cc0d.js.map