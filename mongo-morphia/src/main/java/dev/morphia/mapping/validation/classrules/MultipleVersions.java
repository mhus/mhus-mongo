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
package dev.morphia.mapping.validation.classrules;

import java.util.List;
import java.util.Set;

import dev.morphia.annotations.Version;
import dev.morphia.mapping.MappedClass;
import dev.morphia.mapping.MappedField;
import dev.morphia.mapping.Mapper;
import dev.morphia.mapping.validation.ClassConstraint;
import dev.morphia.mapping.validation.ConstraintViolation;
import dev.morphia.mapping.validation.ConstraintViolation.Level;

/** @author Uwe Schaefer, (us@thomas-daily.de) */
public class MultipleVersions implements ClassConstraint {

    @Override
    public void check(
            final Mapper mapper, final MappedClass mc, final Set<ConstraintViolation> ve) {
        final List<MappedField> versionFields = mc.getFieldsAnnotatedWith(Version.class);
        if (versionFields.size() > 1) {
            ve.add(
                    new ConstraintViolation(
                            Level.FATAL,
                            mc,
                            getClass(),
                            "Multiple @"
                                    + Version.class
                                    + " annotations are not allowed. ("
                                    + new FieldEnumString(versionFields)));
        }
    }
}
