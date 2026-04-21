const BASE_URL = 'http://10.0.0.112:5050'; 

export const buscarLugares = async () => {
  try {
    const response = await fetch(`${BASE_URL}/lugares`);
    return await response.json();
  } catch (error) {
    console.error('Erro ao buscar lugares:', error);
  }
};