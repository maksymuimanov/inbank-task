import type {LoanDecisionResponse} from "@/types/types.ts";
import {SkeletonItem} from "@/components/items/SkeletonItem.tsx";
import {ErrorItem} from "@/components/items/ErrorItem.tsx";
import {ApprovedLoanItem} from "@/components/items/ApprovedLoanItem.tsx";
import {NotApprovedLoanItem} from "@/components/items/NotApprovedLoanItem.tsx";

export const LoanDecisionItem = (
    {
        response,
        loading,
        errorMessage
    }: {
        response: LoanDecisionResponse | undefined,
        loading: boolean,
        errorMessage: string
    }) => {
    if (loading) return (
        <SkeletonItem/>
    );
    if (errorMessage.length > 0) return (
        <ErrorItem errorMessage={errorMessage} />
    );
    return (
        response && (response.status === "POSITIVE"
            ? <ApprovedLoanItem amount={response.amount} />
            : <NotApprovedLoanItem amount={response.amount} />)
    )
}