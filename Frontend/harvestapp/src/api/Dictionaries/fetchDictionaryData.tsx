import axios from "axios";

export const fetchDictionaryData = async (
  endpoint: string,
  setNoData: any,
  setData: any,
  setFetchError: any,
  setLoading: any,
) => {
  setLoading("Ładowanie danych...");
  // await new Promise((resolve) => setTimeout(resolve, 1000)); //sztuczne opoznienie o 1 sec
  try {
    const apiUrl = import.meta.env.VITE_BACKEND_URL;

    const response = await axios.get<any[]>(`${apiUrl}/${endpoint}`);
    // console.log("Crop kind data get method status: ", response.status);
    if (response.data.length === 0) {
      setNoData("Brak danych do wyświetlenia.");
    } else {
      setData(response.data);
    }
  } catch (err) {
    setFetchError("Wystąpił błąd przy próbie załadowania danych.");
    // console.log(`Crop kind data get method error: ${(err as Error).message}`);
  } finally {
    setLoading(false);
  }
};
