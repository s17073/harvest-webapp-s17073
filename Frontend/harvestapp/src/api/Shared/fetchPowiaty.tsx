import { PartOfTeryt } from "../../interfaces/PartOfTeryt";

export const fetchPowiaty = async (
  kodTeryt: string,
): Promise<PartOfTeryt[]> => {
  const token = localStorage.getItem("token");
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/teryt/powiaty?teryt=${kodTeryt}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  const data = await response.json();
  return data;
};
