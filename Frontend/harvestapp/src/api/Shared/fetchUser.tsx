import axios from "axios";

export const fetchUser = async (userEmail: string | null): Promise<any> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;

  const response = await axios.get(`${apiUrl}/user/${userEmail}`);
  console.log(response.data);

  return response.data;
};
