package com.example.admin.appclean.presentation.presenters.impl;


import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;
import com.example.admin.appclean.domain.interactors.AddUserInteractor;
import com.example.admin.appclean.domain.interactors.DeleteUserInteractor;
import com.example.admin.appclean.domain.interactors.GetAllUserInteractor;
import com.example.admin.appclean.domain.interactors.impl.AddUserInteractorImpl;
import com.example.admin.appclean.domain.interactors.impl.GetAllUserInteractorImpl;
import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.domain.repository.UserRepository;
import com.example.admin.appclean.presentation.presenters.AbstractPresenter;
import com.example.admin.appclean.presentation.presenters.MainUserPresenter;

import java.util.List;

public class MainUserPresenterImpl extends AbstractPresenter implements MainUserPresenter,
        AddUserInteractor.Callback,
        GetAllUserInteractor.Callback,
        DeleteUserInteractor.Callback {

    private MainUserPresenter.View mView;
    private UserRepository mUserRepository;

    public MainUserPresenterImpl(Executor executor, MainThread mainThread,
                                 View view, UserRepository userRepository) {
        super(executor, mainThread);
        mView = view;
        mUserRepository = userRepository;
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

    @Override
    public void addNewClient(UserGenericModel userGenericModel) {

        AddUserInteractor addUserInteractor = new AddUserInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository,
                userGenericModel
        );

        addUserInteractor.execute();
    }


    @Override
    public void getAllClients() {
        GetAllUserInteractor getAllUserInteractor = new GetAllUserInteractorImpl(
                mExecutor,
                mMainThread,
                mUserRepository,
                this
        );

        getAllUserInteractor.execute();
    }

    @Override
    public void onUserAdded() {
        mView.onClientAdded();
    }

    @Override
    public void onUserRetrieved(List<UserGenericModel> userGenericModelList) {
        mView.showClients(userGenericModelList);
    }

    @Override
    public void onUserDelete(UserGenericModel userGenericModel) {
        System.out.println("onUserDelete Eliminando el usuario");
    }

    @Override
    public void noUserFound() {

    }
}
