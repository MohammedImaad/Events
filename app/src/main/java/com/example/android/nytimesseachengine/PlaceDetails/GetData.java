package com.example.android.nytimesseachengine.PlaceDetails;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetData {

    @GET("maps/api/place/findplacefromtext/json?inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=AIzaSyDTsd-Li6aTI0CmNuEKxkOElp7tWfbsTm4")
    Call<PlacesAndShiz> getData(@Query("input") String s);
}
