package com.amaysim.coding.presentation.presenters;

import com.amaysim.coding.domain.repositories.AuthRepository;
import com.amaysim.coding.domain.repositories.RxRepository;
import com.amaysim.coding.domain.usecases.LoginUseCase;
import com.amaysim.coding.presentation.contracts.LoginContract;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class LoginPresenter
        extends MvpBasePresenter<LoginContract.View>
        implements LoginContract.Presenter, LoginUseCase.Callback {

    private LoginUseCase useCase;

    public LoginPresenter(RxRepository rxRepository, AuthRepository authRepository) {
        useCase = new LoginUseCase(this, rxRepository, authRepository);
    }

    @Override
    public void login(String number, String password) {
        LoginContract.View view = getView();
        view.showLoadingDialog();
        useCase.login(number, password);
    }

    @Override
    public void showErrorMessage(String message) {
        LoginContract.View view = getView();
        view.hideLoadingDialog();
        view.showErrorMessage(message);
    }

    @Override
    public void showCustomDetailsScreen() {
        LoginContract.View view = getView();
        view.hideLoadingDialog();
        view.showCustomDetailsScreen();
    }
}