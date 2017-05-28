package com.amaysim.coding.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.amaysim.coding.R;
import com.amaysim.coding.data.clients.Cache;
import com.amaysim.coding.data.clients.disk.preference.SharedPreferencesCache;
import com.amaysim.coding.data.managers.AndroidRxManager;
import com.amaysim.coding.data.managers.UserManager;
import com.amaysim.coding.domain.repositories.RxRepository;
import com.amaysim.coding.domain.repositories.UserRepository;
import com.amaysim.coding.presentation.contracts.CustomerDetailsContract;
import com.amaysim.coding.presentation.presenters.CustomerDetailsPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

public class CustomerDetailsActivity
        extends MvpActivity<CustomerDetailsContract.View, CustomerDetailsContract.Presenter>
        implements CustomerDetailsContract.View {

    private TextView numberTextView;
    private TextView subscriptionNameTextView;
    private TextView subscriptionPriceTextView;
    private TextView subscriptionCreditTextView;
    private TextView subscriptionDataTextView;
    private TextView subscriptionExpiryTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        numberTextView = (TextView) findViewById(R.id.number_text_view);
        subscriptionNameTextView = (TextView) findViewById(R.id.subscription_name_text_view);
        subscriptionPriceTextView = (TextView) findViewById(R.id.subscription_price_text_view);
        subscriptionCreditTextView = (TextView) findViewById(R.id.subscription_credit_text_view);
        subscriptionDataTextView = (TextView) findViewById(R.id.subscription_data_text_view);
        subscriptionExpiryTextView = (TextView) findViewById(R.id.subscription_expiry_text_view);

        presenter.load();
    }

    @NonNull
    @Override
    public CustomerDetailsContract.Presenter createPresenter() {
        RxRepository rxRepository = new AndroidRxManager();
        Cache cache = new SharedPreferencesCache(getSharedPreferences(getPackageName(), Context.MODE_APPEND));
        UserRepository userRepository = new UserManager(cache);
        return new CustomerDetailsPresenter(rxRepository, userRepository);
    }


    public static Intent newIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CustomerDetailsActivity.class);
        return intent;
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNumber(String number) {
        numberTextView.setText(number);
    }

    @Override
    public void setSubscriptionName(String name) {
        subscriptionNameTextView.setText(name);
    }

    @Override
    public void setSubscriptionCreditBalance(String creditBalance) {
        subscriptionCreditTextView.setText(creditBalance);
    }

    @Override
    public void setSubscriptionDataBalance(String dataBalance) {
        subscriptionDataTextView.setText(dataBalance);
    }

    @Override
    public void setSubscriptionExpiryDate(String expiryDate) {
        subscriptionExpiryTextView.setText(expiryDate);
    }

    @Override
    public void setSubscriptionPrice(String price) {
        subscriptionPriceTextView.setText(price);
    }
}