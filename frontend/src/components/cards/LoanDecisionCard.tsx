import {Card, CardContent, CardDescription, CardHeader, CardTitle,} from "@/components/ui/card.tsx"
import {LoanDecisionForm} from "@/components/forms/LoanDecisionForm.tsx";
import {useState} from "react";
import type {LoanDecisionResponse} from "@/types/types.ts";
import {LoanDecisionItem} from "@/components/items/LoanDecisionItem.tsx";

export const LoanDecisionCard = () => {
    const [response, setResponse] = useState<LoanDecisionResponse>();
    const [loading, setLoading] = useState<boolean>(false);
    const [errorMessage, setErrorMessage] = useState<string>("");
    return (
        <div className="flex flex-col gap-6">
            <Card>
                <CardHeader>
                    <CardTitle className="text-xl italic">Loan decision engine</CardTitle>
                    <CardDescription>
                        Enter your personal code, loan amount and period below to decide whether to approve the loan
                    </CardDescription>
                </CardHeader>
                <CardContent>
                    <LoanDecisionItem response={response} loading={loading} errorMessage={errorMessage} />
                    <LoanDecisionForm setResponse={setResponse} loading={loading} setLoading={setLoading} setErrorMessage={setErrorMessage} />
                </CardContent>
            </Card>
        </div>
    )
};