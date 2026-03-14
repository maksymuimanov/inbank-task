package io.inbank.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response DTO for loan decision results.
 * <p>
 * Contains the loan approval decision status and the approved loan amount.
 * Provides factory methods for creating different types of responses.
 *
 * @see io.inbank.task.service.LoanDecisionServiceImpl
 * @see LoanDecisionRequest
 * @see Loan
 */
public record LoanDecisionResponse(
        @Schema(description = "Loan decision status", example = "POSITIVE")
        Status status,
        @Schema(description = "Loan amount", example = "2000")
        int amount
) {
    /** Pre-defined invalid response for customers with debt. */
    private static final LoanDecisionResponse INVALID = negative(0);

    /**
     * Returns a pre-defined invalid response for customers with debt.
     *
     * @return negative decision response with zero amount
     */
    public static LoanDecisionResponse invalid() {
        return INVALID;
    }

    /**
     * Creates a positive loan decision response.
     *
     * @param amount the approved loan amount
     * @return positive decision response with the specified amount
     */
    public static LoanDecisionResponse positive(int amount) {
        return new LoanDecisionResponse(Status.POSITIVE, amount);
    }

    /**
     * Creates a negative loan decision response.
     *
     * @param amount the maximum loan amount that could be approved (may be zero)
     * @return negative decision response with the specified amount
     */
    public static LoanDecisionResponse negative(int amount) {
        return new LoanDecisionResponse(Status.NEGATIVE, amount);
    }

    /**
     * Enumeration of possible loan decision statuses.
     */
    public enum Status {
        /** Loan is approved and can be granted. */
        POSITIVE,
        /** Loan is rejected and cannot be granted. */
        NEGATIVE
    }
}
