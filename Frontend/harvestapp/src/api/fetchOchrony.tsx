import { ICoverList } from "../interfaces/ICoverList";

export const fetchOchrony = async (
  coveredType: "uprawy" | "zwierzeta",
): Promise<ICoverList[]> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(
    `${apiUrl}/cover/coverlist?coveredtype=${coveredType}`,
  );

  const data = await response.json();
  return data;
};
