interface CalculationFormProps {
  CalculationStep: React.ComponentType;
}

export const CalculationForm: React.FC<CalculationFormProps> = ({
  CalculationStep,
}) => {
  return (
    <div className="background">
      <div className="content-space">
        <div className="calculation-form-section">
          <CalculationStep />
        </div>
        <section className="calculation-steps-section">
          <div>*</div>
          <div>1. okres ubezpieczenia</div>
          <div>-</div>
          <div>*</div>
          <div>2. dane osobowe</div>
          <div>-</div>
          <div>*</div>
          <div>3. uprawy</div>
          <div>-</div>
          <div>*</div>
          <div>4. zwierzęta</div>
          <div>-</div>
          <div>*</div>
          <div>5. oferty</div>
          <div>-</div>
        </section>
      </div>
    </div>
  );
};
