package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class EmptyInsuranceCompanyNumberException extends DictionaryException {
    public EmptyInsuranceCompanyNumberException() {
        super("Insurance Company Number cannot be null or empty");
    }
}
