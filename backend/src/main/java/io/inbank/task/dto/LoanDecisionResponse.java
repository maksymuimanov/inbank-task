package io.inbank.task.dto;

public record LoanDecisionResponse(
        LoanDecisionStatus status,
        int amount
) {
    private static final LoanDecisionResponse DEBT = negative(0);

    public static LoanDecisionResponse debt() {
        return DEBT;
    }

    public static LoanDecisionResponse positive(int amount) {
        return new LoanDecisionResponse(LoanDecisionStatus.POSITIVE, amount);
    }

    public static LoanDecisionResponse negative(int amount) {
        return new LoanDecisionResponse(LoanDecisionStatus.NEGATIVE, amount);
    }
}
