package com.amaysim.coding.presentation.presenters;

import com.amaysim.coding.domain.repositories.InstallRepository;
import com.amaysim.coding.domain.repositories.RxRepository;
import com.amaysim.coding.domain.usecases.SplashUseCase;
import com.amaysim.coding.presentation.contracts.SplashContract;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class SplashPresenter
        extends MvpBasePresenter<SplashContract.View>
        implements SplashContract.Presenter, SplashUseCase.Callback {

    private SplashUseCase useCase;

    public SplashPresenter(RxRepository rxRepository, InstallRepository installRepository) {
        useCase = new SplashUseCase(this, rxRepository, installRepository);
    }

    @Override
    public void load() {
        useCase.load();
    }

    @Override
    public void showLoginScreen() {
        SplashContract.View view = getView();
        view.showLoginScreen();
    }

    @Override
    public void showErrorMessage(String message) {
        SplashContract.View view = getView();
        view.showErrorMessage(message);
    }
}