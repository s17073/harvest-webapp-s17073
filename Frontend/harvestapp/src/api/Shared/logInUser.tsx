import axios from "axios";

export const logInUser = async (
  email: string,
  password: string,
): Promise<boolean> => {
  const loginData = {
    email: email,
    haslo: password,
  };

  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await axios.post(`${apiUrl}/user/login`, loginData);

  const token = response.data.token;
  const role = response.data.role;

  console.log(response);
  console.log(token);

  localStorage.setItem("token", await token);
  localStorage.setItem("userType", await role);
  localStorage.setItem("userName", loginData.email);

  if (response.status === 200) {
    return true;
  } else {
    return false;
  }
};
