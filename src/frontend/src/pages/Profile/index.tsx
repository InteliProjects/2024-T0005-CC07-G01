import React, { useEffect, useState } from "react";
import "./styles.scss";
import Layout from "../../components/Layout";
import InputFieldProfile from "../../components/InputFieldProfile";
import { getUserbyId } from "../../services/getUserbyId"; // Certifique-se que o caminho está correto
import { getProfileUsuarioPJ } from "../../services/getProfileUsuarioPJ";
import { useApiPlanPJ } from "../../services/getPlanbyidPJ";
import { useCookies } from "react-cookie";
import { Moment } from "react-moment";
import moment from "moment";
import { getProfileUsuarioPF } from "../../services/getProfileUsuarioPF";
import { useApiPlanPF } from "../../services/getPlanbyidPF";

// Página de perfil do usuário
function Profile() {
  // Estado para armazenar os dados do usuário
  const [userData, setUserData] = useState({
    nome: "",
    telefone: "99643330",
    cpf: "",
    rg: "",
    data_nascimento: "",
    data_cadastro: "",
  });

  // Hooks para buscar os dados do plano do usuário
  const apiUsuarioPJ = useApiPlanPJ();
  const apiUsuarioPF = useApiPlanPF();

  const [cookiesAuth] = useCookies(["authToken"]);
  const [cookiesLogin] = useCookies(["login"]);
  var login_pf: boolean = false;

  var login_size = cookiesLogin.login.toString();
  console.log(login_size);

  // Hook para buscar os dados do usuário
  useEffect(() => {
    const fetchPlanData = async () => {
      if (login_size.length == 14) {
        const response = await getProfileUsuarioPJ(
          apiUsuarioPJ,
          cookiesAuth.authToken
        );
        console.log("Resposta " + response.data);
        var dateTime = moment
          .unix(response.data.data_cadastro / 1000)
          .format("DD/MM/YYYY");
        login_pf = false;
        console.log(dateTime);
        response.data.data_cadastro = dateTime;
        setUserData(response.data);
      } else if (login_size.length == 11) {
        const response = await getProfileUsuarioPF(
          apiUsuarioPF,
          cookiesAuth.authToken
        );
        console.log("Resposta " + response.data);
        var dateTime = moment
          .unix(response.data.data_cadastro / 1000)
          .format("DD/MM/YYYY");
        login_pf = true;

        console.log(dateTime);
        response.data.data_cadastro = dateTime;
        setUserData(response.data);
      }
    };

    if (cookiesAuth.authToken) {
      fetchPlanData();
    }
  }, []);

  // Função para formatar um timestamp Unix em uma string de data
  function formatUnixTimestamp(timestamp: any) {
    const date = new Date(timestamp * 1000); // Multiplica por 1000 para converter de segundos para milissegundos
    const day = date.getDate();
    const month = date.getMonth() + 1; // Os meses em JavaScript começam em 0
    const year = date.getFullYear();

    // Formata a data no formato DD/MM/YYYY
    return `${day < 10 ? "0" + day : day}/${
      month < 10 ? "0" + month : month
    }/${year}`;
  }

  // Controladores para a Sidebar e BottomNavbar com base na largura da tela
  const [showSidebar, setShowSidebar] = useState(window.innerWidth > 1200);
  const [useBottomNavbar, setUseBottomNavbar] = useState(
    window.innerWidth <= 932
  );

  // Hook para atualizar o estado de acordo com o tamanho da tela
  useEffect(() => {
    const handleResize = () => {
      setShowSidebar(window.innerWidth > 1200);
      setUseBottomNavbar(window.innerWidth <= 932);
    };

    window.addEventListener("resize", handleResize);
    handleResize(); // Inicializa com base na largura da tela

    return () => window.removeEventListener("resize", handleResize);
  }, []);

  // Renderização da página de perfil
  return (
    <Layout
      showNavbar={true}
      useBottomNavbar={useBottomNavbar}
      showSidebar={showSidebar}
    >
      {/* Renderização condicional da Sidebar */}
      <div className="profile-container">
        <h2 className="profile-title">Meu Perfil</h2>
        <div className="profile-header">
          <div className="profile-photo">
            <img src="" alt="Perfil" className="profile-image" />
          </div>
          <div className="profile-info">
            <InputFieldProfile label="Nome" value={userData.nome} />
            <p className="profile-number">{userData.email || "E-mail"}</p>
          </div>
        </div>
        {/* Dados do usuário */}
        {login_size.length == 11 ? (
          <div className="profile-details">
            <InputFieldProfile label="CPF" value={userData.login} />
            <InputFieldProfile label="RG" value={userData.rg} />
            <InputFieldProfile
              label="Data de Nascimento"
              value={userData.data_cadastro}
            />
          </div>
        ) : (
          <div className="profile-details">
            <InputFieldProfile label="CNPJ" value={userData.login} />
            <InputFieldProfile
              label="Razão Social"
              value={userData.razao_social}
            />
             <InputFieldProfile
              label="Data de Cadastro"
              value={userData.data_cadastro}
            />
            
          </div>
        )}
      </div>
    </Layout>
  );
}

export default Profile;
