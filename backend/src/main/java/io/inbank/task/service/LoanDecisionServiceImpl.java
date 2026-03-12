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

@Service
@RequiredArgsConstructor
public class LoanDecisionServiceImpl implements LoanDecisionService {
    private final CreditSegmentRepository creditSegmentRepository;

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

    private LoanDecisionResponse approve(CreditSegment creditSegment, Loan loan) {
        if (creditSegment.isDebt()) return LoanDecisionResponse.debt();
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

    private float calculateCreditScore(int creditModifier, int loanAmount, int loanPeriod) {
        return ((float) creditModifier / loanAmount) * loanPeriod;
    }

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
