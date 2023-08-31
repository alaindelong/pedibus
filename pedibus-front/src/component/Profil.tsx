import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import Percorso from "./Percorso";


function Profil(){
    const [username,setUsername] = useState<string|null>("")
    const [data, setData] = useState<any>(null)
    const [loading, setLoading] = useState(true)
    useEffect(()=>{
        
        setUsername(localStorage.getItem("username"))
        fetch(`http://localhost:8082/users/${username}`)
        .then(response => response.json())
        .then(r => {setData(r);setLoading(false)})
        .catch(error => console.log(error.message))
    },[username,loading])
    if(loading) return <div>loading ...</div>
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
                  <p> votre role est {data.role}</p> 
               </div>
            </div>
            <Percorso/>
        </div>
    )
}
export default Profil