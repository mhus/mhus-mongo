package dev.morphia.mapping.validation;


import java.util.Set;

import dev.morphia.mapping.MappedClass;
import dev.morphia.mapping.Mapper;


/**
 * @author Uwe Schaefer, (us@thomas-daily.de)
 */
public interface ClassConstraint {
    /**
     * Check that a MappedClass meets the constraint
     *
     * @param mc     the MappedClass to check
     * @param ve     the set of violations
     * @param mapper the Mapper to use for validation
     */
    void check(final Mapper mapper, MappedClass mc, Set<ConstraintViolation> ve);
}
