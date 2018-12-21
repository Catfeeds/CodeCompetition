import api from "@/api/orgStructureApi";

export default {
  state: {
    humanTreeData: [],
    humanTreeNode: [],
    humanTreeTeamNode: [],
    busiTreeData: [],
    busiTreeNode: [],
    busiTeePONode: []
  },
  mutations: {
    updateHumanTreeData(state, value) {
      let dealTreeData = data => {
        return data.map(item => {
          let node = {
            id: item.nodeID,
            data: item,
            name: item.nodeName,
            open: true
          };
          if (item.teamList) {
            node.children = dealTreeData(item.teamList);
          }
          if (item.childList) {
            node.children = dealTreeData(item.childList);
          }
          return node;
        });
      };
      state.humanTreeData = dealTreeData(value);
    },
    updateHumanTreeNode(state, value) {
      state.humanTreeNode = value
        .filter(item => item.operationAllow)
        .map(item => {
          return {
            id: item.levelIndex,
            data: item,
            name: item.levelIndexName
          };
        });
    },
    updateHumanTreeTeamNode(state, value) {
      state.humanTreeTeamNode = value.map(item => {
        return {
          id: item.teamID,
          data: item,
          name: item.nodeName
        };
      });
    },
    updateBusiTreePONode(state, value) {
      state.busiTeePONode = value.map(item => {
        return {
          id: item.teamID,
          data: item,
          name: item.nodeName
        };
      });
    },
    updateBusiTreeData(state, value) {
      let dealTreeData = data => {
        return data.map(item => {
          let node = {
            id: item.nodeID,
            data: item,
            name: item.nodeName,
            open: true
          };
          if (item.teamList) {
            node.children = dealTreeData(item.teamList);
          }
          if (item.childList) {
            node.children = dealTreeData(item.childList);
          }
          return node;
        });
      };
      state.busiTreeData = dealTreeData(value);
    },
    updateBusiTreeNode(state, value) {
      state.busiTreeNode = value
        .filter(item => item.operationAllow)
        .map(item => {
          return {
            id: item.levelIndex,
            data: item,
            name: item.levelIndexName
          };
        });
    }
  },
  actions: {
    getHumanTreeData({ commit }) {
      return api
        .getTreeData("team")
        .then(res => {
          if (res.data.success) {
            commit("updateHumanTreeData", res.data.data);
          } else {
            commit("updateHumanTreeData", []);
          }
        })
        .catch(() => {
          commit("updateHumanTreeData", []);
        });
    },
    getHumanTreeNode({ commit }) {
      return api
        .getTreeNode(1)
        .then(res => {
          if (res.data.success) {
            commit("updateHumanTreeNode", res.data.data);
          } else {
            commit("updateHumanTreeNode", []);
          }
        })
        .catch(() => {
          commit("updateHumanTreeNode", []);
        });
    },
    getBusiTreeData({ commit }) {
      return api
        .getTreeData("po")
        .then(res => {
          if (res.data.success) {
            commit("updateBusiTreeData", res.data.data);
          } else {
            commit("updateBusiTreeData", []);
          }
        })
        .catch(() => {
          commit("updateBusiTreeData", []);
        });
    },
    getBusiTreeNode({ commit }) {
      return api
        .getTreeNode(2)
        .then(res => {
          if (res.data.success) {
            commit("updateBusiTreeNode", res.data.data);
          } else {
            commit("updateBusiTreeNode", []);
          }
        })
        .catch(() => {
          commit("updateBusiTreeNode", []);
        });
    },
    getTreeTeamNode({ commit }) {
      return api
        .getTreeTeamNode()
        .then(res => {
          if (res.data.success) {
            commit("updateHumanTreeTeamNode", res.data.data);
          } else {
            commit("updateHumanTreeTeamNode", []);
          }
        })
        .catch(() => {
          commit("updateHumanTreeTeamNode", []);
        });
    },
    getTreePONode({ commit }) {
      return api
        .getTreePONode()
        .then(res => {
          if (res.data.success) {
            commit("updateBusiTreePONode", res.data.data);
          } else {
            commit("updateBusiTreePONode", []);
          }
        })
        .catch(() => {
          commit("updateBusiTreePONode", []);
        });
    },
    addTreeNode(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .addTreeNode(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delTreeNode(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .delTreeNode(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    editTreeNode(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .editTreeNode(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    moveTreeNode(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .moveTreeNode(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    }
  }
};
