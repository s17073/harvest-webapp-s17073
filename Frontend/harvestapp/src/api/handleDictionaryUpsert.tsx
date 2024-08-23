import axios from "axios";

export const handleDictionaryUpsert = async (
  event: React.FormEvent<HTMLFormElement>,
  endpoint: string,
  method: string,
  dataToAdd: Object,
  setAnnouncement: any,
) => {
  event.preventDefault();

  try {
    const apiUrl = import.meta.env.VITE_BACKEND_URL;

    if (method === "POST") {
      await axios.post(`${apiUrl}/${endpoint}`, dataToAdd);
    } else if (method === "PUT") {
      await axios.put(`${apiUrl}/${endpoint}`, dataToAdd);
    } else {
      throw new Error("Unknown HTTP method.");
    }
    // console.log(response.data);
    setAnnouncement("Rekord został dodany.");
    // console.log(response.status);
  } catch (err) {
    setAnnouncement("Wystąpił błąd przy próbie dodania rekordu.");
    // console.log((err as Error).message);
  }
};
