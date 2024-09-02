import axios from "axios";
import { IStepCrop } from "../../interfaces/IStepCrop";

export const handleAddCrop = async (
  id: number,
  data: IStepCrop,
  cropId?: number,
) => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  if (cropId) {
    await axios.put(`${apiUrl}/calculation/${id}/crop/${cropId}`, data);
  } else {
    await axios.put(`${apiUrl}/calculation/${id}/crop`, data);
  }
};
