import { ICrop } from "../../interfaces/ICrop";

export const fetchUprawy = async (): Promise<ICrop[]> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/cropkind/croplist`);
  const data = await response.json();
  return data;
};
