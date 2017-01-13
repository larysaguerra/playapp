package asyral.com.playapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Feed implements Serializable {

    @SerializedName("feed")
    @Expose
    private Feed_ feed;

    public Feed_ getFeed() {
        return feed;
    }

    public void setFeed(Feed_ feed) {
        this.feed = feed;
    }

}
