import React from "react";
import { Outlet } from "react-router-dom";

function Main(){
    return(
        <div className="container">
            <div className="row">
            <h1>Main page</h1>
            </div>
            <Outlet/>
        </div>
    )
}
export default Main