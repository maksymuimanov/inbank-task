package io.inbank.task.service;

import io.inbank.task.dto.Loan;
import io.inbank.task.dto.LoanDecisionRequest;
import io.inbank.task.dto.LoanDecisionResponse;
import io.inbank.task.entity.CreditSegment;
import io.inbank.task.exception.CreditSegmentNotFoundException;
import io.inbank.task.repository.CreditSegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for loan decision operations.
 * <p>
 * Implements the loan decision engine that evaluates requests based on customer
 * credit segments, applies business rules, and determines the maximum approvable loan amount.
 *
 * @see LoanDecisionService
 * @see LoanDecisionRequest
 * @see LoanDecisionResponse
 * @see Loan
 * @see CreditSegment
 * @see CreditSegmentRepository
 * @see CreditSegmentNotFoundException
 */
@Service
@RequiredArgsConstructor
public class LoanDecisionServiceImpl implements LoanDecisionService {
    /** Repository for accessing customer credit segment data. */
    private final CreditSegmentRepository creditSegmentRepository;

    /**
     * Evaluates a loan request and returns an approval decision.
     * <p>
     * Retrieves the customer's credit segment, checks for debt status,
     * calculates credit score, and determines the maximum approvable loan amount.
     *
     * @param request the loan decision request containing customer and loan details
     * @return loan decision response with approval status and approved amount
     * @throws CreditSegmentNotFoundException if no credit segment exists for the personal code
     */
    @Override
    @Transactional(readOnly = true)
    public LoanDecisionResponse decide(LoanDecisionRequest request) {
        String personalCode = request.personalCode();
        CreditSegment creditSegment = this.creditSegmentRepository.findById(personalCode)
                .orElseThrow(CreditSegmentNotFoundException::new);
        int loanAmount = request.amount();
        int loanPeriod = request.period();
        Loan loan = new Loan(loanAmount, loanPeriod);
        return this.approve(creditSegment, loan);
    }

    /**
     * Approves or rejects a loan based on credit segment and loan parameters.
     * <p>
     * Checks for debt status, calculates credit score, and determines the maximum
     * approvable loan amount based on business rules.
     *
     * @param creditSegment the customer's credit segment information
     * @param loan the loan request containing amount and period
     * @return loan decision response with approval status and approved amount
     */
    private LoanDecisionResponse approve(CreditSegment creditSegment, Loan loan) {
        if (creditSegment.isDebt()) return LoanDecisionResponse.invalid();
        int creditModifier = creditSegment.getCreditModifier();
        int loanAmount = loan.amount();
        int loanPeriod = loan.period();
        float creditScore = this.calculateCreditScore(creditModifier, loanAmount, loanPeriod);
        boolean approved = creditScore >= 1;
        int amount = this.calculateAmount(creditModifier, loanPeriod);
        return approved
                ? LoanDecisionResponse.positive(amount)
                : LoanDecisionResponse.negative(amount);
    }

    /**
     * Calculates the credit score for a loan request.
     * <p>
     * The score determines whether a loan can be approved based on
     * the customer's credit modifier, requested amount, and loan period.
     *
     * @param creditModifier modifier assigned to the customer's segment
     * @param loanAmount requested loan amount
     * @param loanPeriod loan duration in months
     * @return calculated credit score used for approval decision
     */
    private float calculateCreditScore(int creditModifier, int loanAmount, int loanPeriod) {
        return ((float) creditModifier / loanAmount) * loanPeriod;
    }

    /**
     * Calculates the maximum approvable loan amount for given credit modifier and period.
     * <p>
     * Uses recursive approach to adjust loan period when the calculated amount
     * exceeds limits, ensuring the result falls within allowed bounds.
     *
     * @param creditModifier the customer's credit modifier value
     * @param loanPeriod the loan period in months
     * @return maximum approvable loan amount within business constraints
     */
    private int calculateAmount(int creditModifier, int loanPeriod) {
        int amount = creditModifier * loanPeriod;
        if (amount > Loan.MAX_LOAN_AMOUNT && loanPeriod > Loan.MIN_LOAN_PERIOD) {
            int newLoanPeriod = loanPeriod - 1;
            return this.calculateAmount(creditModifier, newLoanPeriod);
        } else if (amount < Loan.MIN_LOAN_AMOUNT && loanPeriod < Loan.MAX_LOAN_PERIOD) {
            int newLoanPeriod = loanPeriod + 1;
            return this.calculateAmount(creditModifier, newLoanPeriod);
        }

        return amount;
    }
}
