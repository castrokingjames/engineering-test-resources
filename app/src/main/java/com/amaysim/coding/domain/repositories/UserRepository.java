package com.amaysim.coding.domain.repositories;

import com.amaysim.coding.domain.entities.User;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<User> save(User user);

    Observable<User> load();
}