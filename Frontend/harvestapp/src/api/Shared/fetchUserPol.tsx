import axios from "axios";

export const fetchUserPol = async (
  userEmail: string | null,
): Promise<Object> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await axios.get(`${apiUrl}/user/${userEmail}/pol`);

  return response.data;
};
