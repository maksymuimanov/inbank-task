package io.inbank.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Credit segment not found")
public class CreditSegmentNotFoundException extends RuntimeException {
}
