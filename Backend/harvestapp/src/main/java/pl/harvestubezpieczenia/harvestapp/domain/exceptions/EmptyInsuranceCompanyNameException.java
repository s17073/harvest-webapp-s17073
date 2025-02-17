package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class EmptyInsuranceCompanyNameException extends DictionaryException {
    public EmptyInsuranceCompanyNameException() {
        super("Insurance Company Name cannot be null or empty");
    }
}