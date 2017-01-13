
package asyral.com.playapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("attributes")
    @Expose
    private Attributes______ attributes;

    public Attributes______ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes______ attributes) {
        this.attributes = attributes;
    }

}
