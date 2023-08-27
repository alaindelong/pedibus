import React from "react";
import { NavLink } from "react-router-dom";

function Home(){
    return(
        <div className="container" style={{backgroundColor:"gray"}}>
            <div className="row">
               <div className="col-md-6">
                   <p>welcome to pedibus app</p>
               </div>
               <div className="col-md-3">
                  <NavLink to="register">Register</NavLink>
               </div>
               <div className="col-md-3">
                  <NavLink to="Login">Login</NavLink>
               </div>
            </div>
        </div>
    )
}
export default Home