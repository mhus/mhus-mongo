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
package dev.morphia.geo;

import com.mongodb.DBObject;

import dev.morphia.converters.SimpleValueConverter;
import dev.morphia.converters.TypeConverter;
import dev.morphia.mapping.MappedField;

/**
 * A Morphia TypeConverter that knows how to turn things that are labelled with the Geometry
 * interface into the correct concrete class, based on the GeoJSON type.
 *
 * <p>Only implements the decode method as the concrete classes can encode themselves without
 * needing a converter. It's when they come out of the database that there's not enough information
 * for Morphia to automatically create Geometry instances.
 */
public class GeometryConverter extends TypeConverter implements SimpleValueConverter {
    /** Sets up this converter to work with things that implement the Geometry interface */
    public GeometryConverter() {
        super(Geometry.class);
    }

    @Override
    public Object decode(
            final Class<?> targetClass,
            final Object fromDBObject,
            final MappedField optionalExtraInfo) {
        DBObject dbObject = (DBObject) fromDBObject;
        String type = (String) dbObject.get("type");
        return getMapper()
                .getConverters()
                .decode(
                        GeoJsonType.fromString(type).getTypeClass(),
                        fromDBObject,
                        optionalExtraInfo);
    }
}
