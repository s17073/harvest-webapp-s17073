import axios from "axios";
import { ILivestock } from "../../interfaces/ILivestock";

export const handleAddLivestock = async (
  id: number,
  data: ILivestock,
  livestockId?: number,
) => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  if (livestockId) {
    await axios.put(
      `${apiUrl}/calculation/${id}/livestock/${livestockId}`,
      data,
    );
  } else {
    await axios.put(`${apiUrl}/calculation/${id}/livestock`, data);
  }
};
