package io.inbank.task.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LoanDecisionRequest(
        @NotBlank(message = "Personal code is required")
        String personalCode,
        @Min(value = Loan.MIN_LOAN_AMOUNT, message = "Loan amount must be at least 2000 €")
        @Max(value = Loan.MAX_LOAN_AMOUNT, message = "Loan amount must be at most 10000 €")
        int amount,
        @Min(value = Loan.MIN_LOAN_PERIOD, message = "Loan period must be at least 12 months")
        @Max(value = Loan.MAX_LOAN_PERIOD, message = "Loan period must be at most 60 months")
        int period
) {
}
