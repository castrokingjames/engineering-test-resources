package com.amaysim.coding.domain.repositories;

import com.amaysim.coding.domain.entities.User;

import io.reactivex.Observable;

public interface AuthRepository
        extends UserRepository {

    Observable<User> login(String number, String password);

}