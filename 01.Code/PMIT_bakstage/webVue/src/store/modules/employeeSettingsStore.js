import api from "@/api/teamSettingsApi";

const employeeSettings = {
  state: {
    region: "",
    costCenter: "",
    regionData: [],
    costCenterData: [],
    regionAndCostDataSource: []
  },
  mutations: {
    updateTMRegino(state, val) {
      state.region = val || "";
    },
    updateTMCostCenter(state, val) {
      state.costCenter = val || "";
    },
    updateRegionAndCostCenter(state, data) {
      let hash = {};
      state.regionAndCostDataSource = data || [];
      state.costCenterData = [];
      state.regionData = data
        .reduceRight((item, next) => {
          hash[next.area] ? "" : (hash[next.area] = true && item.push(next));
          return item;
        }, [])
        .map(item => {
          return item.area;
        });
      state.region = "";
      state.costCenter = "";
    },
    updateCostCenterData(state) {
      state.costCenterData = state.regionAndCostDataSource
        .filter(item => item.area === state.region)
        .map(item => {
          return item.cu;
        });
    }
  },
  actions: {
    getAreaAndCuBycondition({ commit }, pdu) {
      return api
        .getAreaAndCuBycondition(pdu)
        .then(res => {
          if (res.data.success) {
            commit("updateRegionAndCostCenter", res.data.data);
          } else {
            commit("updateRegionAndCostCenter", []);
          }
        })
        .catch(() => {
          commit("updateRegionAndCostCenter", []);
        });
    },
    getTMEmployees({ state }, param) {
      let obj = {
        pdu: param.pdu,
        area: state.region,
        cu: state.costCenter,
        gender: param.gender,
        name: param.employeeName,
        staffId: param.employeeID
      };
      return new Promise((reslove, reject) => {
        api
          .getEmployees(obj)
          .then(res => {
            reslove(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getEmployeesByTeam(commit, teamId) {
      return new Promise((reslove, reject) => {
        api
          .getEmployeesByTeam(teamId)
          .then(res => {
            reslove(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    saveTeamInfos(arg, params) {
      return new Promise((reslove, reject) => {
        api
          .saveTeamInfos(params)
          .then(res => {
            reslove(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    }
  }
};
export default employeeSettings;
