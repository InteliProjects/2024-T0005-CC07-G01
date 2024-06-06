import axios from 'axios';
import { Cookies } from 'react-cookie';
import { jwtDecode, JwtPayload } from 'jwt-decode';

const apiUrl = import.meta.env.VITE_API_URL;
const port_usuario_pj = import.meta.env.VITE_PORT_USUARIO_PJ;

// Criando uma instância da classe Cookies para manipulação de cookies
const cookies = new Cookies();

// Criando uma instância da API Axios configurada com a URL base e opção de enviar cookies
const api = axios.create({
  baseURL: `http://${apiUrl}:${port_usuario_pj}/usuario_pj`,
  withCredentials: true,
});


const extractLoginfromToken = (token: string): JwtPayload => {
  const decoded = jwtDecode<JwtPayload>(token);
  console.log(decoded);

  return decoded;
};

// Função para realizar a autenticação do usuário
export const loginUserPJ = async (login: string, senha: string) => {
try {
    const response = await api.post('/login', { login, senha });
    console.log("Resposta " + apiUrl);
    console.log("Porta " + port_usuario_pj);

    cookies.set('authToken', response.data.token, { path: '/' });
    console.log("Response "+ response.data)
    
    const extractToken = extractLoginfromToken(response.data.token);
    cookies.set("login", extractToken.sub, { path: "/" });


    return response.data;
  } catch (error) { 
    console.error('Error during login:', error);
    throw error;
  }
};

export const logoutPJ = async (auth: any) => {
  try {
     const response = await api.post('/logout', {}, {
       headers: {
         'Authorization': `Bearer ${auth}`,
       }
     });
     console.log(auth);
     cookies.remove('authToken', { path: '/' });
     cookies.remove('login', { path: '/' });
     return response.data;
  } catch (error) {
     console.error(error);
  }
 };
 