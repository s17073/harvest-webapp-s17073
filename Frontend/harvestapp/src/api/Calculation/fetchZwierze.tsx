import { ILivestock } from "../../interfaces/ILivestock";

export const fetchZwierze = async (
  idKalkulacja: number,
  idZwierze: number,
): Promise<ILivestock | undefined> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(
    `${apiUrl}/calculation/${idKalkulacja}/livestock/${idZwierze}`,
  );
  if (response.status != 200) return undefined;
  const data = await response.json();
  return data;
};
