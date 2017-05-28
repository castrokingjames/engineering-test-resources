package com.amaysim.coding.data.clients.disk.preference;

import android.content.SharedPreferences;

import com.amaysim.coding.data.clients.Cache;

public class SharedPreferencesCache
        implements Cache {

    private SharedPreferences sharedPreferences;

    public SharedPreferencesCache(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }
}