import {Field, FieldGroup, FieldLabel} from "@/components/ui/field.tsx";
import {Input} from "@/components/ui/input.tsx";
import {Button} from "@/components/ui/button.tsx";
import {useState} from "react";
import type {LoanDecisionResponse} from "@/type/types.ts";
import {postRequest} from "@/util/forms.ts";

export const LoanDecisionForm = () => {
    const [response, setResponse] = useState<LoanDecisionResponse>();

    return (
        <form action={(formData) => postRequest(formData, setResponse)}>
            <FieldGroup>
                <Field>
                    <FieldLabel htmlFor="personalCode">Personal Code</FieldLabel>
                    <Input name="personalCode" id="personalCode" type="text" placeholder="49002010965" required/>
                </Field>
                <Field>
                    <div className="flex items-center">
                        <FieldLabel htmlFor="loanAmount">Loan amount</FieldLabel>
                    </div>
                    <Input name="loanAmount" id="loanAmount" type="number" min="2000" max="10000" placeholder="2000" required />
                </Field>
                <Field>
                    <div className="flex items-center">
                        <FieldLabel htmlFor="loanPeriod">Loan period</FieldLabel>
                    </div>
                    <Input name="loanPeriod" id="loanPeriod" type="number" min="12" max="60" placeholder="12" required />
                </Field>
                <Field>
                    <Button type="submit">Decide</Button>
                </Field>
            </FieldGroup>
        </form>
    );
};