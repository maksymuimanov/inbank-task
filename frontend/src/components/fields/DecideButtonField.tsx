import {Field} from "@/components/ui/field.tsx";
import {LoadingButton} from "@/components/buttons/LoadingButton.tsx";
import {DecideButton} from "@/components/buttons/DecideButton.tsx";

export const DecideButtonField = ({loading}: {loading: boolean}) => {
    return (
        <Field>
            {loading ? <LoadingButton /> : <DecideButton/>}
        </Field>
    );
};