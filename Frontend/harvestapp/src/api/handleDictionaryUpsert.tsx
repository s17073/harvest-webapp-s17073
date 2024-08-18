import axios from "axios";

export const handleDictionaryUpsert = async (
  event: React.FormEvent<HTMLFormElement>,
  api: string,
  method: string,
  dataToAdd: Object,
  setAnnouncement: any,
) => {
  event.preventDefault();

  try {
    let response;

    if (method === "POST") {
      response = await axios.post(api, dataToAdd);
    } else if (method === "PUT") {
      response = await axios.put(api, dataToAdd);
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
