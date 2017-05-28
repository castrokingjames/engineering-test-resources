package com.amaysim.coding.data.managers;

import com.amaysim.coding.data.clients.Cache;
import com.amaysim.coding.data.clients.web.response.SampleResponse;
import com.amaysim.coding.data.clients.web.service.SampleService;
import com.amaysim.coding.domain.entities.Subscription;
import com.amaysim.coding.domain.entities.User;
import com.amaysim.coding.domain.repositories.AuthRepository;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class AuthManager
        extends UserManager
        implements AuthRepository {

    private SampleService sampleService;

    public AuthManager(SampleService sampleService, Cache cache) {
        super(cache);
        this.sampleService = sampleService;
    }

    @Override
    public Observable<User> login(String number, String password) {
        return sampleService
                .login(number, password)
                .switchMap(this::transform)
                .switchMap(this::save);
    }

    private ObservableSource<User> transform(SampleResponse response) {
        String title = response.data.attributes.get("title");
        String firstName = response.data.attributes.get("first-name");
        String lastName = response.data.attributes.get("last-name");
        String dateOfBirth = response.data.attributes.get("date-of-birth");
        String contactNumber = response.data.attributes.get("contact-number");
        String emailAddress = response.data.attributes.get("email-address");

        String msn =
                response
                        .included.get(0)
                        .attributes
                        .get("msn");

        String subscriptionName =
                response
                        .included.get(2)
                        .attributes
                        .get("name");
        String subscriptionDataBalance =
                response
                        .included
                        .get(1)
                        .attributes
                        .get("included-data-balance");
        String subscriptionCreditBalance =
                response
                        .included
                        .get(1)
                        .attributes
                        .get("included-credit-balance");
        String subscriptionExpiryDate =
                response
                        .included
                        .get(1)
                        .attributes
                        .get("expiry-date");
        String subscriptionPrice =
                response
                        .included
                        .get(2)
                        .attributes
                        .get("price");

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