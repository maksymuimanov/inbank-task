package io.inbank.task.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public record LoanDecisionRequest(
        @Length(min = PERSONAL_CODE_LENGTH, max = PERSONAL_CODE_LENGTH, message = "Personal code must be 11 digits")
        String personalCode,
        @Min(value = Loan.MIN_LOAN_AMOUNT, message = "Loan amount must be at least 2000 €")
        @Max(value = Loan.MAX_LOAN_AMOUNT, message = "Loan amount must be at most 10000 €")
        int amount,
        @Min(value = Loan.MIN_LOAN_PERIOD, message = "Loan period must be at least 12 months")
        @Max(value = Loan.MAX_LOAN_PERIOD, message = "Loan period must be at most 60 months")
        int period
) {
        private static final int PERSONAL_CODE_LENGTH = 11;
}
