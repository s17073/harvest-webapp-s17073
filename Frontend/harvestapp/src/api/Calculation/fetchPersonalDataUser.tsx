import axios from "axios";

export const fetchPersonalDataUser = async (
  id: number,
  email: string,
): Promise<any> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = axios.get(
    `${apiUrl}/calculation/${id}/personaldata/${email}`,
  );

  if ((await response).status != 200) return undefined;
  const data = (await response).data;
  return data;
};
