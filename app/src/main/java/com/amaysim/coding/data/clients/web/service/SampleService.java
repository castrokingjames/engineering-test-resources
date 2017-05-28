package com.amaysim.coding.data.clients.web.service;

import com.amaysim.coding.data.clients.web.response.SampleResponse;

import io.reactivex.Observable;

public interface SampleService {

    Observable<SampleResponse> login(String number, String password);
}