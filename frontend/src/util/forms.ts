import type {LoanDecisionRequest, LoanDecisionResponse} from "@/type/types.ts";
import axios from 'axios';

export function createRequest(formData: FormData): LoanDecisionRequest {
    const personalCode = formData.get("personalCode") as string;
    const loanAmount = parseInt(formData.get("loanAmount") as string);
    const loanPeriod = parseInt(formData.get("loanPeriod") as string);

    return {
        "personalCode": personalCode,
        "loanAmount": loanAmount,
        "loanPeriod": loanPeriod
    };
}

export async function postRequest(formData: FormData, setResponse : React.Dispatch<React.SetStateAction<LoanDecisionResponse | undefined>>) {
    const request = createRequest(formData);
    const url = "http://localhost:8080/api/v1.0/loans/decision";
    await axios.post<LoanDecisionResponse>(url, request)
        .then(response => {
            setResponse(response.data);
        });
}