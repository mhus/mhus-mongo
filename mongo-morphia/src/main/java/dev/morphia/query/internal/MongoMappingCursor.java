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
package dev.morphia.query.internal;

import static com.mongodb.assertions.Assertions.notNull;

import com.mongodb.Function;
import com.mongodb.ServerAddress;
import com.mongodb.ServerCursor;
import com.mongodb.client.MongoCursor;
import com.mongodb.lang.Nullable;

/**
 * Copied from the Java driver
 *
 * @morphia.internal
 * @param <T>
 * @param <U>
 */
class MongoMappingCursor<T, U> implements MongoCursor<U> {
    private final MongoCursor<T> proxied;
    private final Function<T, U> mapper;

    MongoMappingCursor(final MongoCursor<T> proxied, final Function<T, U> mapper) {
        this.proxied = notNull("proxied", proxied);
        this.mapper = notNull("mapper", mapper);
    }

    @Override
    public void close() {
        proxied.close();
    }

    @Override
    public boolean hasNext() {
        return proxied.hasNext();
    }

    @Override
    public U next() {
        return mapper.apply(proxied.next());
    }

    @Nullable
    @Override
    public U tryNext() {
        T proxiedNext = proxied.tryNext();
        if (proxiedNext == null) {
            return null;
        } else {
            return mapper.apply(proxiedNext);
        }
    }

    @Override
    public void remove() {
        proxied.remove();
    }

    @Nullable
    @Override
    public ServerCursor getServerCursor() {
        return proxied.getServerCursor();
    }

    @Override
    public ServerAddress getServerAddress() {
        return proxied.getServerAddress();
    }
}
