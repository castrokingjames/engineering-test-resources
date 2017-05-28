package com.amaysim.coding.domain.repositories;

import com.amaysim.coding.domain.entities.Version;

import io.reactivex.Observable;

public interface InstallRepository {

    Observable<Version> loadVersion();

    Observable<Version> saveVersion(Version version);
}