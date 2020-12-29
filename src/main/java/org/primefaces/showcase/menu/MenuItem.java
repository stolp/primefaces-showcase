package org.primefaces.showcase.menu;

import java.io.Serializable;
import java.util.List;

public class MenuItem implements Serializable {
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
}
