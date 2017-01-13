package asyral.com.playapp.data;

import io.realm.RealmObject;

/**
 * Created by guerra on 10/01/17.
 */

public class CategoryRealmObject extends RealmObject {

    private String id;
    private String label;
    private String term;
    private String scheme;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

}
