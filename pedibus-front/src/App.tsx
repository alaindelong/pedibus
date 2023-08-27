import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './component/Home';
import Register from './component/Register';
import Login from './component/Login';
import Main from './Main';
import Profil from './component/Profil';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Main/>}>
         <Route index element={<Home/>}/>
         <Route path='/register'  element={<Register/>}/>
         <Route path='/login'  element={<Login/>}/>
         <Route path='/profil'  element={<Profil/>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
