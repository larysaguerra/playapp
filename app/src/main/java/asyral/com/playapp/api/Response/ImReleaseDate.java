
package asyral.com.playapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImReleaseDate implements Serializable {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("attributes")
    @Expose
    private Attributes_______ attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Attributes_______ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes_______ attributes) {
        this.attributes = attributes;
    }

}
