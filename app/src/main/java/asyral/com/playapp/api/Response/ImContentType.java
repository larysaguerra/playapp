
package asyral.com.playapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImContentType implements Serializable {

    @SerializedName("attributes")
    @Expose
    private Attributes__ attributes;

    public Attributes__ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes__ attributes) {
        this.attributes = attributes;
    }

}
