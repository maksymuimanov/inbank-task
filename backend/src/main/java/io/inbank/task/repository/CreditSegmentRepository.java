package io.inbank.task.repository;

import io.inbank.task.entity.CreditSegment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data repository for credit segment entities.
 * <p>
 * Provides database access operations for managing customer credit segments
 * with personal code as the primary identifier.
 *
 * @see CreditSegment
 */
public interface CreditSegmentRepository extends JpaRepository<CreditSegment, String> {
}
