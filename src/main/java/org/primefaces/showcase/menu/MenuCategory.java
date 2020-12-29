package org.primefaces.showcase.menu;

import java.io.Serializable;
import java.util.List;

public class MenuCategory implements Serializable {
    private final String label;
    private List<MenuItem> menuItems;

    public MenuCategory(String label, List<MenuItem> menuItems) {
        this.label = label;
        this.menuItems = menuItems;
    }

    public String getLabel() {
        return label;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
