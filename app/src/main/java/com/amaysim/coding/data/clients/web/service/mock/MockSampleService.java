package com.amaysim.coding.data.clients.web.service.mock;

import android.content.Context;

import com.amaysim.coding.data.clients.web.response.SampleResponse;
import com.amaysim.coding.data.clients.web.service.SampleService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.reactivex.Observable;

public class MockSampleService
        implements SampleService {

    private Context context;
    private Gson gson;

    public MockSampleService(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    @Override
    public Observable<SampleResponse> login(String number, String password) {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream stream = context.getAssets().open("collection.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String str;

            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }

            reader.close();
        } catch (IOException e) {
            return Observable.error(e);
        }

        SampleResponse response = gson.fromJson(builder.toString(), SampleResponse.class);

        if(response.included.get(0).id.equals(number)) {
            return Observable.just(response);
        }

        return Observable.error(new IllegalArgumentException("Invalid number/password"));
    }
}