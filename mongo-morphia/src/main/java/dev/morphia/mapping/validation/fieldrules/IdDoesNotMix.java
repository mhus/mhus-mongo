package dev.morphia.mapping.validation.fieldrules;


import java.util.Set;

import de.mhus.lib.annotations.adb.DbPrimaryKey;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Property;
import dev.morphia.annotations.Reference;
import dev.morphia.mapping.MappedClass;
import dev.morphia.mapping.MappedField;
import dev.morphia.mapping.Mapper;
import dev.morphia.mapping.validation.ConstraintViolation;
import dev.morphia.mapping.validation.ConstraintViolation.Level;


/**
 * @author ScottHenandez
 */
public class IdDoesNotMix extends FieldConstraint {

    @Override
    protected void check(final Mapper mapper, final MappedClass mc, final MappedField mf, final Set<ConstraintViolation> ve) {
        // an @Id field can not be a Value, Reference, or Embedded
        if (mf.hasAnnotation(DbPrimaryKey.class)) {
            if (mf.hasAnnotation(Reference.class) || mf.hasAnnotation(Embedded.class) || mf.hasAnnotation(Property.class)) {
                ve.add(new ConstraintViolation(Level.FATAL, mc, mf, getClass(),
                                               mf.getFullName() + " is annotated as @" + DbPrimaryKey.class.getSimpleName()
                                               + " and cannot be mixed with other annotations (like @Reference)"));
            }
        }
    }
}
