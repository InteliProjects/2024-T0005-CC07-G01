import React, { useEffect, useState } from "react";
import "./styles.scss";
import Layout from "../../components/Layout";
import axios from "axios";
import {
  getPlans,
  getPlansPJ,
  useApiPlanPJ,
} from "../../services/getPlanbyidPJ";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import { getPlansPF, useApiPlanPF } from "../../services/getPlanbyidPF";

const Plan: React.FC = () => {
  // Estados para gerenciar os dados do plano do usuário
  const [planData, setPlanData] = useState({
    data_inicio: "",
    id_plano: "",
    telefone: "",
    valor: "",
    contrato: "",
    nome: " ",
    data_final: "",
    fatura: "",
    id_usuario: "",
    qtd_internet_restante: "",
    qtd_internet_consumido: "",
    id_endereco: "",
    status: "",
    qtd_internet: "",
  });

  // Hooks para buscar os dados do plano do usuário
  const apiPlanPJ = useApiPlanPJ();
  const apiPlanPF = useApiPlanPF();

  const [cookiesAuth] = useCookies(["authToken"]);
  const [cookiesLogin] = useCookies(["login"]);

  const navigate = useNavigate();

  var login_size = cookiesLogin.login.toString();
  console.log(login_size);


  // Hook para verificar se o usuário está autenticado
  useEffect(() => {
    if (!cookiesAuth.authToken) {
      navigate("/nao-autenticado");
    }
  }, [cookiesAuth.authToken, navigate]);

  const [isLargeScreen, setIsLargeScreen] = useState(window.innerWidth > 1080);

  // Hook para atualizar o estado de acordo com o tamanho da tela
  useEffect(() => {
    const handleResize = () => {
      setIsLargeScreen(window.innerWidth > 1080);
    };

    window.addEventListener("resize", handleResize);
    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  // Função para buscar os dados do plano do usuário
  useEffect(() => {
    const fetchPlanData = async () => {
      if(login_size.length == 14){
        const response = await getPlansPJ(apiPlanPJ, cookiesAuth.authToken);
        console.log("Resposta " + response.data)
        setPlanData(response.data); 
      } else if(login_size.length == 11){
        const response = await getPlansPF(apiPlanPF, cookiesAuth.authToken);
        console.log("Resposta " + response.data)
        setPlanData(response.data); 
      }
    };

    if (cookiesAuth.authToken) {
      fetchPlanData();
    }
  }, []);

  console.log(planData.fatura)

  // Renderização da página de plano
  return (
    <Layout
      showNavbar={true}
      showSidebar={isLargeScreen}
      useBottomNavbar={!isLargeScreen}
    >
      <div className="plan-container">
        <h1 className="plan-title">Meu Plano</h1>
        <div className="plan-details">
          <h2 className="plan-name">Plano {planData.qtd_internet}</h2>
          <p className="plan-number">{planData.telefone}</p>
        </div>
        <br></br>
        <div className="data-usage">
          <div className="usage-circle">
            <h3 className="used-title">Já usei</h3>
            <p className="used-data">{planData.qtd_internet_consumido}</p>
            <p className="total-data">de {planData.qtd_internet_restante}</p>
          </div>
        </div>
      </div>
    </Layout>
  );
};

export default Plan;
