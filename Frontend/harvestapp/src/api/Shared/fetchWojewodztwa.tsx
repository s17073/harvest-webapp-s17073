import { PartOfTeryt } from "../../interfaces/PartOfTeryt";

export const fetchWojewodztwa = async (): Promise<PartOfTeryt[]> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/teryt/wojewodztwa`);
  const data = await response.json();
  return data;
};
