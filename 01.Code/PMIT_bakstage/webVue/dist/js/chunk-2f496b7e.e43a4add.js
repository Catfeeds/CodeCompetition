(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2f496b7e"],{"289c":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{attrs:{inline:!0}},[a("el-form-item",{attrs:{label:""}},[a("el-select",{attrs:{size:"mini",placeholder:"产品线"},on:{change:e.productChange},model:{value:e.product,callback:function(t){e.product=t},expression:"product"}},e._l(e.productOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e.value}})}))],1),a("el-form-item",{attrs:{label:""}},[a("el-select",{attrs:{size:"mini",placeholder:"区域"},on:{change:e.areaChange},model:{value:e.area,callback:function(t){e.area=t},expression:"area"}},e._l(e.areaOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}))],1),a("el-form-item",{attrs:{label:""}},[a("el-select",{attrs:{size:"mini",placeholder:"交付部"},model:{value:e.area,callback:function(t){e.area=t},expression:"area"}},e._l(e.pduOptions,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})}))],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.$t("table.search")))])],1),a("el-form-item",[a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"8px"},attrs:{type:"primary",size:"mini",icon:"el-icon-download"},on:{click:e.handleExport}},[e._v(e._s(e.$t("button.export")))])],1),a("el-form-item",[a("el-upload",{staticClass:"filter-item",attrs:{action:"system/exceloperation/importScore","before-upload":e.handleUpload,"on-success":e.handleSuccess,"on-error":e.handleError,"show-file-list":!1,accept:"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"}},[a("el-button",{staticStyle:{"margin-left":"8px"},attrs:{size:"mini",icon:"el-icon-upload2",type:"primary"}},[e._v(e._s(e.$t("button.import")))])],1)],1),a("el-form-item",[a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"8px"},attrs:{type:"primary",size:"mini",icon:"el-icon-download"},on:{click:e.handleTemplate}},[e._v(e._s(e.$t("button.template")))])],1)],1),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,"max-height":"350","cell-class-name":e.cellClassFn}},[a("el-table-column",{attrs:{fixed:"",type:"index",width:"60"}}),e._l(e.headers,function(t,n){return a("el-table-column",{key:n,attrs:{label:t.name,prop:t.id,"header-align":"center",align:"center",width:"100"}},e._l(t.courses,function(n){return t.courses&&t.courses.length>0?a("el-table-column",{key:n.courseID,attrs:{label:n.courseName,prop:n.courseID,"header-align":"center",align:"center"}}):e._e()}))})],2),a("el-row",{attrs:{type:"flex",justify:"end"}},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":100,layout:"total, slot, prev, pager, next",total:e.total,"prev-text":"上一页","next-text":"下一页"},on:{"size-change":e.handleSizeChange,"current-change":e.handleSizeChange,"update:currentPage":function(t){e.currentPage=t}}})],1)],1)},r=[],s=(a("7f7f"),a("be94")),i=(a("cadf"),a("551c"),a("097d"),a("2f62")),o={mounted:function(){this.getSDProductInfo(),this.handleFilter()},data:function(){return{headers:[],tableData:[],currentPage:1,pageSize:1,total:100}},computed:Object(s["a"])({},Object(i["c"])(["getSDParams"]),Object(i["e"])({productOptions:function(e){return e.scoreDisplayStore.productData},areaOptions:function(e){return e.scoreDisplayStore.areaData},pduOptions:function(e){return e.scoreDisplayStore.pduData},dataSource:function(e){return e.scoreDisplayStore.scoreData}}),{product:{get:function(){return this.$store.state.scoreDisplayStore.selectedProduct},set:function(e){this.$store.commit("setSDSelectedProduct",e)}},area:{get:function(){return this.$store.state.scoreDisplayStore.selectedArea},set:function(e){this.$store.commit("setSDSelectedArea",e)}},pdu:{get:function(){return this.$store.state.scoreDisplayStore.selectedPDU},set:function(e){this.$store.commit("setSDSelectedPDU",e)}}}),watch:{dataSource:function(e){this.currentPage=1,this.total=e.datas.length,this.headers=e.trains,this.handleSizeChange()}},methods:Object(s["a"])({},Object(i["b"])(["getSDProductInfo","getSDAreas","getSDPDUList"]),{productChange:function(){this.$store.commit("setSDAreaData",[]),this.getSDAreas(this.product)},areaChange:function(){this.$store.commit("setSDPDUData",[]),this.getSDPDUList(this.area)},handleFilter:function(){var e=this.getSDParams();this.$store.dispatch("getTrainingScore1",e)},handleSizeChange:function(){this.tableData=this.dataSource.datas.slice(100*(this.currentPage-1),100*this.currentPage+1)},cellClassFn:function(e){return 0==e.row[e.column.property]?"cell-zero":""},handleExport:function(){this.$message.info("功能正在完善中。。。")},handleTemplate:function(){this.$message.info("功能正在完善中。。。")},handleUpload:function(e){var t=e.name.substring(e.name.lastIndexOf(".")+1),a="xls"===t||"xlsx"===t,n=e.size/1024/1024<10;return a||this.$message({message:"上传文件只能是 xls、xlsx格式!",type:"warning"}),n||this.$message({message:"上传文件大小不能超过 10MB!",type:"warning"}),a||extension2&&n},handleSuccess:function(){this.$message.success("文件导入成功"),this.page.currentPage=1,this.getEmployeeList()},handleError:function(){this.$message.error("文件导入失败,请检查文件格式是否合法")}})},l=o,c=(a("c4d1"),a("2877")),u=Object(c["a"])(l,n,r,!1,null,null,null);u.options.__file="scoreDisplay.vue";t["default"]=u.exports},9738:function(e,t,a){},c4d1:function(e,t,a){"use strict";var n=a("9738"),r=a.n(n);r.a}}]);
//# sourceMappingURL=chunk-2f496b7e.e43a4add.js.map