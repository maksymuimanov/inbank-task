import type {LoanDecisionRequest, LoanDecisionResponse} from "@/types/types.ts";
import axios from 'axios';
import * as React from "react";

export function formRequest(formData: FormData): LoanDecisionRequest {
    const personalCode = formData.get("personalCode") as string;
    const amount = Number.parseInt(formData.get("amount") as string);
    const period = Number.parseInt(formData.get("period") as string);

    return {
        "personalCode": personalCode,
        "amount": amount,
        "period": period
    };
}

export function postRequest(formData: FormData,
                            setResponse : React.Dispatch<React.SetStateAction<LoanDecisionResponse | undefined>>,
                            setLoading : React.Dispatch<React.SetStateAction<boolean>>,
                            setErrorMessage : React.Dispatch<React.SetStateAction<string>>) {
    setLoading(true);
    setErrorMessage("");
    const request = formRequest(formData);
    const url = "http://localhost:8080/api/v1.0/loans/decision";
    axios.post<LoanDecisionResponse>(url, request)
        .catch(error => {
            if (error.message) setErrorMessage(error.message);
            const errorResponse = error.response;
            errorResponse.data.status = "NEGATIVE";
            errorResponse.data.amount = 0;
            return errorResponse;
        })
        .then(response => {
            setResponse(response.data);
            setLoading(false);
        });
}