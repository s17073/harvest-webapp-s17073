import axios from "axios";

export const startNewCalculationWithUserData = async (
  id: number,
  email: string,
) => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await axios.put(`${apiUrl}/calculation/new/${id}/${email}`);

  return response;
};
