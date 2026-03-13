import {Item, ItemContent, ItemMedia, ItemTitle} from "@/components/ui/item.tsx";
import {MehIcon} from "lucide-react";
import {Skeleton} from "@/components/ui/skeleton.tsx";

export const SkeletonItem = () => {
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
    );
};