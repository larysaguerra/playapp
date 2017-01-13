
package asyral.com.playapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Link implements Serializable {

    @SerializedName("attributes")
    @Expose
    private Attributes___ attributes;

    public Attributes___ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes___ attributes) {
        this.attributes = attributes;
    }

}
