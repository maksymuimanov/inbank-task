import {Card, CardContent, CardDescription, CardHeader, CardTitle,} from "@/components/ui/card.tsx"
import {LoanDecisionForm} from "@/components/form/LoanDecisionForm.tsx";

export const LoanDecisionCard = () => {
    return (
        <div className="flex flex-col gap-6">
            <Card>
                <CardHeader>
                    <CardTitle>Loan decision engine</CardTitle>
                    <CardDescription>
                        Enter your personal code, loan amount period below to decide whether to approve the loan
                    </CardDescription>
                </CardHeader>
                <CardContent>
                    <LoanDecisionForm />
                </CardContent>
            </Card>
        </div>
    )
};