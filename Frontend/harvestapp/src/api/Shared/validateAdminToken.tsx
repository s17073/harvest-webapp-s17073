import axios from "axios";

export const validateAdminToken = async (
  token: string | null,
): Promise<boolean> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await axios.get(`${apiUrl}/user/admin/session`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  if (response.status === 200) {
    return response.data;
  } else {
    return false;
  }
};
