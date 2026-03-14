package io.inbank.task.dto;

/**
 * Represents a loan DTO with amount and period.
 * <p>
 * This record encapsulates the basic loan parameters used in
 * loan decision calculations and validation.
 *
 * @see io.inbank.task.service.LoanDecisionServiceImpl
 * @see LoanDecisionRequest
 * @see LoanDecisionResponse
 */
public record Loan(
        int amount,
        int period
) {
        /** Minimum allowed loan amount in euros. */
        public static final int MIN_LOAN_AMOUNT = 2000;
        /** Maximum allowed loan amount in euros. */
        public static final int MAX_LOAN_AMOUNT = 10000;
        /** Minimum allowed loan period in months. */
        public static final int MIN_LOAN_PERIOD = 12;
        /** Maximum allowed loan period in months. */
        public static final int MAX_LOAN_PERIOD = 60;
}
