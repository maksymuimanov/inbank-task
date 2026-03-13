import {SliderInputField} from "@/components/fields/SliderInputField.tsx";

export const PeriodInputField = () => {
    return (
        <SliderInputField
            name="period"
            label="Loan period"
            addon="MON"
            step={1}
            min={12}
            max={60}
        />
    );
};