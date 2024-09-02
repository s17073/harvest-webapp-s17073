import { ICropData } from "../../components/Calculation/CropsFormTable";

export const fetchCrops = async (
  id: number,
): Promise<ICropData[] | undefined> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/calculation/${id}/crops`);
  if (response.status != 200) return undefined;
  const data = await response.json();
  return data;
};
