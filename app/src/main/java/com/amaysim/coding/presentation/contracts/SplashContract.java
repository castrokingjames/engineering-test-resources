package com.amaysim.coding.presentation.contracts;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface SplashContract {

    interface View
            extends MvpView {

        void showLoginScreen();

        void showErrorMessage(String message);
    }

    interface Presenter
            extends MvpPresenter<View> {

        void load();
    }
}