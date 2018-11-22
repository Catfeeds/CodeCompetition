import React from "react";
import DivManagemenComponent from '../compoonentJsx/DivManagemenComponent.jsx';

function Tacos() {
    return <h2>3</h2>;
}

const routers = [
    {
        path: "/sandwiches",
        component: DivManagemenComponent
    },
    {
        path: "/tacos",
        component: Tacos,
        // routes: []
    }
];

export default routers;