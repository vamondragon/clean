package com.example.admin.appclean.domain.interactors.impl;

import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;
import com.example.admin.appclean.domain.interactors.DeleteUserInteractor;
import com.example.admin.appclean.domain.interactors.base.AbstractInteractor;
import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.domain.repository.UserRepository;

public class DeleteUserInteractorImpl extends AbstractInteractor implements DeleteUserInteractor {

    private long mId;
    private DeleteUserInteractor.Callback mCallback;
    private UserRepository mUserRepository;

    public DeleteUserInteractorImpl(Executor threadExecutor, MainThread mainThread, long userId,
                                    DeleteUserInteractor.Callback callback, UserRepository userRepository) {
        super(threadExecutor, mainThread);
        mId = userId;
        mCallback = callback;
        mUserRepository = userRepository;
    }


    @Override
    public void run() {

        final UserGenericModel userGenericModel = mUserRepository.getUserById(mId);

        // we didn't find the cost we were looking for
        if (userGenericModel == null) {

            // notify this on the main thread
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.noUserFound();
                }
            });

            // we found it!
        } else {

            // send it on the main thread
            mUserRepository.delete(userGenericModel);
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onUserDelete(userGenericModel);
                }
            });
        }

    }
}
