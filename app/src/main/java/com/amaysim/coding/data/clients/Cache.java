package com.amaysim.coding.data.clients;

public interface Cache {

    void putString(String key, String value);

    String getString(String key, String defaultValue);
}
