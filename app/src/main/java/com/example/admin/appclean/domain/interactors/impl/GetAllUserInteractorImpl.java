package com.example.admin.appclean.domain.interactors.impl;

import com.example.admin.appclean.domain.interactors.GetAllUserInteractor;
import com.example.admin.appclean.domain.interactors.base.AbstractInteractor;
import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.domain.repository.UserRepository;
import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GetAllUserInteractorImpl extends AbstractInteractor implements GetAllUserInteractor {

    private GetAllUserInteractor.Callback mCallback;
    private UserRepository mUserRepository;


    //Compara los resultados para ordenarlos a la hora de responder al view
    private Comparator<UserGenericModel> mUserComparator = new Comparator<UserGenericModel>() {
        @Override
        public int compare(UserGenericModel lhs, UserGenericModel rhs) {

            if (lhs.getDate().before(rhs.getDate()))
                return 1;

            if (rhs.getDate().before(lhs.getDate()))
                return -1;

            return 0;
        }
    };

    public GetAllUserInteractorImpl(Executor threadExecutor, MainThread mainThread, UserRepository userRepository,
                                    Callback callback) {
        super(threadExecutor, mainThread);

        if (userRepository == null || callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }

        mUserRepository = userRepository;
        mCallback = callback;
    }


    @Override
    public void run() {

        // retrieve the user from the database
        final List<UserGenericModel> userGenericModelList = mUserRepository.getAllUsers();


        if (userGenericModelList != null) {
            // sort them so the most recent cost items come first, and oldest comes last
            Collections.sort(userGenericModelList, mUserComparator);
        }


        // Show costs on the main thread
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onUserRetrieved(userGenericModelList);
            }
        });
    }
}
