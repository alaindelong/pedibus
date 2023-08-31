import React, { useState } from "react";

function Fermata() {
  const [isBooked, setIsBooked] = useState(false);
  
  const handleClick = () => {
    setIsBooked((prev) => !prev);
  };
  return (
    <div>
      {/*<i className="bi bi-circle" onClick={handleClick}
           style={{backgroundColor:color,cursor:"pointer"}}
    ></i>*/}
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          border: "solid",
          borderWidth:"0.1em",
          height:"2em",
          width:"2em",
          borderRadius:"10em",
          cursor:"pointer"
        }}
        onClick={handleClick}
      >
      { isBooked && <div style={{
         display: "flex",
         justifyContent: "center",
         alignItems: "center",
         border: "solid",
         height:"1em",
         width:"1em",
         borderRadius:"10em",
         backgroundColor:"black"
        }}></div> } 
      </div>
    </div>
  );
}
export default Fermata;
