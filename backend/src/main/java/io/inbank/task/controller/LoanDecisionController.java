package io.inbank.task.controller;

import io.inbank.task.dto.LoanDecisionRequest;
import io.inbank.task.dto.LoanDecisionResponse;
import io.inbank.task.service.LoanDecisionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/{version}/loans", version = "1.0")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class LoanDecisionController {
    private final LoanDecisionService loanDecisionService;

    @PostMapping("/decision")
    public ResponseEntity<LoanDecisionResponse> decide(@Valid @RequestBody LoanDecisionRequest request) {
        return ResponseEntity.ok(this.loanDecisionService.decide(request));
    }
}
