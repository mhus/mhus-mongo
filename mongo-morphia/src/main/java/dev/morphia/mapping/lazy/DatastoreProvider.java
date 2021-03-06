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
package dev.morphia.mapping.lazy;

import java.io.Serializable;

import dev.morphia.Datastore;

/**
 * Lightweight object to be created (hopefully by a factory some day) to create provide a
 * Datastore-reference to a resolving Proxy. If this was created by a common Object factory, it
 * could make use of the current context (like Guice Scopes etc.)
 *
 * @author uwe schaefer
 * @see LazyProxyFactory DatastoreProviders are no longer necessary
 */
public interface DatastoreProvider extends Serializable {
    /** @return the Datastore */
    Datastore get();

    /**
     * Registers a Datastore with this provider
     *
     * @param ds the Datastore
     */
    void register(Datastore ds);
}
