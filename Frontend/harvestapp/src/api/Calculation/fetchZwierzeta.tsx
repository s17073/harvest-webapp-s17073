import { ILivestockList } from "../../interfaces/ILivestockList";

export const fetchZwierzeta = async (): Promise<ILivestockList[]> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/livestockkind/livestocklist`);
  const data = await response.json();
  return data;
};
