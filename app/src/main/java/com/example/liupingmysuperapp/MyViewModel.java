package com.example.liupingmysuperapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MyViewModel extends ViewModel {
    private MutableLiveData<ListCatFacts> catFacts = null;
    public MutableLiveData<ListCatFacts> getFact(){
        if(catFacts == null) {
            catFacts = new MutableLiveData<>();
            // request fact only when it's not requested yet
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://catfact.ninja/facts?limit=15").build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if(!response.isSuccessful()){
                        throw new IOException(response.toString());
                    } else {
                        ResponseBody responseBody = response.body();
                        if(responseBody != null) {
                            Gson gson = new Gson();
                            ListCatFacts facts = gson.fromJson(responseBody.string(), ListCatFacts.class);
                            catFacts.postValue(facts);
                        }

                    }
                }
            });
        }

        return catFacts;

    }
}
