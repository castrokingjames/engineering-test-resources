package com.amaysim.coding.domain.repositories;

import io.reactivex.Observable;

public interface RxRepository {

    <T> Observable<T> scheduler(Observable<T> observable);
}