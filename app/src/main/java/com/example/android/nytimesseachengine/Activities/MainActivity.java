package com.example.android.nytimesseachengine.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android.nytimesseachengine.R;
import com.example.android.nytimesseachengine.RecyclerView.RecycleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

public class MainActivity extends AppCompatActivity implements RecycleAdapter.ListItemClickListener {
    RecyclerView recyclerView;
    ArrayList<String> arrayList;
    String[] cities={"Mumbai","NCR","Bengaluru","Hyderabad","Ahmedabad","Chandigarh","Chennai","Pune","Kolkata","Kochi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        arrayList=new ArrayList<>();
        for(String s:cities)
        {
            arrayList.add(s);
        }
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        RecycleAdapter recycleAdapter=new RecycleAdapter(this);
        recycleAdapter.settingUpList(arrayList);
        recyclerView.setAdapter(recycleAdapter);
    }

    @Override
    public void OnListItemClick(int clickedItemIndex) {

        String city = arrayList.get(clickedItemIndex);
        city=city.toLowerCase();
        String baseURL="https://in.bookmyshow.com/"+city+"/events/";
        Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
        intent.putExtra("baseurl",baseURL);
        startActivity(intent);
    }
}
