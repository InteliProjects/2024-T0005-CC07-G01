import axios from "axios";
import { useCookies } from "react-cookie";


const apiUrl = import.meta.env.VITE_API_URL;
const port_usuario_pf = import.meta.env.VITE_PORT_USUARIO_PF;

export const useApiUsuarioPF = () => {
  const [cookiesAuth] = useCookies(['authToken']);

  const api = axios.create({
    baseURL: `http://${apiUrl}:${port_usuario_pf}`,
    withCredentials: true,
  });

  return api;
}

// export const addAuthTokenToApiPlanPF = (api: any, cookies: any) => {
//   api.interceptors.request.use((config: any) => {
//     config.headers.Authorization = `Bearer ${cookies.authToken}`;

//     return config;
//   });
// };

// Função para buscar os dados do usuário
export const getProfileUsuarioPF = async (api: any, cookies: string) => {
  try {
    const response: any = await api.get(`/usuario_pf/usuario`, {
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