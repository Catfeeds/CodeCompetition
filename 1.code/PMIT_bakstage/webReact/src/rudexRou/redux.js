import { createStore}  from "redux";
import { combineReducers } from "redux";
import name from './nameFile.js';
/*
* @ 解析redux的使用 createStore  创建关于redux的对象
* @ combineReducers 多个函数分装在一起，交给createStore使用
*  @ 开始state中的值如果为空，则赋予state =[] ,否则定义初始化数据类型
*  @ action 有两个参数，type为类型 payload为保存当前传递的最新数据 此时state解析为当前最新的数据存储
* */
const initialSt = {
    userID: '',
    userName:''
}

const productReducer = function (state=initialSt,action) {
    switch (action.type){
        case 'QIAO':{
            return {
                ...state,
                userID:action.payload
            }
        }
        case 'GANG':{
            return {
                ...state,
                userName:action.payload
            }
        }
        default:
            return state;
    }
}

const initialState = {
    cart: []
}

const cartmenReducer = function (state=initialState,action) {
    switch (action.type){
        case name.ADD_TO_CART:{
            return {
                ...state,
                cart:[...state.cart, action.payload]
            }
        }
        case name.DEL_TO_CART:{
            return {
                ...state,
                cart:[state.cart.filter(item=>item.product !== action.payload.product )]
            }
        }
        case name.UPD_TO_CART:{
            return {
                ...state,
                cart:[state.cart.map(item=>item.product === action.payload.product ? action.payload:item)]
            }
        }
        default:
            return state;
    }
}
//创建状态管理数据表参数
const allReducer = {
    productm:productReducer,
    cartmenm:cartmenReducer
}
const reducer = combineReducers(allReducer);
export let store = createStore(reducer);
// console.log(store);
/*
* @ 通过写三个方法 去操作数据 三个方法带有三个参数
* */
export function addToCart(product, quantity, unitCost) {
    return {
        type: 'ADD_TO_CART',
        payload: { product, quantity, unitCost }
    }
}
export function delToCart(product, quantity, unitCost) {
    return {
        type: 'UPD_TO_CART',
        payload: { product, quantity, unitCost }
    }
}
export function updToCart(product, quantity, unitCost) {
    return {
        type: 'DEL_TO_CART',
        payload: { product, quantity, unitCost }
    }
}
/*
* @ subscribe函数监听数据更改，放在调用函数更改之后执行调用
* */
let unsubscribe = store.subscribe( ()=>{
    // console.log(store.getState());
    }
)

/*
* @    调用分写数据库方法 增加，删除，更改
* */
// store.dispatch(delToCart("qiao",1,250));
// store.dispatch(updToCart("bread",1,259));

export function UpdateStatus() {
    return store.getState();//调用检测代码函数
};

