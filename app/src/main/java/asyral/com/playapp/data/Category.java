package asyral.com.playapp.data;

import java.io.Serializable;

/**
 * Created by guerra on 11/01/17.
 * Category
 */

public class Category implements Serializable {

    private String type;
    private String name;
    private String icon;

    public Category(String type, String name, String icon) {
        this.name = name;
        this.type = type;
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
