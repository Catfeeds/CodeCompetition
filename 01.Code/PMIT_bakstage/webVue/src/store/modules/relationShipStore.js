import { getProductInfo } from "@/api/appApi";
import { getArea,getRelationShips } from '@/api/relationShipApi'

let state = {
    // products:[],
    // aeras:[],
    selectedProduct:'',
    selectedArea:''
}

let mutations={
    // setProducts(state,val){
    //     state.products = val;
    // },
    // setArea(state,val){
    //     state.aeras = val;
    // },
    setSelectedProduct(state,val){
        state.selectedProduct=val;
    },
    setSelectedArea(state,val){
        state.selectedArea = val;
    }
}

let actions ={
    getRSProductInfo({commit}){
        return getProductInfo();
    },
    getRSArea({commit},product){
        return getArea(product);
    },
    getRelationShips({state}){
        return getRelationShips({
            product:state.product,
            area:state.area
        })
    }
}