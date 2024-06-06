import React, { useEffect, useState } from "react";
import "./styles.scss";
import backLogo from "../../assets/back-logo.svg";
import starIcon from "../../assets/star-icon.svg";
import { logoutPJ } from "../../services/loginUserPJ";
import { logoutPF } from "../../services/loginUserPF";

import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";

// Barra lateral
const Sidebar: React.FC = () => {
  const [isSidebarVisible, setIsSidebarVisible] = useState(true);
  const [cookiesAuth] = useCookies(["authToken"]);
  const [cookiesLogin] = useCookies(["login"]);
  var login_size = cookiesLogin.login ? cookiesLogin.login.toString() : '';

  const navigate= useNavigate();

  const logoutUsuarioPJ = async () => {
    console.log(cookiesAuth.authToken)
    const logoutUsuarioPJ = await logoutPJ(cookiesAuth.authToken);
    navigate("/");
    return logoutUsuarioPJ
  };


  
  const logoutUsuarioPF = async () => {
    console.log(cookiesAuth.authToken)
    const logoutUsuarioPJ = await logoutPF(cookiesAuth.authToken);
    navigate("/");
    return logoutUsuarioPJ
  };


  const toggleSidebar = () => {
    setIsSidebarVisible(!isSidebarVisible);
  };


  return (
    <>
      {isSidebarVisible ? (
        <aside className="sidebar">
          <nav className="sidebar_nav">
            {/* Logo da Vivo */}
            <div className="back-logo">
              <a>
                <img
                  onClick={toggleSidebar}
                  className="logo"
                  src={backLogo}
                ></img>
              </a>
            </div>
            <div className="nimbus-container">
              <h2 className="nimbus-title">Nimbus</h2>
            </div>
            {/* Opções do menu */}
            <div className="options-container">
              <ul>
                <li className="li-container">
                  <img
                    className="star-icon"
                    src={starIcon}
                    alt="Logo da Vivo"
                  />
                  <a href="/home">Home</a>
                </li>
                <li className="li-container">
                  <img
                    className="star-icon"
                    src={starIcon}
                    alt="Logo da Vivo"
                  />
                  <a href="/plano">Meu Plano</a>
                </li>
                <li className="li-container">
                  <img
                    className="star-icon"
                    src={starIcon}
                    alt="Logo da Vivo"
                  />
                  <a href="/perfil">Perfil</a>
                </li>
                <li className="li-container">
                  <img
                    className="star-icon"
                    src={starIcon}
                    alt="Logo da Vivo"
                  />
                   <button className="button-logout" onClick={() => {
                    if(login_size.length == 14){
                      logoutUsuarioPJ();

                    } else if( login_size.length == 11 ) {
                      logoutUsuarioPF();
                    }
                    
                    
                    }}><a>Sair</a></button>
                </li>
              </ul>
            </div>
          </nav>
        </aside>
      ) : (
        <div className="sidebar-short" onClick={toggleSidebar}>
          <img className="show-logo" src={backLogo}></img>
        </div>
      )}
    </>
  );
};

export default Sidebar;
