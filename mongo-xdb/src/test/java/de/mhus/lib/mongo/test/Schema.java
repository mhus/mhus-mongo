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
package de.mhus.lib.mongo.test;

import java.util.List;

import de.mhus.lib.adb.Persistable;
import de.mhus.lib.mongo.MoSchema;

public class Schema extends MoSchema {

    @Override
    public void findObjectTypes(List<Class<? extends Persistable>> list) {
        list.add(Employee.class);
        list.add(MoMetadata.class);
    }

    @Override
    public String getDatabaseName() {
        return "test";
    }
}
