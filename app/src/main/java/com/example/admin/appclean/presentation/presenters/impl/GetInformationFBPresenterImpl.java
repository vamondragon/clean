package com.example.admin.appclean.presentation.presenters.impl;

import android.os.Bundle;

import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;
import com.example.admin.appclean.domain.interactors.GetInformationFBInteractor;
import com.example.admin.appclean.domain.interactors.impl.GetInformationFBInteractorImpl;
import com.example.admin.appclean.domain.repository.UserRepository;
import com.example.admin.appclean.presentation.presenters.AbstractPresenter;
import com.example.admin.appclean.presentation.presenters.GetInformationFBPresenter;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public class GetInformationFBPresenterImpl extends AbstractPresenter implements GetInformationFBPresenter,
        GetInformationFBInteractor.Callback {

    private GetInformationFBInteractorImpl.Callback mCallback;
    private GetInformationFBPresenter.View mView;
    private UserRepository userRepository;
    LoginButton loginButton;
    CallbackManager callbackManager;

    public GetInformationFBPresenterImpl(Executor executor, MainThread mainThread, View view,
                                         UserRepository userRepository,
                                         LoginButton loginButton,
                                         CallbackManager callbackManager) {
        super(executor, mainThread);
        mView = view;
        this.userRepository = userRepository;
        this.loginButton = loginButton;
        this.callbackManager = callbackManager;

    }


    @Override
    public void getInformationFB() {
        GetInformationFBInteractor addUserInteractor = new GetInformationFBInteractorImpl(
                mExecutor,
                mMainThread,
                userRepository,
                loginButton,
                callbackManager,
                this
        );


        addUserInteractor.execute();
    }


    @Override
    public void onRetrieveInformation(Bundle bFacebookData, String mensaje) {
        mView.onDataRetrieve(bFacebookData, mensaje);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }


}
