import { ICrop } from "../../interfaces/ICrop";

export const fetchUprawy = async (): Promise<ICrop[]> => {
  const token = localStorage.getItem("token");
  const apiUrl = import.meta.env.VITE_BACKEND_URL;

  const response = await fetch(`${apiUrl}/cropkind/croplist`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  const data = await response.json();
  return data;
};
