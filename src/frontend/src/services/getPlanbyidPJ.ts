import axios from "axios";
import { useCookies } from "react-cookie";


const apiUrl = import.meta.env.VITE_API_URL;
const port_usuario_pj = import.meta.env.VITE_PORT_USUARIO_PJ;

export const useApiPlanPJ = () => {
  const [cookiesAuth] = useCookies(['authToken']);

  const api = axios.create({
    baseURL: `http://${apiUrl}:${port_usuario_pj}`,
    withCredentials: true,
    
  });

  return api;
}

// export const addAuthTokenToApiPlanPJ = (api: any, cookies: any) => {
//   api.interceptors.request.use((config: any) => {
//     config.headers.Authorization = `Bearer ${cookies.authToken}`;

//     return config;
//   });
// };

// Função para buscar os dados do usuário
export const getPlansPJ = async (api: any, cookies: string) => {
  try {
    const response: any = await api.get(`/usuario_pj/planos_usuario`, {
      headers: {
        'Authorization': `Bearer ${cookies}`,
      }
    });
    console.log(response.data);
    return response;
  } catch (error) {
    console.log("Erro ao buscar usuário");
    throw error;
  }
}