/**
 * Copyright (c) 2008-2015 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.morphia.mapping.cache;


/**
 * This class stores various statistics on an EntityCache
 */
public class EntityCacheStatistics {
    private int entities;
    private int hits;
    private int misses;

    /**
     * Copies the statistics
     *
     * @return the copy
     */
    public EntityCacheStatistics copy() {
        final EntityCacheStatistics copy = new EntityCacheStatistics();
        copy.entities = entities;
        copy.hits = hits;
        copy.misses = misses;
        return copy;
    }

    /**
     * Increments the entity count
     */
    public void incEntities() {
        entities++;
    }

    /**
     * Increments the hit count
     */
    public void incHits() {
        hits++;
    }

    /**
     * Increments the miss count
     */
    public void incMisses() {
        misses++;
    }

    /**
     * Clears the statistics
     */
    public void reset() {
        entities = 0;
        hits = 0;
        misses = 0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + entities + " entities, " + hits + " hits, " + misses + " misses.";
    }
}
