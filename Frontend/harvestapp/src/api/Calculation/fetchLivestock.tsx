import { ILivestock } from "../../interfaces/ILivestock";

export const fetchLivestock = async (
  id: number,
): Promise<ILivestock[] | undefined> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/calculation/${id}/livestock`);
  if (response.status != 200) return undefined;
  const data = await response.json();
  return data;
};
