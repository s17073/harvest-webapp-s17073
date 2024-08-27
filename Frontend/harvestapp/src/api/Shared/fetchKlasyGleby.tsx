import { ISoilClassList } from "../../interfaces/ISoilClassList";

export const fetchKlasyGleby = async (): Promise<ISoilClassList[]> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/soilclass/soilclasslist`);
  const data = await response.json();
  return data;
};
