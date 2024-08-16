import axios from "axios";

export const fetchDictionaryDataById = async (
  id: number,
  url: string,
  setAnnouncement: any,
  setData: any,
) => {
  try {
    const response = await axios.get<any>(`${url}/${id}`);
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
