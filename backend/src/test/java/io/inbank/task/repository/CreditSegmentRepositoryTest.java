package io.inbank.task.repository;

import io.inbank.task.entity.CreditSegment;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CreditSegmentRepositoryTest {
    @Autowired
    private CreditSegmentRepository creditSegmentRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void findById_shouldReturnCreditSegment_whenCreditSegmentExists() {
        CreditSegment creditSegment = new CreditSegment("00000000000", 100, false);
        entityManager.persist(creditSegment);

        Optional<CreditSegment> creditSegmentOptional = creditSegmentRepository.findById(creditSegment.getPersonalCode());

        assertThat(creditSegmentOptional)
                .isNotEmpty()
                .get()
                .isSameAs(creditSegment);
    }

    @Test
    void findById_shouldReturnEmpty_whenCreditSegmentDoesNotExist() {
        CreditSegment creditSegment = new CreditSegment("00000000001", 100, false);

        Optional<CreditSegment> creditSegmentOptional = creditSegmentRepository.findById(creditSegment.getPersonalCode());

        assertThat(creditSegmentOptional)
                .isEmpty();
    }
}
