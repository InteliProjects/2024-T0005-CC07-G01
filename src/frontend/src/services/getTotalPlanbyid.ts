import axios from "axios";

// Função para buscar os dados do usuário
export const getProductsbyId = async (id: string) => {
  try {
    // Await the AxiosIstance() function call
    const response = await axios.get(`http://internal-backend-alb-1783929086.us-east-1.elb.amazonaws.com:8082/plano/${id}`);
    return response.data;
  } catch (error) {
    console.log("Erro ao buscar usuário");
    throw error;
  }
};
