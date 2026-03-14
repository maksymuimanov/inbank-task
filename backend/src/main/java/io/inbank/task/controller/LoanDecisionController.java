package io.inbank.task.controller;

import io.inbank.task.dto.LoanDecisionRequest;
import io.inbank.task.dto.LoanDecisionResponse;
import io.inbank.task.service.LoanDecisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/{version}/loans", version = "1.0")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Loan Decision", description = "Loan decision management")
@RequiredArgsConstructor
public class LoanDecisionController {
    private final LoanDecisionService loanDecisionService;

    @PostMapping("/decision")
    @Operation(summary = "Decide on a loan", description = "Decide whether to approve or reject a loan")
    @ApiResponse(responseCode = "200", description = "Loan decision made")
    @ApiResponse(responseCode = "400", description = "Invalid loan request", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "404", description = "Credit segment not found", content = @Content(schema = @Schema))
    public ResponseEntity<LoanDecisionResponse> decide(@Valid @RequestBody LoanDecisionRequest request) {
        return ResponseEntity.ok(this.loanDecisionService.decide(request));
    }
}
