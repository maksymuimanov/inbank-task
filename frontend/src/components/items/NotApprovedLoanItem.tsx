import {Item, ItemContent, ItemDescription, ItemMedia, ItemTitle} from "@/components/ui/item.tsx";
import {FrownIcon} from "lucide-react";

export const NotApprovedLoanItem = ({amount}: {amount: number}) => {
    return (
        <Item className="rounded-sm mb-5 text-red-50 bg-red-500">
            <ItemMedia variant="icon">
                <FrownIcon />
            </ItemMedia>
            <ItemContent>
                <ItemTitle>Not Approved</ItemTitle>
                <ItemDescription className="text-red-100">
                    Loan amount that can be approved: <span className="font-bold">{amount}</span> euro(s)
                </ItemDescription>
            </ItemContent>
        </Item>
    );
};