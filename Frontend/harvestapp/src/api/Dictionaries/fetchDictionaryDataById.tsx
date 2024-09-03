import axios from "axios";

export const fetchDictionaryDataById = async (
  id: number,
  endpoint: string,
  setAnnouncement: any,
  setData: any,
) => {
  try {
    const token = localStorage.getItem("token");
    const apiUrl = import.meta.env.VITE_BACKEND_URL;

    const response = await axios.get<any>(`${apiUrl}/${endpoint}/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    if (!response.data) {
      setAnnouncement(`Nie znaleziono rekordu o id ${id}.`);
    } else {
      setData(response.data);
      // console.log(response.data);
    }
  } catch (err) {
    setAnnouncement(`Wystąpił błąd przy próbie edycji danych dla id: ${id}`);
    // console.log(
    //   `Crop kind data get y id: ${id} method error: ${(err as Error).message}`,
    // );
  }
};
