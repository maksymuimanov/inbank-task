package io.inbank.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

/**
 * Request DTO for loan decision evaluation.
 * <p>
 * Contains all required information to process a loan decision request,
 * including personal code and loan parameters.
 *
 * @see io.inbank.task.service.LoanDecisionServiceImpl
 * @see Loan
 * @see LoanDecisionResponse
 */
public record LoanDecisionRequest(
        @Schema(description = "Personal code", example = "49002010965")
        @Length(min = PERSONAL_CODE_LENGTH, max = PERSONAL_CODE_LENGTH, message = "Personal code must be 11 digits")
        String personalCode,
        @Schema(description = "Loan amount", example = "2000")
        @Min(value = Loan.MIN_LOAN_AMOUNT, message = "Loan amount must be at least 2000 €")
        @Max(value = Loan.MAX_LOAN_AMOUNT, message = "Loan amount must be at most 10000 €")
        int amount,
        @Schema(description = "Loan period", example = "12")
        @Min(value = Loan.MIN_LOAN_PERIOD, message = "Loan period must be at least 12 months")
        @Max(value = Loan.MAX_LOAN_PERIOD, message = "Loan period must be at most 60 months")
        int period
) {
        /** Required length of personal code in digits. */
        private static final int PERSONAL_CODE_LENGTH = 11;
}
