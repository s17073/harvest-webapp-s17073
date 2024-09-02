import axios from "axios";

export const handleDeleteLivestock = async (
  calcId: number,
  livestockId: number,
) => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  await axios.delete(
    `${apiUrl}/calculation/${calcId}/livestock/${livestockId}`,
  );
};
