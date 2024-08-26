import axios from "axios";
import { IStepInsurancePeriod } from "../interfaces/IStepInsurancePeriod";

export const handleAddInsurancePeriod = async (
  id: number,
  data: IStepInsurancePeriod,
) => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  await axios.put(`${apiUrl}/calculation/${id}`, data);
};
