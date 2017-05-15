package com.example.admin.appclean.presentation.presenters.impl;

import android.app.Activity;
import android.view.ViewGroup;

import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;
import com.example.admin.appclean.domain.interactors.AddUserInteractor;
import com.example.admin.appclean.domain.interactors.PermissionsInteractor;
import com.example.admin.appclean.domain.interactors.impl.AddUserInteractorImpl;
import com.example.admin.appclean.domain.interactors.impl.PermissionsInteractorImpl;
import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.presentation.presenters.AbstractPresenter;
import com.example.admin.appclean.presentation.presenters.MainUserPresenter;
import com.example.admin.appclean.presentation.presenters.PermissionsPresenter;
import com.example.admin.appclean.utils.PermissionUtil.PermisionItem;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class PermissionsPresenterImpl extends AbstractPresenter implements PermissionsPresenter,
        PermissionsInteractor.Callback {

    private Activity activity;
    private MultiplePermissionsListener allPermissionsListener;
    private ViewGroup rootView;
    private PermissionsPresenter.View mView;

    public PermissionsPresenterImpl(Executor executor, MainThread mainThread, Activity activity,
                                    MultiplePermissionsListener allPermissionsListener,
                                    ViewGroup rootView, View view) {
        super(executor, mainThread);
        mView = view;
        this.activity = activity;
        this.allPermissionsListener = allPermissionsListener;
        this.rootView = rootView;

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
    public void onPermissionGrantedDenied(List<PermisionItem> permisionItems) {

        int permisosNegados = 0;
        boolean continuarFlujo = false;

        if (permisionItems != null) {

            for (PermisionItem item : permisionItems) {
                if (!item.isGranted()) permisosNegados++;
            }

            if (permisosNegados == 0) {
                continuarFlujo = true;
            }
        }

        mView.onPermissionsRequets(continuarFlujo);
    }

    @Override
    public void permissionsRequets() {
        PermissionsInteractor addUserInteractor = new PermissionsInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                activity,
                allPermissionsListener,
                rootView
        );

        addUserInteractor.execute();
    }
}
