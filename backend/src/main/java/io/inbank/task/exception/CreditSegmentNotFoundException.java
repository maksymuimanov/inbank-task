package io.inbank.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a credit segment is not found for a given personal code.
 * <p>
 * This exception is mapped to HTTP 404 Not Found status automatically.
 *
 * @see io.inbank.task.service.LoanDecisionServiceImpl
 * @see io.inbank.task.repository.CreditSegmentRepository
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Credit segment not found")
public class CreditSegmentNotFoundException extends RuntimeException {
}
