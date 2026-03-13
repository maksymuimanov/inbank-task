import {LoanDecisionCard} from "@/components/cards/LoanDecisionCard.tsx";

export const Main = () => {
    return (
        <main className="flex min-h-svh w-full items-center justify-center p-6 md:p-10">
            <div className="w-full max-w-xl">
                <LoanDecisionCard />
            </div>
        </main>
    )
}