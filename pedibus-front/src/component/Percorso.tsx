import React from "react";
import Fermata from "./Fermata";

function Percorso() {
  const fermate = ["paris", "defense", "cergy"];
  const show = false
  const handleClick = () =>{

  }
  return (
    <div className="row">
      <div className="col-sm-4">
        <h6>Andata</h6>
        <div>
            <ul>
                {
                    fermate.map((f,index) =>(
                        <div className="row" key={index}>
                          <Fermata/>
                          <p>
                  {" "}
                {index!== fermate.length-1 && <i className="bi bi-arrow-down"
                style={{fontSize:"2em"}}
                ></i>}  
                </p>
                        </div>
                    ))
                }
            </ul>
        </div>
      </div>
      <div className="col-sm-4">
        <h6>nome fermata</h6>
        <div>
            <ul>
                {fermate.map((f,index) => <p className="row" key={index}
                style={{lineHeight:"4em"}}
                >{f}</p>)}
            </ul>
        </div>
      </div>

      <div className="col-sm-4">
        <h6>Ritorno</h6>
        <div>
            <ul>
                {
                    fermate.map((f,index) =>(
                        <div className="row" key={index}>
                          <Fermata/>
                          <p>
                  {" "}
                {index!== fermate.length-1 && <i className="bi bi-arrow-up"
                style={{fontSize:"2em"}}
                ></i>}  
                </p>
                        </div>
                    ))
                }
            </ul>
        </div>
      </div>
      { show && <div>
        <ul>
          {fermate.map((f, index) => (
            <div className="row" key={index}>
              <div className="col-sm-4">
                <Fermata/>
                <p>
                  {" "}
                {index!== fermate.length-1 && <i className="bi bi-arrow-down"
                style={{fontSize:"2em"}}
                ></i>}  
                </p>
              </div>
              <div className="col-sm-4"> {f}</div>
              <div className="col-sm-4">
                {" "}
                <Fermata/>
                <p>
                  {" "}
                  {index!== fermate.length-1 && <i className="bi bi-arrow-up"
                  style={{fontSize:"2em"}}
                  ></i>} 
                </p>
              </div>
            </div>
          ))}
        </ul>
      </div>}
    </div>
  );
}
export default Percorso;
