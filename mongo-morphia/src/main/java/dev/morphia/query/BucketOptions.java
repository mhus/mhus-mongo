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

import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import dev.morphia.aggregation.Accumulator;

/**
 * The options for a bucket stage of aggregation pipeline.
 *
 * @author Roman Lapin
 */
public class BucketOptions {

    private Object defaultField;
    private Map<String, Accumulator> accumulators = new HashMap<String, Accumulator>();

    /**
     * Converts a BucketOptions to a DBObject for use by the Java driver.
     *
     * @return the DBObject
     */
    public DBObject toDBObject() {
        DBObject dbObject = new BasicDBObject();
        if (defaultField != null) {
            dbObject.put("default", defaultField);
        }

        DBObject output = new BasicDBObject();
        for (Map.Entry<String, Accumulator> entry : accumulators.entrySet()) {
            output.put(entry.getKey(), entry.getValue().toDBObject());
        }
        if (!accumulators.isEmpty()) {
            dbObject.put("output", output);
        }

        return dbObject;
    }

    /**
     * Define default field for the bucket stage
     *
     * @param defaultField name of the field
     * @return this
     */
    public BucketOptions defaultField(final Object defaultField) {
        this.defaultField = defaultField;
        return this;
    }

    /**
     * Define output field for the bucket stage
     *
     * @param fieldName name of the output field
     * @return this
     */
    public OutputOperation output(final String fieldName) {

        return new OutputOperation(fieldName);
    }

    /**
     * Defines an output for bucketauto stage, that consists of the fieldname and the accumulator
     */
    public class OutputOperation {

        private String fieldName;

        /**
         * Creates the output operation for given fieldname
         *
         * @param fieldName name of the output field
         */
        public OutputOperation(final String fieldName) {
            this.fieldName = fieldName;
        }

        /**
         * Returns an array of all unique values that results from applying an expression to each
         * document in a group of documents that share the same group by key. Order of the elements
         * in the output array is unspecified.
         *
         * @param field the field to process
         * @return an Accumulator
         * @mongodb.driver.manual reference/operator/aggregation/addToSet $addToSet
         */
        public BucketOptions addToSet(final String field) {
            accumulators.put(fieldName, new Accumulator("$addToSet", field));
            return BucketOptions.this;
        }

        /**
         * Returns the average value of the numeric values that result from applying a specified
         * expression to each document in a group of documents that share the same group by key.
         * $avg ignores non-numeric values.
         *
         * @param field the field to process
         * @return an Accumulator
         * @mongodb.driver.manual reference/operator/aggregation/avg $avg
         */
        public BucketOptions average(final String field) {

            accumulators.put(fieldName, new Accumulator("$avg", field));
            return BucketOptions.this;
        }

        /**
         * Calculates and returns the sum of all the numeric values that result from applying a
         * specified expression to each document in a group of documents that share the same group
         * by key. $sum ignores non-numeric values.
         *
         * @param field the field to process
         * @return an Accumulator
         * @mongodb.driver.manual reference/operator/aggregation/sum $sum
         */
        public BucketOptions sum(final Object field) {
            accumulators.put(fieldName, new Accumulator("$sum", field));
            return BucketOptions.this;
        }
    }

    /** @return default bucket name */
    public Object getDefaultField() {
        return defaultField;
    }

    /** @return output accumulators per output field */
    public Map<String, Accumulator> getAccumulators() {
        return accumulators;
    }
}
