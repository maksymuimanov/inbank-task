import {Item, ItemContent, ItemDescription, ItemMedia, ItemTitle} from "@/components/ui/item.tsx";
import {LaughIcon} from "lucide-react";

export const ApprovedLoanItem = ({amount}: {amount: number}) => {
    return (
        <Item className="rounded-sm mb-5 text-emerald-50 bg-emerald-500">
            <ItemMedia variant="icon">
                <LaughIcon />
            </ItemMedia>
            <ItemContent>
                <ItemTitle>Approved</ItemTitle>
                <ItemDescription className="text-emerald-100">
                    Loan amount that can be approved: <span className="font-bold">{amount}</span> euro(s)
                </ItemDescription>
            </ItemContent>
        </Item>
    );
};