import axios from "axios";

export const startNewCalculation = async (): Promise<number> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await axios.post(`${apiUrl}/calculation/new`);

  if (response.status === 200) {
    const calculationId = (await response.data) as number;
    return calculationId;
  } else {
    return 0;
  }
};
