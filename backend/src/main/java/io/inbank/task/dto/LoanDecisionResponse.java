package io.inbank.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoanDecisionResponse(
        @Schema(description = "Loan decision status", example = "POSITIVE")
        Status status,
        @Schema(description = "Loan amount", example = "2000")
        int amount
) {
    private static final LoanDecisionResponse INVALID = negative(0);

    public static LoanDecisionResponse invalid() {
        return INVALID;
    }

    public static LoanDecisionResponse positive(int amount) {
        return new LoanDecisionResponse(Status.POSITIVE, amount);
    }

    public static LoanDecisionResponse negative(int amount) {
        return new LoanDecisionResponse(Status.NEGATIVE, amount);
    }

    public enum Status {
        POSITIVE,
        NEGATIVE
    }
}
