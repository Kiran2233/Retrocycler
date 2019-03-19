package com.example.retrocycler;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String API_KEY ="5d7703f7f90640f299f8d73287d4a86e";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MainArticleAdapter adapter;
    private List<Article> articleList;
    private APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv1);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setHasFixedSize(true);


       apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        Log.d("oncreate","hkhhdhf");
       Call<ResponseModel> call = apiInterface.getLatestNews("bbc-news",API_KEY);
        Log.d("oncreate","hkhhdhf");
  call.enqueue(new Callback<ResponseModel>() {
      @Override
      public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {


          if(response.body().getStatus().equals("ok")) {
              Log.d("onresponse","entered if1");
              final List<Article> articleList = response.body().getArticles();
              Log.d("onresponse","entered if1 again");
              if(articleList.size()>0) {
                  Log.d("onresponse","entered if2");
                  MainArticleAdapter mainArticleAdapter = new MainArticleAdapter(MainActivity.this,articleList, new MainArticleAdapter.OnItemClickListener() {
                      @Override
                      public void onItemClick(Article item,int position) {
                          Intent i = new Intent(MainActivity.this,Fullnews.class);
                          i.putExtra("url",articleList.get(position).getUrl());
                          startActivity(i);
                      }
                  });
                  recyclerView.setAdapter(mainArticleAdapter);

              }
          }
      }

      @Override
      public void onFailure(Call<ResponseModel> call, Throwable t) {
          Log.d("onfail","entered",t);

      }
  });

    }


    }


