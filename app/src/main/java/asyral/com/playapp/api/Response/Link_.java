
package asyral.com.playapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Link_ implements Serializable {

    @SerializedName("attributes")
    @Expose
    private Attributes________ attributes;

    public Attributes________ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes________ attributes) {
        this.attributes = attributes;
    }

}
