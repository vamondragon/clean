package com.example.admin.appclean.domain.interactors.impl;

import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;
import com.example.admin.appclean.domain.interactors.UpdateUserInteractor;
import com.example.admin.appclean.domain.interactors.base.AbstractInteractor;
import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.domain.repository.UserRepository;

import java.util.Date;

public class UpdateUserInteractorImpl extends AbstractInteractor implements UpdateUserInteractor {


    private UpdateUserInteractor.Callback mCallback;
    private UserRepository mUserRepository;

    //Valores originales
    private UserGenericModel mUpdatedUserGenericModel;

    //Valores que pudieron ser modificados
    private String mNombre;
    private String mApellidoPaterno;
    private String mApellidoMaterno;
    private String mCorreoElectronico;
    private String mPhone;
    private Date mDate;

    public UpdateUserInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                    Callback mCallback, UserRepository mUserRepository,
                                    String mNombre, String mApellidoPaterno,
                                    String mApellidoMaterno, String mCorreoElectronico, String mPhone, Date mDate,
                                    UserGenericModel updatedUserGenericModel) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mUserRepository = mUserRepository;
        this.mNombre = mNombre;
        this.mApellidoPaterno = mApellidoPaterno;
        this.mApellidoMaterno = mApellidoMaterno;
        this.mCorreoElectronico = mCorreoElectronico;
        this.mPhone = mPhone;
        this.mDate = mDate;
        this.mUpdatedUserGenericModel = updatedUserGenericModel;
    }


    @Override
    public void run() {
        long mUserId = mUpdatedUserGenericModel.getId();
        UserGenericModel userGenericModel = mUserRepository.getUserById(mUserId);


        userGenericModel = new UserGenericModel(mNombre, mApellidoPaterno, mApellidoMaterno, mCorreoElectronico, mPhone, mDate);
        mUserRepository.insert(userGenericModel);

        // notify on main thread that the update was successful
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onUserUpdated(mUpdatedUserGenericModel);
            }
        });

    }
}
