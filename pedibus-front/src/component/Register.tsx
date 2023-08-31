import React, { useState } from "react";
import { PedibusUser, PedibusUserResponse } from "../model/Model";
import { useNavigate } from "react-router-dom";


function Register(){

    const [user, setUser] = useState<PedibusUser>({})
    const [confirmPwd, setConfirmPwd] = useState("")
    const [isValid, setIsValid] = useState(false)
    const navigate = useNavigate()

    const handleChange =(e) =>{
        setUser({
            ...user,
            [e.target.name]:e.target.value
        })
        setUser(prev =>({...prev,username:prev.email}))
    }

    const OnRegister =(user:PedibusUser) =>{
        //setUser({nome,username,email,password})
        console.log("user to registrate "+user.nome +" "+user.username+" "+user.email+" "+user.password)
        user.password === confirmPwd? setIsValid(true):setIsValid(false)
        //post http request
        fetch("http://localhost:8082/register",{
            method:'POST',
            headers: new Headers({"Content-type":"application/json;charset=UTF-8"}),
            body:JSON.stringify(user)
        })
        
        .then((response:Response) => {if(response.ok) return response.text();
        else throw new Error("error occurs "+response.statusText)})
        .then((token) => {//console.log(token); 
            localStorage.setItem("token",token) 
            if(user.username!==undefined)
            localStorage.setItem("username",user.username)
            navigate("../profil") 
        })
        
          
    }
    return(
        <div className="container">
            <div className="row">
               <h5>registration</h5>
            </div>
            <div className="row">
                <div className="mb-2">
                    <input className="form-control" type="text" placeholder="name" 
                    name="nome"
                    onChange={handleChange}/>
                </div>
                <div className="mb-2">
                    <input className="form-control" type="email" placeholder="email" name="email"
                    onChange={handleChange}
                    />
                </div>
                <div className="mb-2">
                    <input className="form-control" type="password" placeholder="password" name="password"
                    onChange={handleChange}
                    />
                </div>
                <div className="mb-1">
                    <input className="form-control" type="password" placeholder="confirmPassword" 
                    name="confirmPwd"
                    onChange={(event)=>setConfirmPwd(event.target.value)}
                    />
                </div>
                <div className="d-grid">
                   <button className="btn btn-primary" type="button"
                   onClick={ () => OnRegister(user)}
                   >Register</button> 
                   {!isValid && <span style={{color:"red"}}>passwords do not match</span>}
                </div>
            </div>
        </div>
    )
}
export default Register