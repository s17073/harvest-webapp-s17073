import axios from "axios";

export const handleDeleteCrop = async (calcId: number, cropId: number) => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  await axios.delete(`${apiUrl}/calculation/${calcId}/crop/${cropId}`);
};
