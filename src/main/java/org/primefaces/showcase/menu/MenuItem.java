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
package org.primefaces.showcase.menu;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MenuItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private final String label;
    private String url;
    private List<MenuItem> menuItems;

    public MenuItem(String label, String url) {
        this.label = label;
        this.url = url;
    }

    public MenuItem(String label, List<MenuItem> menuItems) {
        this.label = label;
        this.menuItems = menuItems;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

	@Override
	public int hashCode() {
		return Objects.hash(label, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MenuItem)) {
			return false;
		}
		MenuItem other = (MenuItem) obj;
		return Objects.equals(label, other.label) && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "MenuItem [label=" + label + ", url=" + url + "]";
	}
}
