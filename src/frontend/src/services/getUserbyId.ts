import axios from "axios";

// Função para buscar os dados do usuário
export const getUserbyId = async (cpf: string) => {
  try {
    // Await the AxiosIstance() function call
    const response = await axios.get(`http://localhost:8085/usuario_pf/${cpf}`);
    return response.data;
  } catch (error) {
    console.log("Erro ao buscar usuário");
    throw error;
  }
};

export const getUserbyIdCNPJ = async (cnpj: string) => {
  try {
    // Await the AxiosIstance() function call
    const response = await axios.get(`http://localhost:8086/usuario_pj/${cnpj}`);
    return response.data;
  } catch (error) {
    console.log("Erro ao buscar usuário");
    throw error;
  }
};
