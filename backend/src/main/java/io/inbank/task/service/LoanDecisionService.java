package io.inbank.task.service;

import io.inbank.task.dto.LoanDecisionRequest;
import io.inbank.task.dto.LoanDecisionResponse;

/**
 * Service interface for loan decision operations.
 * <p>
 * Defines the contract for evaluating loan requests and determining
 * approval decisions based on business rules and customer credit segments.
 *
 * @see LoanDecisionServiceImpl
 * @see LoanDecisionRequest
 * @see LoanDecisionResponse
 */
public interface LoanDecisionService {
    /**
     * Evaluates a loan request and returns an approval decision.
     * <p>
     * Processes the loan decision by checking the customer's credit segment,
     * applying business rules, and calculating the maximum approvable loan amount.
     *
     * @param request the loan decision request containing customer and loan details
     * @return loan decision response with approval status and approved amount
     */
    LoanDecisionResponse decide(LoanDecisionRequest request);
}
