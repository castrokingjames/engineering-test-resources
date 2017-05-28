package com.amaysim.coding.data.managers;

import com.amaysim.coding.data.clients.Cache;
import com.amaysim.coding.domain.entities.Version;
import com.amaysim.coding.domain.repositories.InstallRepository;

import io.reactivex.Observable;

public class InstallManager
        implements InstallRepository {

    private static final String KEY_VERSION = "KEY_VERSION";

    private Cache cache;

    public InstallManager(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Observable<Version> loadVersion() {
        String value = cache.getString(KEY_VERSION, null);

        if (value == null)
            return Observable.error(new NullPointerException("Current version is empty"));

        return Observable.just(new Version(value));
    }

    @Override
    public Observable<Version> saveVersion(Version version) {
        cache.putString(KEY_VERSION, version.version);

        return Observable.just(version);
    }
}