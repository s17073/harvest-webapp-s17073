import { PartOfTeryt } from "../../interfaces/PartOfTeryt";

export const fetchAgenci = async (): Promise<PartOfTeryt[]> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;

  const response = await fetch(`${apiUrl}/agent`);

  const data = await response.json();
  return data;
};
