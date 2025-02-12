import axios from "axios";

export const handleAcceptOffer = async (
  idCalculation: number,
  idOffer: number,
) => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await axios.post(
    `${apiUrl}/calculation/${idCalculation}/acceptOffer/${idOffer}`,
  );
  if (response.status != 200) return undefined;
  return response.data;
};
