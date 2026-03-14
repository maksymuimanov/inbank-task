package io.inbank.task.service;

import io.inbank.task.dto.LoanDecisionRequest;
import io.inbank.task.dto.LoanDecisionResponse;
import io.inbank.task.entity.CreditSegment;
import io.inbank.task.exception.CreditSegmentNotFoundException;
import io.inbank.task.repository.CreditSegmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanDecisionServiceTest {
    @InjectMocks
    private LoanDecisionServiceImpl loanDecisionService;
    @Mock
    private CreditSegmentRepository creditSegmentRepository;
    private LoanDecisionRequest request;

    @Test
    void decide_shouldReturnStatusPositiveAndGreaterAmount_whenCreditScoreIsGreaterThanOne() {
        request = new LoanDecisionRequest("00000000000", 2000, 12);

        CreditSegment creditSegment = new CreditSegment(request.personalCode(), 500, false);
        when(creditSegmentRepository.findById(request.personalCode()))
                .thenReturn(Optional.of(creditSegment));

        LoanDecisionResponse response = loanDecisionService.decide(request);

        assertThat(response.status())
                .isSameAs(LoanDecisionResponse.Status.POSITIVE);
        assertThat(response.amount())
                .isGreaterThan(request.amount())
                .isEqualTo(request.period() * creditSegment.getCreditModifier());
    }

    @Test
    void decide_shouldReturnStatusPositiveAndEqualAmount_whenCreditScoreIsEqualsOne() {
        request = new LoanDecisionRequest("00000000000", 2000, 20);

        CreditSegment creditSegment = new CreditSegment(request.personalCode(), 100, false);
        when(creditSegmentRepository.findById(request.personalCode()))
                .thenReturn(Optional.of(creditSegment));

        LoanDecisionResponse response = loanDecisionService.decide(request);

        assertThat(response.status())
                .isSameAs(LoanDecisionResponse.Status.POSITIVE);
        assertThat(response.amount())
                .isEqualTo(request.amount())
                .isEqualTo(request.period() * creditSegment.getCreditModifier());
    }

    @Test
    void decide_shouldReturnStatusNegativeAndLessAmount_whenCreditScoreIsLessThanOne() {
        request = new LoanDecisionRequest("00000000000", 2001, 20);

        CreditSegment creditSegment = new CreditSegment(request.personalCode(), 100, false);
        when(creditSegmentRepository.findById(request.personalCode()))
                .thenReturn(Optional.of(creditSegment));

        LoanDecisionResponse response = loanDecisionService.decide(request);

        assertThat(response.status())
                .isSameAs(LoanDecisionResponse.Status.NEGATIVE);
        assertThat(response.amount())
                .isLessThan(request.amount())
                .isEqualTo(request.period() * creditSegment.getCreditModifier());
    }

    @Test
    void decide_shouldReturnStatusNegativeAndZeroAmount_whenDebt() {
        request = new LoanDecisionRequest("00000000000", 2000, 20);

        CreditSegment creditSegment = new CreditSegment(request.personalCode(), 100, true);
        when(creditSegmentRepository.findById(request.personalCode()))
                .thenReturn(Optional.of(creditSegment));

        LoanDecisionResponse response = loanDecisionService.decide(request);

        assertThat(response.status())
                .isSameAs(LoanDecisionResponse.Status.NEGATIVE);
        assertThat(response.amount())
                .isLessThan(request.amount())
                .isEqualTo(0);
    }

    @Test
    void decide_shouldReturnStatusPositiveAndGreaterAmountWithPeriodSubstraction_whenCreditScoreIsEqualsOneAndAmountGreaterThanMaxAmount() {
        request = new LoanDecisionRequest("00000000000", 2000, 51);

        CreditSegment creditSegment = new CreditSegment(request.personalCode(), 200, false);
        when(creditSegmentRepository.findById(request.personalCode()))
                .thenReturn(Optional.of(creditSegment));

        LoanDecisionResponse response = loanDecisionService.decide(request);

        assertThat(response.status())
                .isSameAs(LoanDecisionResponse.Status.POSITIVE);
        assertThat(response.amount())
                .isNotEqualTo(request.amount())
                .isNotEqualTo(request.period() * creditSegment.getCreditModifier())
                .isEqualTo((request.period() - 1) * creditSegment.getCreditModifier());
    }

    @Test
    void decide_shouldReturnStatusNegativeAndLessAmountWithPeriodAddition_whenCreditScoreIsLessThanOneAndAmountLessThanMinAmount() {
        request = new LoanDecisionRequest("00000000000", 2200, 39);

        CreditSegment creditSegment = new CreditSegment(request.personalCode(), 50, false);
        when(creditSegmentRepository.findById(request.personalCode()))
                .thenReturn(Optional.of(creditSegment));

        LoanDecisionResponse response = loanDecisionService.decide(request);

        assertThat(response.status())
                .isSameAs(LoanDecisionResponse.Status.NEGATIVE);
        assertThat(response.amount())
                .isNotEqualTo(request.amount())
                .isNotEqualTo(request.period() * creditSegment.getCreditModifier())
                .isEqualTo((request.period() + 1) * creditSegment.getCreditModifier());
    }

    @Test
    void decide_shouldThrowCreditSegmentNotFoundException_whenCreditSegmentIsNotFound() {
        request = new LoanDecisionRequest("00000000000", 2000, 20);

        when(creditSegmentRepository.findById(request.personalCode()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> loanDecisionService.decide(request))
                .isInstanceOf(CreditSegmentNotFoundException.class);
    }
}
