import {Item, ItemContent, ItemDescription, ItemMedia, ItemTitle} from "@/components/ui/item.tsx";
import {MehIcon} from "lucide-react";

export const ErrorItem = ({errorMessage}: {errorMessage: string}) => {
    return (
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
    );
};