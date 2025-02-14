import axios from "axios";

export const signInUser = async (dataToAdd: Object): Promise<boolean> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;

  const response = await axios.post(`${apiUrl}/user/register`, dataToAdd);

  if (response.status === 200) {
    return response.data;
  } else {
    return false;
  }
};
