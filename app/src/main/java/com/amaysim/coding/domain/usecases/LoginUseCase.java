package com.amaysim.coding.domain.usecases;

import com.amaysim.coding.domain.entities.User;
import com.amaysim.coding.domain.repositories.AuthRepository;
import com.amaysim.coding.domain.repositories.RxRepository;

public class LoginUseCase {

    private Callback callback;
    private RxRepository rxRepository;
    private AuthRepository authRepository;

    public LoginUseCase(Callback callback,
                        RxRepository rxRepository,
                        AuthRepository authRepository) {
        this.callback = callback;
        this.rxRepository = rxRepository;
        this.authRepository = authRepository;
    }

    public void login(String number, String password) {
        if (number.isEmpty() || password.isEmpty()) {
            callback.showErrorMessage("Empty number/password");
            return;
        }

        authRepository
                .login(number, password)
                .compose(rxRepository::scheduler)
                .subscribe(
                        this::onLogin,
                        this::onLoginError
                );
    }

    private void onLogin(User user) {
        callback.showCustomDetailsScreen();
    }

    private void onLoginError(Throwable throwable) {
        callback.showErrorMessage(throwable.getMessage());
    }

    public interface Callback {

        void showErrorMessage(String message);

        void showCustomDetailsScreen();
    }
}