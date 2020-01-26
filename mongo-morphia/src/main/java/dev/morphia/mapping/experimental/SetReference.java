/**
 * Copyright (c) 2008-2015 MongoDB, Inc.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.morphia.mapping.experimental;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import dev.morphia.Datastore;
import dev.morphia.mapping.MappedClass;

/**
 * @param <T>
 * @morphia.internal
 */
@SuppressWarnings("unchecked")
class SetReference<T> extends CollectionReference<Set<T>> {
    private Set<T> values;

    /** @morphia.internal */
    SetReference(final Datastore datastore, final MappedClass mappedClass, final List ids) {
        super(datastore, mappedClass, ids);
    }

    SetReference(final Set<T> values) {
        this.values = values;
    }

    @Override
    Set<T> getValues() {
        return values;
    }

    public Set<T> get() {
        if (values == null && getIds() != null) {
            values = new LinkedHashSet(find());
        }
        return values;
    }
}
