<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="14">
        <el-card shadow="never">
          <div slot="header" class="clearfix">人力体系树</div>
          <div id="empRelationTree" class="ztree" style="height:440px;"></div>
        </el-card>
      </el-col>
      <el-col :span="10" class="em-list">
        <el-card shadow="never">
          <div slot="header" class="clearfix">人力体系节点</div>
          <div id="empListTree" class="ztree" style="height:80px;"></div>
          <div id="teamListTree" class="ztree" style="height:360px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import "ztree";
import { mapState, mapMutations, mapActions } from "vuex";
export default {
  data() {
    let vm = this;
    return {
      humanTreeSetting: {
        view: {
          showIcon: false,
          addHoverDom: vm.addHoverDom,
          removeHoverDom: vm.removeHoverDom
        },
        edit: {
          enable: true,
          showRemoveBtn: false,
          showRenameBtn: false,
          drag: {
            prev: false,
            next: false,
            inner: true
          }
        },
        data: {
          simpleData: {
            enable: true
          }
        },
        callback: {
          beforeDrag: vm.beforeTreeDrag,
          beforeDrop: vm.beforeTreeDrop
        }
      },
      humanNodeSetting: {
        view: {
          showIcon: false,
          showLine: false
        },
        edit: {
          enable: true,
          showRemoveBtn: false,
          showRenameBtn: false,
          drag: {
            isMove: false
          }
        },
        data: {
          simpleData: {
            enable: true
          }
        },
        callback: {
          beforeDrag: vm.beforeNodeDrag,
          beforeDrop: vm.beforeNodeDrop
        }
      },
      humanTeamNodeSetting: {
        view: {
          showIcon: false,
          showLine: false
        },
        edit: {
          enable: true,
          showRemoveBtn: false,
          showRenameBtn: false
        },
        data: {
          simpleData: {
            enable: true
          }
        },
        callback: {
          beforeDrag: vm.beforeTeamDrag,
          beforeDrop: vm.beforeTeamDrop
        }
      }
    };
  },
  computed: {
    ...mapState({
      humanTreeDataSource: state => state.orgStructureStore.humanTreeData,
      humanTreeNodeSource: state => state.orgStructureStore.humanTreeNode,
      humanTreeTeamSource: state => state.orgStructureStore.humanTreeTeamNode
    })
  },
  mounted() {
    this.getHumanTreeData();
    this.getHumanTreeNode();
    this.getHumanTreeTeamNode();
  },
  watch: {
    humanTreeNodeSource(data) {
      $.fn.zTree.init($("#empListTree"), this.humanNodeSetting, data);
    },
    humanTreeDataSource(data) {
      $.fn.zTree.init($("#empRelationTree"), this.humanTreeSetting, data);
    },
    humanTreeTeamSource(data) {
      $.fn.zTree.init($("#teamListTree"), this.humanTeamNodeSetting, data);
    }
  },
  methods: {
    ...mapActions([
      "getHumanTreeData",
      "getHumanTreeNode",
      "getHumanTreeTeamNode"
    ]),
    beforeTreeDrag(treeId, treeNodes) {
      this.currentTreeId = treeId;
      return !treeNodes[0].disDrop;
    },
    beforeTreeDrop(treeId, treeNodes, targetNode, moveType) {
      return !targetNode && this.currentTreeId === treeId;
    },
    beforeNodeDrag(treeId, treeNodes) {
      this.currentTreeId = treeId;
      return true;
    },
    beforeNodeDrop(treeId, treeNodes, targetNode, moveType) {
      if (targetNode.data.nodeType === "BG") {
        this.$message.warning("只能给BD以下节点新增子节点或同级节点");
        return false;
      }
      this.handleAddNode(treeId, targetNode, treeNodes[0]);
      return !!targetNode && this.currentTreeId !== treeId;
    },
    beforeTeamDrag(treeId, treeNodes) {
      this.currentTreeId = treeId;
      return true;
    },
    beforeTeamDrop(treeId, treeNodes, targetNode, moveType) {
      if (targetNode.data.nodeType === "BG") {
        this.$message.warning("只能给BD以下节点新增子节点或同级节点");
        return false;
      }
      this.handleAddNode(treeId, targetNode, treeNodes[0], true);
      return !!targetNode && this.currentTreeId !== treeId;
    },
    addHoverDom(treeId, treeNode) {
      let isFlag = ["BG", "BD"].indexOf(treeNode.data.nodeType) >= 0;
      if (
        treeNode.editNameFlag ||
        isFlag ||
        $("#btnDel_" + treeNode.tId).length > 0
      ) {
        return;
      }
      var sObj = $("#" + treeNode.tId + "_span");
      //删除
      var detHtml =
        "<span class='button remove' id='btnDel_" +
        treeNode.tId +
        "' title='删除节点'></span>";
      sObj.after(detHtml);
      //修改
      var editHtml =
        "<span class='button edit' id='btnEdit_" +
        treeNode.tId +
        "' title='修改节点'></span>";
      sObj.after(editHtml);
      $("#btnEdit_" + treeNode.tId)
        .off()
        .on("click", () => {
          this.editNode(treeId, treeNode);
        });
      $("#btnDel_" + treeNode.tId)
        .off()
        .on("click", () => {
          this.deleteNode(treeId, treeNode);
        });
    },
    removeHoverDom(treeId, treeNode) {
      $("#btnEdit_" + treeNode.tId)
        .off()
        .remove();
      $("#btnDel_" + treeNode.tId)
        .off()
        .remove();
    },
    deleteNode(treeId, treeNode) {
      let vm = this;
      if (treeNode.isParent) {
        vm.$confirm("该节点存在子节点,是否同步删除子节点?", "提示", {
          confirmButtonText: "是",
          cancelButtonText: "否",
          type: "warning"
        })
          .then(() => {
            vm.handleDelNode(treeId, treeNode, true);
          })
          .catch(() => {
            vm.handleDelNode(treeId, treeNode, true);
          });
      } else {
        vm.$confirm("此操作将删除该节点,是否继续?", "提示", {
          confirmButtonText: "是",
          cancelButtonText: "否",
          type: "warning"
        })
          .then(() => {
            vm.handleDelNode(treeId, treeNode, true);
          })
          .catch(() => {
            vm.removeHoverDom(treeId, treeNode);
          });
      }
    },
    editNode(treeId, treeNode) {
      let vm = this;
      vm.$prompt("请输入节点名称", "节点修改", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputValue: treeNode.name,
        inputValidator: vm.validNodeName
      })
        .then(({ value }) => {
          let param = {
            type: "team",
            relationID: 1,
            nodePath: treeNode.data.nodePath,
            nodeType: treeNode.data.nodeType,
            nodeID: treeNode.id,
            nodeName: value
          };
          vm.$store.dispatch("editHumanTreeNode", param).then(res => {
            if (res.success) {
              vm.$message.success("节点修改成功");
              vm.getHumanTreeData();
            } else {
              vm.$message.success("节点修改失败");
              vm.removeHoverDom(treeId, treeNode);
            }
          });
        })
        .catch(() => {
          vm.removeHoverDom(treeId, treeNode);
        });
    },
    handleDelNode(treeId, treeNode, isDelChildren) {
      let vm = this;
      let param = {
        type: "team",
        relationID: "",
        deleteChildren: isDelChildren,
        nodePath: treeNode.data.nodePath,
        nodeType: treeNode.data.nodeType,
        nodeID: treeNode.id
      };
      vm.$store.dispatch("delHumanTreeNode", param).then(res => {
        if (res.success) {
          vm.$message.success("节点删除成功");
          vm.getHumanTreeData();
        } else {
          vm.$message.error("节点删除失败");
          vm.removeHoverDom(treeId, treeNode);
        }
      });
    },
    handleAddNode(treeId, treeNode, newTreeNode, isTeam) {
      let vm = this;
      let param = {
        type: "team",
        relationID: "1",
        addNodeType: newTreeNode.data.nodeType,
        addNodeName: newTreeNode.name,
        parentNodeID: treeNode.id,
        parentNodePath: treeNode.data.nodePath,
        parentNodeType: treeNode.data.nodeType
      };
      if(isTeam) {
        param.addTeamID = newTreeNode.id;
      }else{
        param.addNodeType = newTreeNode.data.levelIndexID;
      }
      vm.$store
        .dispatch("addHumanTreeNode", param)
        .then(res => {
          if (res.success) {
            vm.$message.success("节点添加成功");
          } else {
            vm.$message.error("节点添加失败");
          }
          vm.getHumanTreeData();
          vm.getHumanTreeTeamNode();
        })
        .catch(() => {
          vm.$message.error("节点添加失败");
          vm.getHumanTreeData();
          vm.getHumanTreeTeamNode();
        });
    },
    validNodeName(value) {
      if (!value) {
        return "请输入新的节点名称";
      }
      return true;
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scope>
.ztree {
  overflow: auto;
}
.tree-wrap-border {
  border: 1px solid #dcdfe6;
}
.em-list .el-tag {
  margin-right: 10px;
}
.em-list {
  li {
    display: inline-block;
    margin-bottom: 10px;
    .button {
      display: none;
    }
    a {
      margin-right: 10px;
      background-color: rgba(64, 158, 255, 0.1) !important;
      display: inline-block;
      padding: 0 10px;
      height: 32px !important;
      line-height: 30px;
      font-size: 12px;
      color: #409eff;
      border-radius: 4px;
      box-sizing: border-box;
      border: 1px solid rgba(64, 158, 255, 0.2);
      white-space: nowrap;
    }
  }
}
.em-wrap .el-form-item {
  margin-bottom: 7px;
}
</style>
