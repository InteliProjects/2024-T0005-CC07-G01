import React from 'react';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { useNavigate } from 'react-router-dom';
import './styles.scss';
import Logo from '../../assets/vivo-logo.svg';
import qrCode from '../../assets/qr-code-vivo.svg';
import InputField from '../../components/InputField/InputField';
import { loginUserPF } from '../../services/loginUserPF';
import { loginUserPJ } from '../../services/loginUserPJ';

// Página de login
const Signin = () => {
 const navigate = useNavigate();

 // Formik é uma biblioteca que auxilia na criação de formulários em React
 const formik = useFormik({
    initialValues: {
      login: '',
      senha: '',
    },
    validationSchema: Yup.object({
      login: Yup.string()
        .required('O login é obrigatório')
        .min(11, 'O login deve ter 11 caracteres')
        .matches(/^[0-9]+$/, 'O login deve conter apenas números'),
      senha: Yup.string()
        .required('A senha é obrigatória')
        .min(6, 'A senha deve ter pelo menos 6 caracteres'),
    }),
    onSubmit: async (values) => {
      try {

        if (values.login.length === 11) {
          const data = await loginUserPF(values.login, values.senha);
          console.log("Data PF" + data);
          navigate('/home'); 

        } else if (values.login.length === 14) {
          const data = await loginUserPJ(values.login, values.senha);
          console.log("Data PJ " + data);
          navigate('/home'); 

        } else {
          console.error('login length is invalid');
        }
        
      } catch (error) {
        console.error('Erro durante o login:', error);
      }
    },
 });

 // Renderização da página de login
 return (
    <div className="Signin">
      <header className="Signin-header">
        <img src={Logo} alt="Vivo Logo" className="Signin-logo" />
      </header>
      <main className="Signin-main">
        <section className="login-container">
          <h1>Login Vivo</h1>
          <form onSubmit={formik.handleSubmit}>
            <InputField
              name="login"
              type="text"
              placeholder="Digite seu CPF ou CNPJ"
              value={formik.values.login}
              onChange={formik.handleChange}
              handleBlur={formik.handleBlur}
              touched={formik.touched.login}
              errors={formik.errors.login}
            />
            <InputField
              name="senha"
              type="password"
              placeholder="Digite sua senha"
              value={formik.values.senha}
              onChange={formik.handleChange}
              handleBlur={formik.handleBlur}
              touched={formik.touched.senha}
              errors={formik.errors.senha}
            />
            <button type="submit" className="login-button">ENTRAR</button>
          </form>
          <a href="#forgot" className="forgot-password" onClick={() => navigate('/forgot-password')}>Esqueci minha senha</a>
          <p>Primeira vez aqui? <span className="sign-up" onClick={() => navigate('/cadastro')}>Cadastre-se</span></p>
        </section>
        <section className="qr-container">
          <h1>Baixe o app Vivo</h1>
          <p>Acesse as informações do seu celular, TV, internet e telefone fixo, pague faturas e muito mais!</p>
          <img src={qrCode} alt="QR Code" className="qr-code" />
          <p>Aponte a câmera do celular e baixe o app!</p>
        </section>
      </main>
    </div>
 );
};

export default Signin;