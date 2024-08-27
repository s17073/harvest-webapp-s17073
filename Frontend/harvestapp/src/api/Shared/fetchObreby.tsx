import { PartOfTeryt } from "../../interfaces/PartOfTeryt";

export const fetchObreby = async (
  teryt: string,
  setLoading: React.Dispatch<React.SetStateAction<string | undefined>>,
): Promise<PartOfTeryt[]> => {
  setLoading("Pobieram dane obrębu...");

  const response = await fetch(
    `https://uldk.gugik.gov.pl/?request=GetRegionById&id=${teryt}&result=teryt,region`,
  );

  const lines = (await response.text()).trim().split("\n");

  const obreby = lines.slice(1).map((line) => {
    const [kodTeryt, nazwa] = line.split("|");
    return { kodTeryt, nazwa };
  });

  setLoading(undefined);
  return obreby;
};
