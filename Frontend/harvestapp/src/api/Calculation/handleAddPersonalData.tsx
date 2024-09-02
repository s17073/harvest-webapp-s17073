import axios from "axios";
import { IStepPersonalData } from "../../interfaces/IStepPersonalData";

export const handleAddPersonalData = async (
  id: number,
  data: IStepPersonalData,
) => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  await axios.put(`${apiUrl}/calculation/${id}/personaldata`, data);
};
