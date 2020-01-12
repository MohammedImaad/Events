package com.example.android.nytimesseachengine.Activities;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.android.nytimesseachengine.EventDetails.EventsAndShiz;
import com.example.android.nytimesseachengine.PlaceDetails.GetData;
import com.example.android.nytimesseachengine.PlaceDetails.PlacesAndShiz;
import com.example.android.nytimesseachengine.PlaceDetails.RetrofitClientInstance;
import com.example.android.nytimesseachengine.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    static class Page {
        String content;

        Page(String content) {
            this.content = content;
        }
    }
    static final class PageAdapter implements Converter<ResponseBody, MapsActivity.Page> {
        static final Converter.Factory FACTORY = new Converter.Factory() {
            @Override
            public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                if (type == MapsActivity.Page.class) return new MapsActivity.PageAdapter();
                return null;
            }
        };

        @Override
        public MapsActivity.Page convert(ResponseBody responseBody) throws IOException {
            Document document = Jsoup.parse(responseBody.string());
            Elements script = document.select("script");
            Element v=script.get(9);
            return new MapsActivity.Page(v.html());
        }
    }
    interface PageService {
        @GET
        Call<MapsActivity.Page> get(@Url HttpUrl url);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Dispatcher dispatcher = new Dispatcher(Executors.newFixedThreadPool(20));
        dispatcher.setMaxRequests(20);
        dispatcher.setMaxRequestsPerHost(1);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .connectionPool(new ConnectionPool(100, 30, TimeUnit.SECONDS))
                .build();
        Intent intent=getIntent();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse(intent.getStringExtra("baseurl")))
                .addConverterFactory(MapsActivity.PageAdapter.FACTORY)
                .build();
        MapsActivity.PageService requestAddress = retrofit.create(MapsActivity.PageService.class);
        Call<MapsActivity.Page> pageCall = requestAddress.get(HttpUrl.parse(intent.getStringExtra("baseurl")));
        pageCall.enqueue(new Callback<MapsActivity.Page>() {
            @Override
            public void onResponse(Call<MapsActivity.Page> call, Response<MapsActivity.Page> response) {
                ArrayList<String> address=new ArrayList<>();
                final ArrayList<String> eventName=new ArrayList<>();
                final ArrayList<String> urls=new ArrayList<>();
                String s=response.body().content;
                String s1=s.substring(26);
                s1=s1.replaceAll("u002F","");
                Pattern pattern=Pattern.compile("\"EventTitle\":\"[a-zA-Z0-9!@#$%^*&()\\'-`:\\s_<>{}?.+,/]*\"");
                Pattern pattern1=Pattern.compile("\"VenueName\":\"[a-zA-Z0-9!@#$%^*&()\\'-`:\\s_<>{}?.+,/]*\"");
                Pattern pattern2=Pattern.compile("\"FShareURL\":\"[a-zA-Z0-9!@#$%^*&()\\'-`:\\s_<>{}?.+,/]*\"");
                Matcher matcher=pattern.matcher(s1);
                Matcher matcher1=pattern1.matcher(s1);
                Matcher matcher2=pattern2.matcher(s1);
                while(matcher.find())
                {
                    String nam=matcher.group().substring(13);
                    nam=nam.substring(1,nam.length()-1);
                    eventName.add(nam);
                }
                while(matcher1.find())
                {
                    String s2=matcher1.group().substring(12);
                    address.add(s2);
                }
                while (matcher2.find())
                {
                    String u=matcher2.group().substring(12);
                    Log.i("URLLLLL",u);
                    urls.add(u);
                }
                GetData getData= RetrofitClientInstance.getRetrofitInstance().create(GetData.class);
                for(int h=0;h<15;h++)
                {
                    final int c=h;
                    Call<PlacesAndShiz> call1=getData.getData(address.get(h));
                    call1.enqueue(new Callback<PlacesAndShiz>() {
                        @Override
                        public void onResponse(Call<PlacesAndShiz> call, Response<PlacesAndShiz> response) {
                            try {
                                creatingAMarker(response.body().getCandidates().get(0).getGeometry().getLocation().getLat(),response.body().getCandidates().get(0).getGeometry().getLocation().getLng(),eventName.get(c));
                            }catch (Exception e)
                            {
                                Log.i("URL", response.raw().request().url().url().toString());
                                //e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<PlacesAndShiz> call, Throwable t) {
                            t.printStackTrace();

                        }
                    });

                }
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String title=marker.getTitle();
                        for(int i=0;i<eventName.size();i++)
                        {
                            if(eventName.get(i).equals(title))
                            {
                                Log.i("ClickedURL",urls.get(i));
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                String uriString = urls.get(i).replace("\\\\", "//");
                                intent.setData(Uri.parse(uriString.substring(1,uriString.length()-1)));
                                startActivity(intent);
                            }
                        }
                        return false;
                    }
                });

            }
            @Override
            public void onFailure(Call<MapsActivity.Page> call, Throwable t) {
                t.printStackTrace();
                Log.e("Outside",t.toString());

            }
        });

    }
    public void creatingAMarker(double latitude,double longitude,String s)
    {
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title(s));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
