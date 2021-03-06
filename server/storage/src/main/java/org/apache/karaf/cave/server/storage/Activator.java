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
package org.apache.karaf.cave.server.storage;

import java.io.File;

import org.apache.karaf.cave.server.api.CaveFeatureGateway;
import org.apache.karaf.cave.server.api.CaveRepositoryService;
import org.apache.karaf.util.tracker.BaseActivator;
import org.apache.karaf.util.tracker.annotation.Managed;
import org.apache.karaf.util.tracker.annotation.ProvideService;
import org.apache.karaf.util.tracker.annotation.Services;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.ManagedService;

@Services(
        provides = {
                @ProvideService(CaveRepositoryService.class),
                @ProvideService(CaveFeatureGateway.class)
        }
)
@Managed("org.apache.karaf.cave.server.storage")
public class Activator extends BaseActivator implements ManagedService {

    @Override
    protected void doStart() throws Exception {
        CaveRepositoryServiceImpl service = new CaveRepositoryServiceImpl();
        service.setBundleContext(FrameworkUtil.getBundle(Activator.class).getBundleContext());
        service.setStorageLocation(new File(getString("cave.storage.location", System.getProperty("karaf.data") + File.separator + "cave")));
        service.init();
        register(CaveRepositoryService.class, service);

        CaveFeatureGatewayImpl gateway = new CaveFeatureGatewayImpl();
        register(CaveFeatureGateway.class, gateway);
    }

}
