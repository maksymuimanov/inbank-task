package io.inbank.task.dto;

public record Loan(
        int amount,
        int period
) {
        public static final int MIN_LOAN_AMOUNT = 2000;
        public static final int MAX_LOAN_AMOUNT = 10000;
        public static final int MIN_LOAN_PERIOD = 12;
        public static final int MAX_LOAN_PERIOD = 60;
}
