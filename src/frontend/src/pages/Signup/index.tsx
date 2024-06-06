import React, { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import "./styles.scss";
import { createUsuarioPf, createUsuarioNormal, useApiUsuarioPF, useApiUsuarioNormal, useApiUsuarioPJ, createUsuarioPj  } from "../../services/createUsuario.ts";
import InputField from '../../components/InputField/InputField';
import Button from '../../components/Button/Button.tsx';
import { useNavigate } from "react-router-dom";

// O componente Signup gerencia o registro de novos usuários, tanto pessoas físicas quanto jurídicas.
const Signup: React.FC = () => {
  
  const navigate = useNavigate(); // Hook para redirecionamento

  // Estados para gerenciar as informações de entrada do formulário.
  const [selectedFlow, setSelectedFlow] = useState("page1");
  const [nome, setNome] = useState("");
  const [razao_social, setRazaoSocial] = useState("");
  const [email, setEmail] = useState("");
  const [data_nascimento, setDataNascimento] = useState("");
  const [senha, setSenha] = useState("");
  const [rg, setRg] = useState("");
  const [login, setLogin] = useState("");
  const apiUsuarioNormal= useApiUsuarioNormal();
  const apiUsuarioPf= useApiUsuarioPF();
  const apiUsuarioPj= useApiUsuarioPJ();

  const [pjData, setPjData] = useState({
    login: "",
    razao_social: "",
    email: "",
    senha: "",
    data_nascimento: "",
  });

  // Função para enviar os dados do formulário para a API
  const handleSubmitPJ = async () => {

    const now = new Date();
    const formattedDate = now.toISOString();

    try {
        const usuarioFormData = {
          tipo: "Pessoa Jurídica",
          nome: razao_social, 
          email,
          saldo: "100",
          data_cadastro: formattedDate
        };

        const usuarioId = await createUsuarioNormal(apiUsuarioNormal, usuarioFormData);

        console.log("Id Usuario" + usuarioId)
        const usuarioPjFormData = {
          login, 
          role: "USUARIOPJ",
          razao_social,
          senha,
          data_nascimento,
          id_usuario: usuarioId
        };

        console.log("usuarioPfFormData:", usuarioPjFormData);

        await createUsuarioPj(apiUsuarioPj, usuarioPjFormData, usuarioId);
        navigate('/'); // Redireciona para a página principal após o sucesso
    } catch (error) {
        console.log("Erro ao criar usuário PF", error);
    }
};

// Função para enviar os dados do formulário para a API
  const handleSubmitPF = async () => {

    const now = new Date();
    const formattedDate = now.toISOString();

    try {
        const usuarioFormData = {
          tipo: "Pessoa Física",
          nome, 
          email,
          saldo: "100",
          data_cadastro: formattedDate
        };

        const usuarioId = await createUsuarioNormal(apiUsuarioNormal, usuarioFormData);

        console.log("Id Usuario" + usuarioId)
        const usuarioPfFormData = {
          login, 
          role: "USUARIOPF",
          nome,
          rg,
          senha,
          data_nascimento,
          id_usuario: usuarioId
        };

        console.log("usuarioPfFormData:", usuarioPfFormData);

        await createUsuarioPf(apiUsuarioPf, usuarioPfFormData, usuarioId);
        navigate('/'); // Redireciona para a página principal após o sucesso
    } catch (error) {
        console.log("Erro ao criar usuário PF", error);
    }
};


  // Função para redirecionar para o fluxo selecionado
  const handleFlowSelection = (flow: string) => {
    setSelectedFlow(flow);
  };

  // Função para resetar o fluxo de cadastro para a seleção inicial.
  const resetFlow = () => {
    setSelectedFlow("page1");
  };

  useEffect(() => {
    console.log("PJ Data:", JSON.stringify(pjData));
  }, [pjData]);

  // Renderização condicional baseada no fluxo selecionado.
  return (
    <Layout showNavbar={false} showSidebar={false}>
    <div className="signup-container">
      
      {/* Bloco de seleção inicial para tipo de cadastro */}
      {selectedFlow === 'page1' && (
        <>
          <h2 className="title">Cadastro Vivo</h2>
          <Button label="Pessoa Física  " onClick={() => handleFlowSelection('pessoaFisica')} />
          <div className="ortext">Ou</div>
          <Button label="Pessoa Jurídica" onClick={() => handleFlowSelection('pessoaJuridica')} />
        </>
      )}
      {/* Bloco para cadastro de pessoa Física */}
      {selectedFlow === 'pessoaFisica' && (
        <div className="pf-container">
          <h2 className="title-signup-pf">Cadastro Vivo Pessoa Física</h2>
          
          {/* Campos do formulário para cadastro de pessoa física */}
          <label htmlFor="cpf">CPF:</label>
          <InputField name="cpf" type="text" placeholder="Digite seu CPF aqui" value={login} onChange={(e) => setLogin(e.target.value)} />
          <label htmlFor="nome">Nome:</label>
          <InputField name="nome" type="text" placeholder="Digite seu nome aqui" value={nome} onChange={(e) => setNome(e.target.value)} />
          <label htmlFor="email">Email:</label>
          <InputField name="email" type="email" placeholder="Digite seu email aqui" value={email} onChange={(e) => setEmail(e.target.value)} />
          <label htmlFor="rg">RG:</label>
          <InputField name="rg" type="text" placeholder="Digite seu RG aqui" value={rg} onChange={(e) => setRg(e.target.value)} />
          <label htmlFor="senha">Senha:</label>
          <InputField name="senha" type="password" placeholder="Digite sua senha aqui" value={senha} onChange={(e) => setSenha(e.target.value)} />
          <label htmlFor="dataNascimento">Data de Nascimento:</label>
          <InputField name="dataNascimento" type="date" placeholder="Data de Nascimento" value={data_nascimento} onChange={(e) => setDataNascimento(e.target.value)} />
          <div className="button-container">
            <Button label="Voltar" onClick={() => setSelectedFlow('page1')} />
            <Button label="Enviar" onClick={handleSubmitPF} />
          </div>
        </div>
      )}
      
      {/* Bloco para cadastro de pessoa Jurídica */}
      {selectedFlow === 'pessoaJuridica' && (
        <div className="pj-container">
          <h2 className="title-signup-pj">Cadastro Vivo Pessoa Jurídica</h2>
          {/* Campos do formulário para cadastro de pessoa jurídica */}
          <label htmlFor="cnpj">CNPJ:</label>
          <InputField name="cnpj" type="text" placeholder="Digite seu CNPJ aqui" value={login} onChange={(e) => setLogin(e.target.value)} />
          <label htmlFor="razaoSocial">Razão Social:</label>
          <InputField name="razaoSocial" type="text" placeholder="Digite a razão social aqui" value={razao_social} onChange={(e) => setRazaoSocial(e.target.value)} />
          <label htmlFor="email">Email:</label>
          <InputField name="email" type="email" placeholder="Digite o email da empresa aqui" value={email} onChange={(e) => setEmail(e.target.value)}/>
          <label htmlFor="senha">Senha:</label>
          <InputField name="senha" type="password" placeholder="Crie uma senha" value={senha} onChange={(e) => setSenha(e.target.value)}/>
          <label htmlFor="dataFundacao">Data de Fundação:</label>
          <InputField name="dataFundacao" type="date" placeholder="Data de Fundação" value={data_nascimento} onChange={(e) => setDataNascimento(e.target.value)} />

          {/* Botões para voltar ou enviar formulário */}
          <div className="button-container">
            <Button label="Voltar" onClick={() => setSelectedFlow('page1')} />
            <Button label="Enviar" onClick={handleSubmitPJ} />
          </div>
          </div>
        )}
      </div>
    </Layout>
  );
};

export default Signup;