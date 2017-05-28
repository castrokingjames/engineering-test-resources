package com.amaysim.coding.domain.usecases;

import com.amaysim.coding.domain.entities.User;
import com.amaysim.coding.domain.repositories.RxRepository;
import com.amaysim.coding.domain.repositories.UserRepository;

public class CustomerDetailsUseCase {

    private Callback callback;
    private RxRepository rxRepository;
    private UserRepository userRepository;

    public CustomerDetailsUseCase(Callback callback,
                                  RxRepository rxRepository,
                                  UserRepository userRepository) {
        this.callback = callback;
        this.rxRepository = rxRepository;
        this.userRepository = userRepository;
    }

    public void load() {
        userRepository
                .load()
                .compose(rxRepository::scheduler)
                .subscribe(
                        this::onLoad,
                        this::onLoadError
                );
    }

    private void onLoad(User user) {
        callback.showUser(user);
    }

    private void onLoadError(Throwable throwable) {
        callback.showErrorMessage(throwable.getMessage());
    }

    public interface Callback {

        void showUser(User user);

        void showErrorMessage(String message);
    }
}