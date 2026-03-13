import {Item, ItemContent, ItemDescription, ItemMedia, ItemTitle} from "@/components/ui/item.tsx";
import {FrownIcon, LaughIcon, MehIcon} from "lucide-react";
import type {LoanDecisionResponse} from "@/types/types.ts";
import {Skeleton} from "@/components/ui/skeleton.tsx";

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
    if (loading) {
        return (
            <Item className="rounded-sm mb-5 text-muted-foreground bg-muted">
                <ItemMedia variant="icon">
                    <MehIcon />
                </ItemMedia>
                <ItemContent>
                    <ItemTitle><Skeleton className="bg-secondary h-5 w-37.5" /></ItemTitle>
                    <Skeleton className="bg-secondary h-5 w-75" />
                </ItemContent>
            </Item>
        )
    }
    if (errorMessage.length > 0) return (
        <Item className="rounded-sm mb-5 text-amber-50 bg-amber-500">
            <ItemMedia variant="icon">
                <MehIcon />
            </ItemMedia>
            <ItemContent>
                <ItemTitle>Failed to decide</ItemTitle>
                <ItemDescription className="text-amber-100">
                    {errorMessage}
                </ItemDescription>
            </ItemContent>
        </Item>
    )

    return (
        response && (
            response.status === "POSITIVE"
            ? <Item className="rounded-sm mb-5 text-emerald-50 bg-emerald-500">
                    <ItemMedia variant="icon">
                        <LaughIcon />
                    </ItemMedia>
                    <ItemContent>
                        <ItemTitle>Approved</ItemTitle>
                        <ItemDescription className="text-emerald-100">
                            Loan amount that can be approved: <span className="font-bold">{response.amount}</span> euro(s)
                        </ItemDescription>
                    </ItemContent>
            </Item>
            : <Item className="rounded-sm mb-5 text-red-50 bg-red-500">
                    <ItemMedia variant="icon">
                        <FrownIcon />
                    </ItemMedia>
                    <ItemContent>
                        <ItemTitle>Not Approved</ItemTitle>
                        <ItemDescription className="text-red-100">
                            Loan amount that can be approved: <span className="font-bold">{response.amount}</span> euro(s)
                        </ItemDescription>
                    </ItemContent>
            </Item>
        )
    )
}