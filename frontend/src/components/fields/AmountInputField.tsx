import {SliderInputField} from "@/components/fields/SliderInputField.tsx";

export const AmountInputField = () => {
    return (
        <SliderInputField
            name="amount"
            label="Loan amount"
            addon="EUR"
            step={100}
            min={2000}
            max={10000}
        />
    );
};