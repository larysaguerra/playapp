package asyral.com.playapp.api;


import asyral.com.playapp.api.Response.Feed;
import asyral.com.playapp.tools.Constants;
import asyral.com.playapp.tools.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by guerra on 10/01/17.
 */

public class ApiUtil {

    public interface AppInterface {
        //example-> https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json
        @GET("us/rss/{category}/limit={limit}/json")
        Call<Feed> getApps(@Path("category") String category,
                           @Path("limit") String limit);
    }

    public void getTopFreeApps(String category, int limit, Callback<Feed> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AppInterface service = retrofit.create(AppInterface.class);
        Utils.logD("ApiUtil", "us/rss/" + category + "/limit=" + limit + "/json");
        Call<Feed> feedCall = service.getApps(category, String.valueOf(limit));
        feedCall.enqueue(callback);
    }

}
