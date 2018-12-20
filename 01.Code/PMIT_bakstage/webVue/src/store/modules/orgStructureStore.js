import api from "@/api/orgStructureApi";

export default {
  state: {
    humanTreeData: [],
    humanTreeNode: []
  },
  mutations: {
    updateHumanTreeData(state, value) {
      let dealTreeData = data => {
        return data.map(item => {
          let node = {
            id: item.nodeID,
            data: item,
            name: item.nodeName
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
    }
  },
  actions: {
    getHumanTreeData({ commit }) {
      return api
        .getHumanTreeData()
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
        .getHumanTreeNode(1)
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
    addHumanTreeNode(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .addHumanTreeNode(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delHumanTreeNode(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .delHumanTreeNode(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    editHumanTreeNode(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .editHumanTreeNode(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    moveHumanTreeNode(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .moveHumanTreeNode(param)
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
