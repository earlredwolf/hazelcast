/*
 * Copyright (c) 2008-2012, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.map;

import com.hazelcast.nio.Data;
import com.hazelcast.spi.PartitionAwareOperation;
import com.hazelcast.spi.impl.AbstractNamedOperation;

import java.util.Set;

public class MapKeySetOperation extends AbstractNamedOperation implements PartitionAwareOperation {
    Set<Data> keySet;

    public MapKeySetOperation(String name) {
        super(name);
    }

    public MapKeySetOperation() {
    }

    public void run() {
        MapService mapService = (MapService) getService();
        DefaultRecordStore recordStore = mapService.getMapPartition(getPartitionId(), name);
        keySet = recordStore.keySet();
    }

    @Override
    public Object getResponse() {
        return keySet;
    }

    @Override
    public boolean returnsResponse() {
        return true;
    }
}