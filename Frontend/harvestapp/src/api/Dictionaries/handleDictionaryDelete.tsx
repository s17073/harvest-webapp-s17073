import axios from "axios";

export const handleDictionaryDelete = async (
  id: number,
  endpoint: string,
  setData: any,
  fetchDictionaryData: any,
  setAnnouncement: any,
) => {
  try {
    const apiUrl = import.meta.env.VITE_BACKEND_URL;

    const response = await axios.delete(`${apiUrl}/${endpoint}/${id}`);
    // console.log(`Crop kind id: ${id} delete method status: ${response.status}`);
    if (response.status === 200) {
      setData([]);
      await fetchDictionaryData();
    } else {
      throw new Error();
    }
  } catch (err) {
    // console.log(`Crop kind id: ${id} delete error: ${(err as Error).message}`);
    setAnnouncement(`Wystąpił błąd podczasu usuwania elementu od id ${id}`);
  }
};
