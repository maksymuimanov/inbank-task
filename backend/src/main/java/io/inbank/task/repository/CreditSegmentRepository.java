package io.inbank.task.repository;

import io.inbank.task.entity.CreditSegment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditSegmentRepository extends JpaRepository<CreditSegment, String> {
}
