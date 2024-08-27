export const fetchCheckFieldId = async (
  id: string,
  setLoading: React.Dispatch<React.SetStateAction<string | undefined>>,
): Promise<boolean> => {
  setLoading("Sprawdzam poprawność identyfikatora");

  const response = await fetch(
    `https://uldk.gugik.gov.pl/?request=GetParcelById&id=${id}&result=teryt,region`,
  );

  const lines = (await response.text()).trim().split("\n");

  setLoading(undefined);
  if (lines[0] == "0") return true;
  else return false;
};
