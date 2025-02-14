import axios from "axios";

export const fetchUserCalc = async (
  userEmail: string | null,
): Promise<Object> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await axios.get(`${apiUrl}/user/${userEmail}/calc`);

  return response.data;
};
