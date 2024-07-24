package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class MaxValueLowerThanMarketValueException extends DictionaryException {
    public MaxValueLowerThanMarketValueException(double maxValue, double value) {
        super("The maximum value (" + maxValue + ") cannot be lower than the market value (" + value + ")");
    }
}
