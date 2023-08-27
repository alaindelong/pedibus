import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";


function Profil(){
    const [username,setUsername] = useState<string|null>("")
    const [data, setData] = useState<any>(null)
    useEffect(()=>{
        
        setUsername(localStorage.getItem("username"))
        fetch(`http://localhost:8082/users/${username}`)
        .then(response => response.json())
        .then(data => {console.log(data.username);setData(data)})
        .catch(error => console.log(error.message))
    },[username])
    return(
        <div className="container">
            <div className="row">
                <div className="col-md-6">
                <h1>welcome {username} !!!</h1>
                </div>
               <div className="col-md-6"> 
                 <NavLink to="../login" onClick={() => localStorage.clear()}>Logout</NavLink>
               </div>
               <div className="row">
                 { data && <p> votre role est {data.role}</p> }
               </div>
            </div>
        </div>
    )
}
export default Profil