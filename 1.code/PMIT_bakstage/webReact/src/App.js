import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import {store,addToCart,delToCart,UPD_TO_CART} from './rudexRou/redux.js';
import { Layout, Menu, Breadcrumb, Icon, Button } from 'antd';
import routers from './rudexRou/routers.js';
import axios from './rudexRou/urlHttp.js';

const { Header, Content, Footer, Sider } = Layout;

// wrap <Route> and use this everywhere instead, then when
// sub routes are added to any route it'll work
function RouteWithSubRoutes(route) {
    return (
        <Route path={route.path}
            render={props => (
                // pass the sub-routes down to keep nesting
                <route.component {...props} routes={route.routes} />
            )}
        />
    );
}

//导航组件模块开发
const SubMenu = Menu.SubMenu;
class App extends Component {
    constructor(props){
        super(props);
        this.state = {
            collapsed: false,  //管理左侧隐藏参数
            logoName : 'PMOIT项目管理系统'
        };
    };
    onCollapse = (collapsed) => {
        this.setState({
            logoName : !this.state.collapsed?'PMOIT':'PMOIT项目管理系统'
        });
        this.setState({ collapsed:!this.state.collapsed });
    };
    SelectedItem = (e) => {  //点击导航栏目调用函数   item
        console.log(e);
        console.log(this.props);
        // this.props.history.push('/aa');
    };
    render() {
        return (
            <Router>
                <Layout style={{ minHeight: '100vh'}}>
                    <Sider width="240" collapsible collapsed={this.state.collapsed} onCollapse={this.onCollapse}>
                        <div className="pmoit-navgaition-logo">
                            {this.state.logoName}
                        </div>
                        <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                            <SubMenu key="sub1" title={<span><Icon type="setting" theme="outlined" /><span>系统设置</span></span>}>
                                <Menu.Item key="1"><Link to="/tacos">人员管理</Link></Menu.Item>
                                <Menu.Item key="2"><Link to="/sandwiches">部门管理</Link></Menu.Item>
                            </SubMenu>
                            <SubMenu key="sub2" title={<span><Icon type="team" theme="outlined" /><span>人资管理</span></span>}>
                                <Menu.Item key="3"><Link to="/tacos">Team 1</Link></Menu.Item>
                                <Menu.Item key="4"><Link to="/sandwiches">Team 2</Link></Menu.Item>
                            </SubMenu>
                        </Menu>
                    </Sider>
                    <Layout>
                        <Header style={{ background: '#fff', padding: 0 }}>
                            <Icon className="trigger" type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'}
                                onClick={this.onCollapse}/>
                        </Header>
                        <Content style={{ margin: '10px 16px' }}>
                            <div style={{ padding: 10, background: '#fff', minHeight: 360 }}>
                                {routers.map((route, i) => (
                                <RouteWithSubRoutes key={i} {...route} />
                                ))}
                            </div>
                        </Content>
                    </Layout>
                </Layout>
            </Router>
        );
    };
    // componentWillMount(){
    //     store.dispatch(addToCart("qiao",1,250));
    // };
}
export default App;


// componentDidMount(){  //请求函数丢次
//     axios.get('/').then(function (response) {
//         console.log(response);
//     }).catch(function (error) {
//         console.log(error);
//     })
// }

