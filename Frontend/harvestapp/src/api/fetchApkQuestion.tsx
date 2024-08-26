import { IApk } from "../interfaces/IApk";

export const fetchApkQuestions = async (): Promise<IApk[]> => {
  const apiUrl = import.meta.env.VITE_BACKEND_URL;
  const response = await fetch(`${apiUrl}/apk/questions`);
  const dane = await response.json();
  return dane;
};
