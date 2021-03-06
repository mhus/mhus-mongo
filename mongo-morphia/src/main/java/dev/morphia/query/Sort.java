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
package dev.morphia.query;

/**
 * Used for sorting query results or defining a sort stage in an aggregation pipeline
 *
 * @mongodb.driver.manual reference/operator/aggregation/sort/ $sort
 * @since 1.3
 */
public class Sort {
    private static final String NATURAL = "$natural";

    private final String field;
    private final int order;

    /**
     * Creates a sort on a field with a direction.
     *
     * <ul>
     *   <li>1 to specify ascending order.
     *   <li>-1 to specify descending order.
     * </ul>
     *
     * @param field the field
     * @param order the order
     */
    protected Sort(final String field, final int order) {
        this.field = field;
        this.order = order;
    }

    /**
     * Creates an ascending sort on a field
     *
     * @param field the field
     * @return the Sort instance
     */
    public static Sort ascending(final String field) {
        return new Sort(field, 1);
    }

    /**
     * Creates a descending sort on a field
     *
     * @param field the field
     * @return the Sort instance
     */
    public static Sort descending(final String field) {
        return new Sort(field, -1);
    }

    /**
     * Creates an ascending sort on a field
     *
     * @return the Sort instance
     */
    public static Sort naturalAscending() {
        return new Sort(NATURAL, 1);
    }

    /**
     * Creates a descending natural sort on a field
     *
     * @return the Sort instance
     */
    public static Sort naturalDescending() {
        return new Sort(NATURAL, -1);
    }

    /**
     * Returns the sort order.
     *
     * <ul>
     *   <li>1 for ascending order.
     *   <li>-1 for descending order.
     * </ul>
     *
     * @return the sort order
     */
    public int getOrder() {
        return order;
    }

    /** @return the sort field */
    public String getField() {
        return field;
    }
}
