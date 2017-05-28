package com.amaysim.coding.data.managers;

import com.amaysim.coding.domain.repositories.RxRepository;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AndroidRxManager
        implements RxRepository {

    @Override
    public <T> Observable<T> scheduler(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}