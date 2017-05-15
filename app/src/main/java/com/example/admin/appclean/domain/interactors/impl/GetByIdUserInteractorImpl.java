package com.example.admin.appclean.domain.interactors.impl;

import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;
import com.example.admin.appclean.domain.interactors.GetByIdUserInteractor;
import com.example.admin.appclean.domain.interactors.base.AbstractInteractor;
import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.domain.repository.UserRepository;

public class GetByIdUserInteractorImpl extends AbstractInteractor implements GetByIdUserInteractor {

    private long mUserId;
    private GetByIdUserInteractor.Callback mCallback;
    private UserRepository mUserRepository;

    public GetByIdUserInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                     long mUserId, Callback mCallback,
                                     UserRepository mUserRepository) {
        super(threadExecutor, mainThread);
        this.mUserId = mUserId;
        this.mCallback = mCallback;
        this.mUserRepository = mUserRepository;
    }

    @Override
    public void run() {

        final UserGenericModel userGenericModel = mUserRepository.getUserById(mUserId);

        if (userGenericModel == null) { // we didn't find the cost we were looking for

            // notify this on the main thread
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.noUserFound();
                }
            });
        } else { // we found it!

            // send it on the main thread
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onUserRetrieved(userGenericModel);
                }
            });
        }

    }
}
