import axios from "axios";

export const fetchPolicyData = async (id: any) => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await axios.get(`${apiUrl}/policy/${id}`);

  if (response.status === 200) {
    const policyData = await response.data;
    return policyData;
  }
};
