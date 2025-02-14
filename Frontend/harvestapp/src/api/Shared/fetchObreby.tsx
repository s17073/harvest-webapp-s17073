import { PartOfTeryt } from "../../interfaces/PartOfTeryt";

export const fetchObreby = async (
  teryt: string,
  setLoading: React.Dispatch<React.SetStateAction<string | undefined>>,
  signal: AbortSignal,
): Promise<PartOfTeryt[]> => {
  setLoading("Pobieram dane obrębu...");

  try {
    const response = await fetch(
      `https://uldk.gugik.gov.pl/?request=GetRegionById&id=${teryt}&result=teryt,region`,
      { signal },
    );

    const lines = (await response.text()).trim().split("\n");

    const obreby = lines.slice(1).map((line) => {
      const [kodTeryt, nazwa] = line.split("|");
      return { kodTeryt, nazwa };
    });
    setLoading(undefined);
    return obreby;
  } catch (e: any) {
    if (e.name === "AbortError") {
      return [];
      setLoading(undefined);
    }
    return [];
    setLoading(undefined);
  }
};
