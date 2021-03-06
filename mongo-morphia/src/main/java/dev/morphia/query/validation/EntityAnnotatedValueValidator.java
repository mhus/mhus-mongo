/**
 * Copyright (C) 2019 Mike Hummel (mh@mhus.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.morphia.query.validation;

import static java.lang.String.format;

import java.util.List;

import dev.morphia.Key;
import dev.morphia.annotations.Entity;

/**
 * Ensures that a Class is annotated with @Entity.
 *
 * @see Entity
 */
public final class EntityAnnotatedValueValidator extends TypeValidator {
    private static final EntityAnnotatedValueValidator INSTANCE =
            new EntityAnnotatedValueValidator();

    private EntityAnnotatedValueValidator() {}

    /**
     * Get the instance.
     *
     * @return the Singleton instance of this validator
     */
    public static EntityAnnotatedValueValidator getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean appliesTo(final Class<?> type) {
        return Key.class.equals(type);
    }

    @Override
    protected void validate(
            final Class<?> type,
            final Object value,
            final List<ValidationFailure> validationFailures) {
        if (value.getClass().getAnnotation(Entity.class) == null) {
            validationFailures.add(
                    new ValidationFailure(
                            format(
                                    "When type is a Key the value should be an annotated entity. "
                                            + "Value '%s' was a %s",
                                    value, value.getClass())));
        }
    }
}
