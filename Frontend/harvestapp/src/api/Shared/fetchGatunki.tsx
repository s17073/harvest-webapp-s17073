import { ICropVarietyList } from "../../interfaces/ICropVarietyList";

export const fetchGatunki = async (
  idUprawy: number,
): Promise<ICropVarietyList[]> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(
    `${apiUrl}/cropvariety/cropvarietylist?cropid=${idUprawy}`,
  );

  if (response.status == 200) {
    const data = await response.json();
    return data;
  } else {
    const data: ICropVarietyList[] = [];
    return data;
  }
};
