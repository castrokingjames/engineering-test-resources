package com.amaysim.coding.domain.usecases;

import com.amaysim.coding.BuildConfig;
import com.amaysim.coding.domain.entities.Version;
import com.amaysim.coding.domain.repositories.InstallRepository;
import com.amaysim.coding.domain.repositories.RxRepository;

import java.util.concurrent.TimeUnit;

// TODO
// Display only once(first run/update) or always?
public class SplashUseCase {

    private Callback callback;
    private RxRepository rxRepository;
    private InstallRepository installRepository;

    public SplashUseCase(Callback callback,
                         RxRepository rxRepository,
                         InstallRepository installRepository) {
        this.callback = callback;
        this.rxRepository = rxRepository;
        this.installRepository = installRepository;
    }

    public void load() {
        installRepository
                .loadVersion()
                .delay(2, TimeUnit.SECONDS)
                .compose(rxRepository::scheduler)
                .subscribe(
                        this::onLoad,
                        this::onLoadError
                );
    }

    private void onLoad(Version version) {
        if (!version.version.equals(BuildConfig.VERSION_NAME)) {
            saveVersion();
        } else {
            callback.showLoginScreen();
        }
    }

    private void onLoadError(Throwable throwable) {
        if (throwable instanceof NullPointerException) {
            saveVersion();
        } else {
            callback.showErrorMessage(throwable.getMessage());
        }
    }

    private void saveVersion() {
        Version version = new Version(BuildConfig.VERSION_NAME);

        installRepository
                .saveVersion(version)
                .compose(rxRepository::scheduler)
                .subscribe(
                        this::onSaveVersion,
                        this::onSaveVersionError
                );
    }

    private void onSaveVersion(Version version) {
        callback.showLoginScreen();
    }

    private void onSaveVersionError(Throwable throwable) {
        callback.showErrorMessage(throwable.getMessage());
    }

    public interface Callback {

        void showLoginScreen();

        void showErrorMessage(String message);
    }
}