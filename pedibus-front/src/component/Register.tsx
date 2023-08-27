import React, { useState } from "react";
import { PedibusUser, PedibusUserResponse } from "../model/Model";
import { useNavigate } from "react-router-dom";


function Register(){

    const [user, setUser] = useState<PedibusUser>({})
    const [nome, setNome] = useState("")
    const [email, setEmail] = useState("")
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [confirmPwd, setConfirmPwd] = useState("")
    const [isValid, setIsValid] = useState(false)
    const navigate = useNavigate()

    const OnRegister =() =>{
        //setUser({nome,username,email,password})
        console.log("user to registrate "+nome +" "+username+" "+email+" "+password)
        password === confirmPwd? setIsValid(true):setIsValid(false)
        //post http request
        fetch("http://localhost:8082/register",{
            method:'POST',
            headers: new Headers({"Content-type":"application/json;charset=UTF-8"}),
            body:JSON.stringify({
                "nome":nome,
                "username":username,
                "email":email,
                "password":password
            })
        })
        
        .then((response:Response) => {if(response.ok) return response.text();
        else throw new Error("error occurs "+response.statusText)})
        .then((token) => {//console.log(token); 
            localStorage.setItem("token",token) 
            localStorage.setItem("username",username)
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
                    value={nome}
                    onChange={(event)=>setNome(event.target.value)}/>
                </div>
                <div className="mb-2">
                    <input className="form-control" type="email" placeholder="email" value={email}
                    onChange={(event)=>{setEmail(event.target.value);setUsername(event.target.value)}}
                    />
                </div>
                <div className="mb-2">
                    <input className="form-control" type="password" placeholder="password" value={password}
                    onChange={(event)=>setPassword(event.target.value)}
                    />
                </div>
                <div className="mb-1">
                    <input className="form-control" type="password" placeholder="confirmPassword" 
                    value={confirmPwd}
                    onChange={(event)=>setConfirmPwd(event.target.value)}
                    />
                </div>
                <div className="d-grid">
                   <button className="btn btn-primary" type="button"
                   onClick={ OnRegister}
                   >Register</button> 
                   {!isValid && <span style={{color:"red"}}>passwords do not match</span>}
                </div>
            </div>
        </div>
    )
}
export default Register