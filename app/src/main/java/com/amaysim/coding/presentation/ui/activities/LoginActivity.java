package com.amaysim.coding.presentation.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amaysim.coding.R;
import com.amaysim.coding.data.clients.Cache;
import com.amaysim.coding.data.clients.disk.preference.SharedPreferencesCache;
import com.amaysim.coding.data.clients.web.service.SampleService;
import com.amaysim.coding.data.clients.web.service.mock.MockSampleService;
import com.amaysim.coding.data.managers.AndroidRxManager;
import com.amaysim.coding.data.managers.AuthManager;
import com.amaysim.coding.domain.repositories.AuthRepository;
import com.amaysim.coding.domain.repositories.RxRepository;
import com.amaysim.coding.presentation.contracts.LoginContract;
import com.amaysim.coding.presentation.presenters.LoginPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

public class LoginActivity
        extends MvpActivity<LoginContract.View, LoginContract.Presenter>
        implements LoginContract.View, View.OnClickListener {

    private EditText numberEditText;
    private EditText passwordEditText;
    private CardView loginCardView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        numberEditText = (EditText) findViewById(R.id.number_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        loginCardView = (CardView) findViewById(R.id.login_card_view);
        loginCardView.setOnClickListener(this);
    }

    @NonNull
    @Override
    public LoginContract.Presenter createPresenter() {
        RxRepository rxRepository = new AndroidRxManager();
        Cache cache = new SharedPreferencesCache(getSharedPreferences(getPackageName(), Context.MODE_APPEND));
        SampleService sampleService = new MockSampleService(this);
        AuthRepository authRepository = new AuthManager(sampleService, cache);
        return new LoginPresenter(rxRepository, authRepository);
    }

    @Override
    public void showLoadingDialog() {
        progressDialog = ProgressDialog.show(this, getString(R.string.loading), getString(R.string.connecting_to_server));
    }

    @Override
    public void hideLoadingDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCustomDetailsScreen() {
        Intent intent = CustomerDetailsActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        String number = numberEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        presenter.login(number, password);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        return intent;
    }
}