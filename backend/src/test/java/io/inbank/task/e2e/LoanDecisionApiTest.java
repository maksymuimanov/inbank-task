package io.inbank.task.e2e;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class LoanDecisionApiTest {
    private static final String LOAN_DECISION_POSITIVE_REQUEST_JSON = """
            {
                "personalCode": "49002010976",
                "amount": 2000,
                "period": 20
            }
            """;
    private static final String LOAN_DECISION_NEGATIVE_REQUEST_JSON = """
            {
                "personalCode": "49002010976",
                "amount": 2000,
                "period": 12
            }
            """;
    private static final String LOAN_DECISION_DEBT_REQUEST_JSON = """
            {
                "personalCode": "49002010965",
                "amount": 2000,
                "period": 12
            }
            """;
    private static final String LOAN_DECISION_BAD_PERSONAL_CODE_REQUEST_JSON = """
            {
                "personalCode": "000000000001111111111111111",
                "amount": 2000,
                "period": 12
            }
            """;
    private static final String LOAN_DECISION_BAD_AMOUNT_REQUEST_JSON = """
            {
                "personalCode": "00000000000",
                "amount": 100000,
                "period": 12
            }
            """;
    private static final String LOAN_DECISION_BAD_PERIOD_REQUEST_JSON = """
            {
                "personalCode": "00000000000",
                "amount": 2000,
                "period": 10
            }
            """;
    private static final String LOAN_DECISION_NOT_FOUND_REQUEST_JSON = """
            {
                "personalCode": "00000000000",
                "amount": 2000,
                "period": 12
            }
            """;
    private RestTestClient testClient;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        testClient = RestTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void shouldReturnPositiveDecisionAndOkStatus_whenValidLoanDecisionRequestAndCreditScoreIsGreaterThanOrEqualToOne() {
        testClient.post()
                .uri("/api/v1.0/loans/decision")
                .contentType(MediaType.APPLICATION_JSON)
                .body(LOAN_DECISION_POSITIVE_REQUEST_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.status")
                .isEqualTo("POSITIVE");
    }

    @Test
    void shouldReturnNegativeDecisionAndOkStatus_whenValidLoanDecisionRequestAndCreditScoreIsLessThanOne() {
        testClient.post()
                .uri("/api/v1.0/loans/decision")
                .contentType(MediaType.APPLICATION_JSON)
                .body(LOAN_DECISION_NEGATIVE_REQUEST_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.status")
                .isEqualTo("NEGATIVE");
    }

    @Test
    void shouldReturnNegativeDecisionAndOkStatus_whenValidLoanDecisionRequestAndDebt() {
        testClient.post()
                .uri("/api/v1.0/loans/decision")
                .contentType(MediaType.APPLICATION_JSON)
                .body(LOAN_DECISION_DEBT_REQUEST_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.status")
                .isEqualTo("NEGATIVE");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            LOAN_DECISION_BAD_PERSONAL_CODE_REQUEST_JSON,
            LOAN_DECISION_BAD_AMOUNT_REQUEST_JSON,
            LOAN_DECISION_BAD_PERIOD_REQUEST_JSON
    })
    void shouldReturnBadRequestStatus_whenInvalidLoanDecisionRequest(String requestJson) {
        testClient.post()
                .uri("/api/v1.0/loans/decision")
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestJson)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
    
    @Test
    void shouldReturnNotFoundStatus_whenCreditSegmentNotExists() {
        testClient.post()
                .uri("/api/v1.0/loans/decision")
                .contentType(MediaType.APPLICATION_JSON)
                .body(LOAN_DECISION_NOT_FOUND_REQUEST_JSON)
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
