/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.internal.changedetection;

import org.gradle.cache.PersistentIndexedCache;
import org.gradle.cache.Serializer;

public interface TaskArtifactStateCacheAccess {
    /**
     * Performs some long running operation. Releases all locks while the operation is running, and reacquires the locks at the end of
     * the long running operation.
     *
     * <p>This method is re-entrant, so that an action can call back into this method.</p>
     */
    void longRunningOperation(String operationDisplayName, Runnable action);

    <K, V> PersistentIndexedCache createCache(String cacheName, Class<K> keyType, Class<V> valueType);

    <K, V> PersistentIndexedCache<K, V> createCache(String cacheName, Class<K> keyType, Class<V> valueType, Serializer<V> valueSerializer);
}
