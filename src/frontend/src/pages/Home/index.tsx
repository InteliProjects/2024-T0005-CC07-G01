import React, { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import BottomNavbar from "../../components/BottomNavbar";
import "./styles.scss";
import imageHome from "../../assets/image-home.svg";
import BenefitsContainer from "../../components/BenefitsContainer";
import { getUserbyId, getUserbyIdCNPJ } from "../../services/getUserbyId";
import { useCookies } from "react-cookie";
import { Cookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import { useApiPlanPJ } from "../../services/getPlanbyidPJ";
import {
  getProfileUsuarioPF,
  useApiUsuarioPF,
} from "../../services/getProfileUsuarioPF";
import {
  getProfileUsuarioPJ,
  useApiUsuarioPJ,
} from "../../services/getProfileUsuarioPJ";

import moment from "moment";
import { useApiPlanPF } from "../../services/getPlanbyidPF";

export const Home: React.FC = () => {
  const [isLargeScreen, setIsLargeScreen] = useState(window.innerWidth >= 1080);
  const [userName, setUserName] = useState("");
  const [razaoSocial, setRazaoSocial] = useState("");

  const apiUsuarioPJ = useApiUsuarioPJ();
  const apiUsuarioPF = useApiUsuarioPF();

  const [cookiesAuth] = useCookies(["authToken"]);
  const [cookiesLogin] = useCookies(["login"]);
  const [tokenChecked, setTokenChecked] = useState(false);


  var login_size = cookiesLogin.login ? cookiesLogin.login.toString() : '';
  
  const cookies = new Cookies();
  const navigate = useNavigate();

//   useEffect(() => {
//     if (!tokenChecked && !cookiesAuth.authToken) {
//       navigate("/nao-autenticado");
//       setTokenChecked(false); 
//     }
//  }, [cookiesAuth.authToken, navigate, tokenChecked]);

  

  useEffect(() => {
    function handleResize() {
      setIsLargeScreen(window.innerWidth >= 1080);
    }
    window.addEventListener("resize", handleResize);
    handleResize();
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  useEffect(() => {
    const fetchPlanData = async () => {
      console.log(cookiesLogin.login);
      console.log("Valor " + login_size);

      if (login_size.length === 14) {
        console.log("PJ");
        const response = await getProfileUsuarioPJ(
          apiUsuarioPJ,
          cookiesAuth.authToken
        );
        setUserName(response.data.nome);
      } else if (login_size.length === 11) {
        console.log("PF");

        const response = await getProfileUsuarioPF(
          apiUsuarioPF,
          cookiesAuth.authToken
        );
        setUserName(response.data.nome);
      }
    };

    if (cookiesAuth.authToken) {
      fetchPlanData();
    }
  }, []);

  return (
    <Layout
      showNavbar={true}
      showSidebar={isLargeScreen}
      useBottomNavbar={!isLargeScreen}
    >
      <div className="container">
        <div className="head-container">
          <div className="article-container">
            <h1 className="article-title">__</h1>
            <h1>Olá {userName}</h1>
            <p className="article-text">
              Fevereiro é mês da Internet Segura. Aproveite para ler as dicas
              que preparamos para você.
            </p>
            <button className="button-tips">Ler as dicas</button>
          </div>
          <div className="image-container">
            <img
              className="img"
              src={imageHome}
              alt="Dicas de Internet Segura"
            ></img>
          </div>
        </div>
        <div className="body-container">
          <BenefitsContainer
            clientType="Cliente Platinum"
            benefitsLink="/platinum"
          />
          <BenefitsContainer clientType="Cliente Gold" benefitsLink="/gold" />
        </div>
      </div>
    </Layout>
  );
};
