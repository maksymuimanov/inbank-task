package io.inbank.task.controller;

import io.inbank.task.dto.LoanDecisionRequest;
import io.inbank.task.dto.LoanDecisionResponse;
import io.inbank.task.service.LoanDecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/{version}/loans", version = "1.0")
@RequiredArgsConstructor
public class LoanDecisionController {
    private final LoanDecisionService loanDecisionService;

    @PostMapping("/decision")
    public ResponseEntity<LoanDecisionResponse> decide(LoanDecisionRequest request) {
        return ResponseEntity.ok(this.loanDecisionService.decide(request));
    }
}
