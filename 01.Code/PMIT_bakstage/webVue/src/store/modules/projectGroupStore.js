import api from "@/api/projectGroupApi";
const projectGroup = {
  state: {
    searchForm: {
      product: "",
      du: "",
      pdu: "",
      teamName: "",
      pm: "",
      productOptions: [],
      duOptions: [],
      pduOptions: [],
    },
    dataSource:[],
    newForm:{
      product: "",
      du: "",
      pdu: "",
      teamName: "",
      pm: "",
      productOptions: [],
      duOptions: [],
      pduOptions: [],
    }
  },
  mutations: {
    updatePGProductData(state,value){
      state.searchForm.productOptions = value || [];
      state.searchForm.product = '';
      state.searchForm.duOptions = value || [];
      state.searchForm.du = '';
      state.searchForm.pduOptions = value || [];
      state.searchForm.pdu = '';
    },
    updatePGDUData(state,value){
      state.searchForm.duOptions = value || [];
      state.searchForm.du = '';
      state.searchForm.pduOptions = value || [];
      state.searchForm.pdu = '';
    },
    updatePGPDUData(state,value){
      state.searchForm.pduOptions = value || [];
      state.searchForm.pdu = '';
    },
    updateNewFormProductData(state,value){
      state.newForm.productOptions = value || [];
      state.newForm.product = '';
      state.newForm.duOptions = value || [];
      state.newForm.du = '';
      state.newForm.pduOptions = value || [];
      state.newForm.pdu = '';
    },
    updateNewFormDUData(state,value){
      state.newForm.duOptions = value || [];
      state.newForm.du = '';
      state.newForm.pduOptions = value || [];
      state.newForm.pdu =  '';
    },
    updateNewFormPDUData(state,value){
      state.newForm.pduOptions = value || [];
      state.newForm.pdu = '';
    },
    updateDataSource(state,value){
      state.dataSource = value || [];
    }
  },
  actions: {
    getPGProductInfo({ dispatch, state, commit }) {
        return dispatch('getProductInfo').then(data=>{
            commit('updatePGProductData',data);
            return dispatch('getPGDU');
        })
    },
    getPGDU({ dispatch, state, commit }) {
        return dispatch('getDUInfo',{bu:state.searchForm.product}).then(data=>{
            commit('updatePGDUData',data);
            return dispatch('getPGPDUList');
        })
    },
    getPGPDUList({ dispatch,commit, state }) {
        return dispatch('getPDUInfo',{
          bu:state.searchForm.product,
          du:state.searchForm.du
        }).then(data=>{
            commit('updatePGPDUData',data);
        })
    },
    getNewFormProductInfo({ dispatch, state, commit }) {
        return dispatch('getProductInfo').then(data=>{
            commit('updateNewFormProductData',data);
            return dispatch('getNewFormDU');
        })
    },
    getNewFormDU({ dispatch, state, commit }) {
        return dispatch('getDUInfo',{bu:state.searchForm.product}).then(data=>{
            commit('updateNewFormDUData',duData);
            return dispatch('getNewFormPDUList');
        })
    },
    getNewFormPDUList({ commit, state }) {
        return dispatch('getPDUInfo',{
          bu:state.searchForm.product,
          du:state.searchForm.du
        }).then(data=>{
            commit('updateNewFormPDUData',data);
        })
    },
    addProjectInfo(){
      return api.addProjectInfo();
    },
    getProjectGroupInfo({commit,state},pageInfo) {
      return api.getProjectGroupInfo({
        bu:state.searchForm.product,
        du:state.searchForm.du,
        pdu:state.searchForm.pdu,
        projectName:state.searchForm.teamName,
        pmName:state.searchForm.pm,
        pageNo:pageInfo && pageInfo.pageNo || 1
      }).then(ret=>{
          commit('updateDataSource',ret && ret.data && ret.data.data||[]);
      })
    }
  }
};
export default projectGroup;
