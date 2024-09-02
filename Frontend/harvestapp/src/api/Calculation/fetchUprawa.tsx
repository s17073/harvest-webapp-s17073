import { ICropData } from "../../components/Calculation/CropsFormTable";

export const fetchUprawa = async (
  idKalkulacja: number,
  idUprawa: number,
): Promise<ICropData | undefined> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(
    `${apiUrl}/calculation/${idKalkulacja}/crop/${idUprawa}`,
  );
  if (response.status != 200) return undefined;
  const data = await response.json();
  return data;
};
