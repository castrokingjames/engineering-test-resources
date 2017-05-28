package com.amaysim.coding.presentation.contracts;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface LoginContract {

    interface View
            extends MvpView {

        void showLoadingDialog();

        void hideLoadingDialog();

        void showErrorMessage(String message);

        void showCustomDetailsScreen();
    }

    interface Presenter
            extends MvpPresenter<View> {

        void login(String number, String password);
    }
}