package com.amaysim.coding.presentation.contracts;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface CustomerDetailsContract {

    interface View
            extends MvpView {

        void showErrorMessage(String message);

        void setNumber(String number);

        void setSubscriptionName(String name);

        void setSubscriptionCreditBalance(String creditBalance);

        void setSubscriptionDataBalance(String dataBalance);

        void setSubscriptionExpiryDate(String expiryDate);

        void setSubscriptionPrice(String price);
    }

    interface Presenter
            extends MvpPresenter<View> {

        void load();
    }
}