package com.amaysim.coding.presentation.presenters;

import com.amaysim.coding.domain.entities.User;
import com.amaysim.coding.domain.repositories.RxRepository;
import com.amaysim.coding.domain.repositories.UserRepository;
import com.amaysim.coding.domain.usecases.CustomerDetailsUseCase;
import com.amaysim.coding.presentation.contracts.CustomerDetailsContract;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class CustomerDetailsPresenter
        extends MvpBasePresenter<CustomerDetailsContract.View>
        implements CustomerDetailsContract.Presenter, CustomerDetailsUseCase.Callback {

    private CustomerDetailsUseCase useCase;

    public CustomerDetailsPresenter(RxRepository rxRepository, UserRepository userRepository) {
        useCase = new CustomerDetailsUseCase(this, rxRepository, userRepository);
    }

    @Override
    public void load() {
        useCase.load();
    }

    @Override
    public void showUser(User user) {
        CustomerDetailsContract.View view = getView();
        view.setNumber(user.msn);
        view.setSubscriptionName(user.subscription.name);
        view.setSubscriptionCreditBalance(user.subscription.getCreditBalance());
        view.setSubscriptionDataBalance(user.subscription.getDataBalance());
        view.setSubscriptionExpiryDate(user.subscription.getExpiryDate());
        view.setSubscriptionPrice(user.subscription.getPrice());
    }

    @Override
    public void showErrorMessage(String message) {
        CustomerDetailsContract.View view = getView();
        view.showErrorMessage(message);
    }
}