/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.cave.server.maven;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A {@link ThreadFactory} which sets the thread name to an unique name.
 *
 * <p>The thread name uses the following syntax <tt>name #counter</tt>, where counter in an unique counter, starting from 1.</p>
 */
public final class ThreadFactory implements java.util.concurrent.ThreadFactory {

    private static final AtomicInteger counter = new AtomicInteger();

    private final String name;

    /**
     * Prefix of the thread name
     */
    public ThreadFactory(final String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, name + " #" + counter.incrementAndGet());
    }
}
