import {Button} from "@/components/ui/button.tsx";
import {Leapfrog} from "ldrs/react";
import 'ldrs/react/Leapfrog.css'

export const LoadingButton = () => {
    return (
        <Button className="h-16" disabled>
            <Leapfrog size="40" speed="2.5" color="var(--primary-foreground)"/>
        </Button>
    );
};