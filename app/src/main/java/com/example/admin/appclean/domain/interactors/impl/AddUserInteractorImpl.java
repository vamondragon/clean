package com.example.admin.appclean.domain.interactors.impl;

import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;
import com.example.admin.appclean.domain.interactors.AddUserInteractor;
import com.example.admin.appclean.domain.interactors.base.AbstractInteractor;
import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.domain.repository.UserRepository;


public class AddUserInteractorImpl extends AbstractInteractor implements AddUserInteractor {

    private AddUserInteractor.Callback mCallback;
    private UserRepository mUserRepository;
    private UserGenericModel userGenericModel;

    public AddUserInteractorImpl(Executor threadExecutor,
                                 MainThread mainThread, Callback callback,
                                 UserRepository userRepository, UserGenericModel userGenericModel) {

        super(threadExecutor, mainThread);
        this.mCallback = callback;
        this.mUserRepository = userRepository;
        this.userGenericModel = userGenericModel;
    }

    @Override
    public void run() {

        // constructor userGenericModel Object
        UserGenericModel userGenericModel = this.userGenericModel;

        //Call Repository insert method
        mUserRepository.insert(userGenericModel);

        // notify on the main thread that we have inserted this item
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onUserAdded();
            }
        });

    }
}
