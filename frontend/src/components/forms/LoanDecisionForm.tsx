import {FieldGroup} from "@/components/ui/field.tsx";
import type {LoanDecisionResponse} from "@/types/types.ts";
import {postRequest} from "@/utils/forms.ts";
import * as React from "react"
import {PersonalCodeInputField} from "@/components/fields/PersonalCodeInputField.tsx";
import {AmountInputField} from "@/components/fields/AmountInputField.tsx";
import {PeriodInputField} from "@/components/fields/PeriodInputField.tsx";
import {DecideButtonField} from "@/components/fields/DecideButtonField.tsx";

export const LoanDecisionForm = (
    {
        setResponse,
        loading,
        setLoading,
        setErrorMessage
    }: {
        setResponse: React.Dispatch<React.SetStateAction<LoanDecisionResponse | undefined>>,
        loading: boolean,
        setLoading: React.Dispatch<React.SetStateAction<boolean>>,
        setErrorMessage: React.Dispatch<React.SetStateAction<string>>
    }
) => {
    return (
        <form onSubmit={(e) => {
            e.preventDefault()
            const formData = new FormData(e.currentTarget)
            postRequest(formData, setResponse, setLoading, setErrorMessage)
        }}>
            <FieldGroup>
                <PersonalCodeInputField />
                <AmountInputField />
                <PeriodInputField />
                <DecideButtonField loading={loading} />
            </FieldGroup>
        </form>
    );
};