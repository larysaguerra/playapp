package asyral.com.playapp.data;

import io.realm.RealmObject;

/**
 * Created by guerra on 10/01/17.
 */

public class ImageRealmObject  extends RealmObject{

    private String label;
    private String height;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
