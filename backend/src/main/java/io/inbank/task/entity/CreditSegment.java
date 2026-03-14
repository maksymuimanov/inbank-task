package io.inbank.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Entity representing a customer's credit segment.
 * <p>
 * Contains customer identification, credit modifier for loan calculations,
 * and debt status that affects loan approval decisions.
 *
 * @see io.inbank.task.repository.CreditSegmentRepository
 */
@Entity
@Table(name = "credit_segments")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CreditSegment {
    /** Personal code serving as the primary identifier. */
    @Id
    private String personalCode;
    /** Credit modifier used in loan decision calculations. */
    private int creditModifier;
    /** Flag indicating if the customer has outstanding debt. */
    private boolean debt;

    /**
     * Compares this credit segment with another object for equality.
     * <p>
     * Two credit segments are considered equal if they have the same personal code.
     * Handles Hibernate proxy objects correctly.
     *
     * @param object the object to compare with
     * @return true if the objects represent the same credit segment, false otherwise
     */
    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CreditSegment that = (CreditSegment) object;
        return Objects.equals(getPersonalCode(), that.getPersonalCode());
    }

    /**
     * Generates a hash code for this credit segment.
     * <p>
     * Uses the effective class to handle Hibernate proxy objects correctly.
     *
     * @return hash code value for this object
     */
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
