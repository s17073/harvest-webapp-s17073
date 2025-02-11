import axios from "axios";

export const handleGenerateOffers = async (id: number): Promise<any> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await axios.post(`${apiUrl}/calculation/${id}/offers`);
  if (response.status != 200) return undefined;
  return response.data;
};
