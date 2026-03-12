package io.inbank.task.service;

import io.inbank.task.dto.LoanDecisionRequest;
import io.inbank.task.dto.LoanDecisionResponse;

public interface LoanDecisionService {
    LoanDecisionResponse decide(LoanDecisionRequest request);
}
