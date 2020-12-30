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

/**
 * FileContentSettings
 *
 * @author Sébastien Lepage / last modified by $Author$
 * @version $Revision$
 * @since 6.3
 */
public class FileContentSettings {

    private Marker[] startMarkers = null;
    private Marker[] endMarkers = null;
    private String type;

    public Marker[] getStartMarkers() {
        if (startMarkers == null) {
            return new Marker[0];
        }
        return startMarkers;
    }

    public FileContentSettings setStartMarkers(Marker... startMarkers) {
        this.startMarkers = startMarkers;
        return this;
    }

    public Marker[] getEndMarkers() {
        if (endMarkers == null) {
            return new Marker[0];
        }
        return endMarkers;
    }

    public FileContentSettings setEndMarkers(Marker... endMarkers) {
        this.endMarkers = endMarkers;
        return this;
    }

    public String getType() {
        return type;
    }

    public FileContentSettings setType(String type) {
        this.type = type;
        return this;
    }
}
