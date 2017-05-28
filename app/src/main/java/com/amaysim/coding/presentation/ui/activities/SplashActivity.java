package com.amaysim.coding.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.amaysim.coding.R;
import com.amaysim.coding.data.clients.Cache;
import com.amaysim.coding.data.clients.disk.preference.SharedPreferencesCache;
import com.amaysim.coding.data.managers.AndroidRxManager;
import com.amaysim.coding.data.managers.InstallManager;
import com.amaysim.coding.domain.repositories.InstallRepository;
import com.amaysim.coding.domain.repositories.RxRepository;
import com.amaysim.coding.presentation.contracts.SplashContract;
import com.amaysim.coding.presentation.presenters.SplashPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

public class SplashActivity
        extends MvpActivity<SplashContract.View, SplashContract.Presenter>
        implements SplashContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter.load();
    }

    @NonNull
    @Override
    public SplashContract.Presenter createPresenter() {
        RxRepository rxRepository = new AndroidRxManager();
        Cache cache = new SharedPreferencesCache(getSharedPreferences(getPackageName(), Context.MODE_APPEND));
        InstallRepository installRepository = new InstallManager(cache);
        return new SplashPresenter(rxRepository, installRepository);
    }

    @Override
    public void showLoginScreen() {
        Intent intent = LoginActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}