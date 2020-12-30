/*
 * Copyright 2009-2020 PrimeTek.
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
package org.primefaces.showcase.util;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.primefaces.cache.CacheProvider;

/**
 * Skip the usage via PrimeApplicationContext, it's not available in 6.2.x
 */
@ApplicationScoped
public class ShowcaseCacheProvider {
    private CacheProvider cacheProvider;

    @PostConstruct
    public void init() {
        cacheProvider = new EHCache3Provider();
    }
    
    public CacheProvider getCacheProvider() {
        return cacheProvider;
    }
}
