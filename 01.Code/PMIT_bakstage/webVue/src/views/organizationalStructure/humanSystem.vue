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
          <div id="empListTree" class="ztree" style="height:440px;"></div>
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
          showIcon: false
        },
        edit: {
          enable: true,
          showRemoveBtn: false,
          showRenameBtn: false,
          drag: {
            isMove: false,
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
          beforeDrag: this.beforeDrag1,
          beforeDrop: this.beforeDrop1
        }
      },
      humanNodeSetting: {
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
          beforeDrag: this.beforeDrag2,
          beforeDrop: this.beforeDrop2
        }
      }
    };
  },
  computed: {
    ...mapState({
      humanTreeDataSource: state => state.orgStructureStore.humanTreeData,
      humanTreeNodeSource: state => state.orgStructureStore.humanTreeNode
    })
  },
  mounted() {
    this.getHumanTreeData();
    this.getHumanTreeNode();
  },
  watch: {
    humanTreeNodeSource(data) {
      $.fn.zTree.init($("#empListTree"), this.humanNodeSetting, data);
    },
    humanTreeDataSource(data) {
      $.fn.zTree.init($("#empRelationTree"), this.humanTreeSetting, data);
    }
  },
  methods: {
    ...mapActions(["getHumanTreeData", "getHumanTreeNode"]),
    beforeDrag1(treeId, treeNodes) {
      this.currentTreeId = treeId;
      return !treeNodes[0].disDrop;
    },
    beforeDrop1(treeId, treeNodes, targetNode, moveType) {
      return !targetNode && this.currentTreeId !== treeId;
    },
    beforeDrag2(treeId, treeNodes) {
      this.currentTreeId = treeId;
      return true;
    },
    beforeDrop2(treeId, treeNodes, targetNode, moveType) {
      if (targetNode.level > 0) {
        this.$message.warning("只能拖动到项目组名称(一级节点)上面");
        return false;
      }
      return !!targetNode && this.currentTreeId !== treeId;
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
#empListTree li {
  display: inline-block;
  margin-bottom: 10px;
}
#empListTree li .button {
  display: none;
}
#empListTree a {
  margin-right: 10px;
  background-color: rgba(64, 158, 255, 0.1);
  display: inline-block;
  padding: 0 10px;
  height: 32px;
  line-height: 30px;
  font-size: 12px;
  color: #409eff;
  border-radius: 4px;
  box-sizing: border-box;
  border: 1px solid rgba(64, 158, 255, 0.2);
  white-space: nowrap;
}
.em-wrap .el-form-item {
  margin-bottom: 7px;
}
</style>
