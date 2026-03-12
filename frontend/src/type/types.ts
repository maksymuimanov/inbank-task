export type LoanDecisionRequest = {
    "personalCode": string,
    "loanAmount": number,
    "loanPeriod": number
}

export type LoanDecisionResponse = {
    "status": "NEGATIVE" | "POSITIVE",
    "amount": number
}