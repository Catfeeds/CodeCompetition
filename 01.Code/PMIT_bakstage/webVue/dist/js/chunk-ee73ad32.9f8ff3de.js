(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ee73ad32"],{6929:function(e,t,a){"use strict";var n=a("76fd"),r=a.n(n);r.a},"76fd":function(e,t,a){},fa71:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:14}},[a("el-card",{attrs:{shadow:"never"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[e._v("人力体系树")]),a("div",{staticClass:"ztree",staticStyle:{height:"440px"},attrs:{id:"empRelationTree"}})])],1),a("el-col",{staticClass:"em-list",attrs:{span:10}},[a("el-card",{attrs:{shadow:"never"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[e._v("人力体系节点")]),a("div",{staticClass:"ztree",staticStyle:{height:"80px"},attrs:{id:"empListTree"}}),a("div",{staticClass:"ztree",staticStyle:{height:"360px"},attrs:{id:"teamListTree"}})])],1)],1)],1)},r=[],o=(a("7f7f"),a("be94")),d=(a("cadf"),a("551c"),a("097d"),a("2654"),a("2f62")),i={data:function(){var e=this;return{humanTreeSetting:{view:{showIcon:!1,addHoverDom:e.addHoverDom,removeHoverDom:e.removeHoverDom},edit:{enable:!0,showRemoveBtn:!1,showRenameBtn:!1,drag:{prev:!1,next:!1,inner:!0}},data:{simpleData:{enable:!0}},callback:{beforeDrag:e.beforeTreeDrag,beforeDrop:e.beforeTreeDrop}},humanNodeSetting:{view:{showIcon:!1,showLine:!1},edit:{enable:!0,showRemoveBtn:!1,showRenameBtn:!1,drag:{isMove:!1}},data:{simpleData:{enable:!0}},callback:{beforeDrag:e.beforeNodeDrag,beforeDrop:e.beforeNodeDrop}},humanTeamNodeSetting:{view:{showIcon:!1,showLine:!1},edit:{enable:!0,showRemoveBtn:!1,showRenameBtn:!1},data:{simpleData:{enable:!0}},callback:{beforeDrag:e.beforeTeamDrag,beforeDrop:e.beforeTeamDrop}}}},computed:Object(o["a"])({},Object(d["e"])({humanTreeDataSource:function(e){return e.orgStructureStore.humanTreeData},humanTreeNodeSource:function(e){return e.orgStructureStore.humanTreeNode},humanTreeTeamSource:function(e){return e.orgStructureStore.humanTreeTeamNode}})),mounted:function(){this.getHumanTreeData(),this.getHumanTreeNode(),this.getHumanTreeTeamNode()},watch:{humanTreeNodeSource:function(e){$.fn.zTree.init($("#empListTree"),this.humanNodeSetting,e)},humanTreeDataSource:function(e){$.fn.zTree.init($("#empRelationTree"),this.humanTreeSetting,e)},humanTreeTeamSource:function(e){$.fn.zTree.init($("#teamListTree"),this.humanTeamNodeSetting,e)}},methods:Object(o["a"])({},Object(d["b"])(["getHumanTreeData","getHumanTreeNode","getHumanTreeTeamNode"]),{beforeTreeDrag:function(e,t){return this.currentTreeId=e,!t[0].disDrop},beforeTreeDrop:function(e,t,a,n){return!a&&this.currentTreeId===e},beforeNodeDrag:function(e,t){return this.currentTreeId=e,!0},beforeNodeDrop:function(e,t,a,n){return"BG"===a.data.nodeType?(this.$message.warning("只能给BD以下节点新增子节点或同级节点"),!1):(this.handleAddNode(e,a,t[0]),!!a&&this.currentTreeId!==e)},beforeTeamDrag:function(e,t){return this.currentTreeId=e,!0},beforeTeamDrop:function(e,t,a,n){return"BG"===a.data.nodeType?(this.$message.warning("只能给BD以下节点新增子节点或同级节点"),!1):(this.handleAddNode(e,a,t[0],!0),!!a&&this.currentTreeId!==e)},addHoverDom:function(e,t){var a=this,n=["BG","BD"].indexOf(t.data.nodeType)>=0;if(!(t.editNameFlag||n||$("#btnDel_"+t.tId).length>0)){var r=$("#"+t.tId+"_span"),o="<span class='button remove' id='btnDel_"+t.tId+"' title='删除节点'></span>";r.after(o);var d="<span class='button edit' id='btnEdit_"+t.tId+"' title='修改节点'></span>";r.after(d),$("#btnEdit_"+t.tId).off().on("click",function(){a.editNode(e,t)}),$("#btnDel_"+t.tId).off().on("click",function(){a.deleteNode(e,t)})}},removeHoverDom:function(e,t){$("#btnEdit_"+t.tId).off().remove(),$("#btnDel_"+t.tId).off().remove()},deleteNode:function(e,t){var a=this;t.isParent?a.$confirm("该节点存在子节点,是否同步删除子节点?","提示",{confirmButtonText:"是",cancelButtonText:"否",type:"warning"}).then(function(){a.handleDelNode(e,t,!0)}).catch(function(){a.handleDelNode(e,t,!0)}):a.$confirm("此操作将删除该节点,是否继续?","提示",{confirmButtonText:"是",cancelButtonText:"否",type:"warning"}).then(function(){a.handleDelNode(e,t,!0)}).catch(function(){a.removeHoverDom(e,t)})},editNode:function(e,t){var a=this;a.$prompt("请输入节点名称","节点修改",{confirmButtonText:"确定",cancelButtonText:"取消",inputValue:t.name,inputValidator:a.validNodeName}).then(function(n){var r=n.value,o={type:"team",relationID:1,nodePath:t.data.nodePath,nodeType:t.data.nodeType,nodeID:t.id,nodeName:r};a.$store.dispatch("editHumanTreeNode",o).then(function(n){n.success?(a.$message.success("节点修改成功"),a.getHumanTreeData()):(a.$message.success("节点修改失败"),a.removeHoverDom(e,t))})}).catch(function(){a.removeHoverDom(e,t)})},handleDelNode:function(e,t,a){var n=this,r={type:"team",relationID:"",deleteChildren:a,nodePath:t.data.nodePath,nodeType:t.data.nodeType,nodeID:t.id};n.$store.dispatch("delHumanTreeNode",r).then(function(a){a.success?(n.$message.success("节点删除成功"),n.getHumanTreeData()):(n.$message.error("节点删除失败"),n.removeHoverDom(e,t))})},handleAddNode:function(e,t,a,n){var r=this,o={type:"team",relationID:"1",addNodeType:a.data.nodeType,addNodeName:a.name,parentNodeID:t.id,parentNodePath:t.data.nodePath,parentNodeType:t.data.nodeType};n&&(o.addTeamID=a.id),r.$store.dispatch("addHumanTreeNode",o).then(function(e){e.success?r.$message.success("节点添加成功"):r.$message.error("节点添加失败"),r.getHumanTreeData(),r.getHumanTreeTeamNode()}).catch(function(){r.$message.error("节点添加失败"),r.getHumanTreeData(),r.getHumanTreeTeamNode()})},validNodeName:function(e){return!!e||"请输入新的节点名称"}})},s=i,c=(a("6929"),a("2877")),u=Object(c["a"])(s,n,r,!1,null,null,null);u.options.__file="humanSystem.vue";t["default"]=u.exports}}]);
//# sourceMappingURL=chunk-ee73ad32.9f8ff3de.js.map