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

/**
 * REST controller for loan decision operations.
 * <p>
 * Provides endpoints for evaluating loan requests and returning approval decisions
 * based on customer credit segments and business rules.
 *
 * @see io.inbank.task.service.LoanDecisionService
 * @see io.inbank.task.dto.LoanDecisionRequest
 * @see io.inbank.task.dto.LoanDecisionResponse
 * @see io.inbank.task.config.OpenAPIConfig
 * @see io.inbank.task.config.WebConfig
 * @see io.inbank.task.exception.WebExceptionHandler
 */
@RestController
@RequestMapping(path = "/api/{version}/loans", version = "1.0")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Loan Decision", description = "Loan decision management")
@RequiredArgsConstructor
public class LoanDecisionController {
    private final LoanDecisionService loanDecisionService;

    /**
     * Evaluates a loan request and returns an approval decision.
     * <p>
     * Processes the loan decision request by checking the customer's credit segment,
     * calculating credit score, and determining the maximum approvable loan amount.
     *
     * @param request validated loan decision request containing personal code, amount, and period
     * @return loan decision response with approval status and approved amount
     */
    @PostMapping("/decision")
    @Operation(summary = "Decide on a loan", description = "Decide whether to approve or reject a loan")
    @ApiResponse(responseCode = "200", description = "Loan decision made")
    @ApiResponse(responseCode = "400", description = "Invalid loan request", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "404", description = "Credit segment not found", content = @Content(schema = @Schema))
    public ResponseEntity<LoanDecisionResponse> decide(@Valid @RequestBody LoanDecisionRequest request) {
        return ResponseEntity.ok(this.loanDecisionService.decide(request));
    }
}
