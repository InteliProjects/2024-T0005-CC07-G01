import axios from 'axios';
import { useCookies } from 'react-cookie';


const apiUrl = import.meta.env.VITE_API_URL;
const port_usuario_pf = import.meta.env.VITE_PORT_USUARIO_PF;
const port_usuario_pj = import.meta.env.VITE_PORT_USUARIO_PJ;

const port_usuario = import.meta.env.VITE_PORT_USUARIO;


  export const useApiUsuarioNormal = () => {
    const [cookiesAuth] = useCookies(['authToken']);
  
    const api = axios.create({
      baseURL: `http://${apiUrl}:${port_usuario}`,
      withCredentials: true,
      
    });
  
    return api;
  }

  export const useApiUsuarioPF = () => {
    const [cookiesAuth] = useCookies(['authToken']);
  
    const api = axios.create({
      baseURL: `http://${apiUrl}:${port_usuario_pf}`,
      withCredentials: true,
      
    });
  
    return api;
  }
  
  export const useApiUsuarioPJ = () => {
    const [cookiesAuth] = useCookies(['authToken']);
  
    const api = axios.create({
      baseURL: `http://${apiUrl}:${port_usuario_pj}`,
      withCredentials: true,
      
    });
  
    return api;
  }

export const createUsuarioNormal = async (api: any, formData: any) => {
    try{
        const response= await api.post('/usuario', formData, {
            headers: {
                'Content-Type': 'application/json'
            }
            
        });
        console.log("Resposta " + response.data.id_usuario)
        return response.data.id_usuario;

    } catch (error) {
        console.log("Erro ao criar usuário");
        throw error;
    }
}

// Função para criar um usuário na tabela 'usuário_pf' do banco de dados.
export const createUsuarioPf = async (api: any, formData: any, usuarioId: string) => {
    try{
        formData.id_usuario = usuarioId;

        const response= await api.post('/usuario_pf/register', formData, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        return response.data;

    } catch (error) {
        console.log("Erro ao criar usuário");
        throw error;
    }
}

export const createUsuarioPj = async (api: any, formData: any, usuarioId: string) => {
    try{
        formData.id_usuario = usuarioId;

        const response= await api.post('/usuario_pj/register', formData, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        return response.data;

    } catch (error) {
        console.log("Erro ao criar usuário");
        throw error;
    }
}