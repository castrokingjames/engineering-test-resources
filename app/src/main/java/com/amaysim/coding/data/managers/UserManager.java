package com.amaysim.coding.data.managers;

import com.amaysim.coding.data.clients.Cache;
import com.amaysim.coding.domain.entities.Subscription;
import com.amaysim.coding.domain.entities.User;
import com.amaysim.coding.domain.repositories.UserRepository;

import io.reactivex.Observable;

public class UserManager
        implements UserRepository {

    private static final String KEY_TITLE = "KEY_TITLE";
    private static final String KEY_FIRST_NAME = "KEY_FIRST_NAME";
    private static final String KEY_LAST_NAME = "KEY_LAST_NAME";
    private static final String KEY_DATE_OF_BIRTH = "KEY_DATE_OF_BIRTH";
    private static final String KEY_MSN = "KEY_MSN";
    private static final String KEY_CONTACT_NUMBER = "KEY_CONTACT_NUMBER";
    private static final String KEY_EMAIL_ADDRESS = "KEY_EMAIL_ADDRESS";
    private static final String KEY_SUBSCRIPTION_NAME = "KEY_SUBSCRIPTION_NAME";
    private static final String KEY_SUBSCRIPTION_DATA_BALANCE = "KEY_SUBSCRIPTION_DATA_BALANCE";
    private static final String KEY_SUBSCRIPTION_CREDIT_BALANCE = "KEY_SUBSCRIPTION_CREDIT_BALANCE";
    private static final String KEY_SUBSCRIPTION_EXPIRY_DATE = "KEY_SUBSCRIPTION_EXPIRY_DATE";
    private static final String KEY_SUBSCRIPTION_PRICE = "KEY_SUBSCRIPTION_PRICE";

    private Cache cache;

    public UserManager(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Observable<User> save(User user) {
        cache.putString(KEY_TITLE, user.title);
        cache.putString(KEY_FIRST_NAME, user.firstName);
        cache.putString(KEY_LAST_NAME, user.lastName);
        cache.putString(KEY_DATE_OF_BIRTH, user.dateOfBirth);
        cache.putString(KEY_CONTACT_NUMBER, user.contactNumber);
        cache.putString(KEY_MSN, user.msn);
        cache.putString(KEY_EMAIL_ADDRESS, user.emailAddress);
        cache.putString(KEY_SUBSCRIPTION_NAME, user.subscription.name);
        cache.putString(KEY_SUBSCRIPTION_DATA_BALANCE, user.subscription.dataBalance);
        cache.putString(KEY_SUBSCRIPTION_CREDIT_BALANCE, user.subscription.creditBalance);
        cache.putString(KEY_SUBSCRIPTION_EXPIRY_DATE, user.subscription.expiryDate);
        cache.putString(KEY_SUBSCRIPTION_PRICE, user.subscription.price);

        return Observable.just(user);
    }

    @Override
    public Observable<User> load() {
        String title = cache.getString(KEY_TITLE, null);
        String firstName = cache.getString(KEY_FIRST_NAME, null);
        String lastName = cache.getString(KEY_LAST_NAME, null);
        String dateOfBirth = cache.getString(KEY_DATE_OF_BIRTH, null);
        String contactNumber = cache.getString(KEY_CONTACT_NUMBER, null);
        String msn = cache.getString(KEY_MSN, null);
        String emailAddress = cache.getString(KEY_EMAIL_ADDRESS, null);
        String subscriptionName = cache.getString(KEY_SUBSCRIPTION_NAME, null);
        String subscriptionDataBalance = cache.getString(KEY_SUBSCRIPTION_DATA_BALANCE, null);
        String subscriptionCreditBalance = cache.getString(KEY_SUBSCRIPTION_CREDIT_BALANCE, null);
        String subscriptionExpiryDate = cache.getString(KEY_SUBSCRIPTION_EXPIRY_DATE, null);
        String subscriptionPrice = cache.getString(KEY_SUBSCRIPTION_PRICE, null);
        Subscription subscription = new Subscription(
                subscriptionName,
                subscriptionDataBalance,
                subscriptionCreditBalance,
                subscriptionExpiryDate,
                subscriptionPrice
        );

        User user = new User(
                title,
                firstName,
                lastName,
                dateOfBirth,
                contactNumber,
                msn,
                emailAddress,
                subscription
        );

        return Observable.just(user);
    }
}