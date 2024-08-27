import { PartOfTeryt } from "../../interfaces/PartOfTeryt";

export const fetchGminy = async (kodTeryt: string): Promise<PartOfTeryt[]> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/teryt/gminy?teryt=${kodTeryt}`);

  const data = await response.json();
  return data;
};
