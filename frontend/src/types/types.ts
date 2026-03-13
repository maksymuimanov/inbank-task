export type LoanDecisionRequest = {
    "personalCode": string,
    "amount": number,
    "period": number
}

export type LoanDecisionResponse = {
    "status": "NEGATIVE" | "POSITIVE",
    "amount": number
}