package com.example.admin.appclean.domain.interactors.impl;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.appclean.R;
import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;
import com.example.admin.appclean.domain.interactors.PermissionsInteractor;
import com.example.admin.appclean.domain.interactors.base.AbstractInteractor;
import com.example.admin.appclean.utils.Common;
import com.example.admin.appclean.utils.PermissionUtil.ErrorListener;
import com.example.admin.appclean.utils.PermissionUtil.MultiplePermissionListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;


public class PermissionsInteractorImpl extends AbstractInteractor implements PermissionsInteractor {

    private PermissionsInteractor.Callback mCallback;
    private MultiplePermissionsListener allPermissionsListener;
    private PermissionRequestErrorListener errorListener;
    private Activity activity;
    private ViewGroup rootView;
    private Common common;

    public PermissionsInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                     PermissionsInteractor.Callback mCallback,
                                     Activity activity,
                                     MultiplePermissionsListener allPermissionsListener,
                                     ViewGroup rootView) {

        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.activity = activity;
        this.allPermissionsListener = allPermissionsListener;
        this.rootView = rootView;
        common = new Common(activity);
    }


    @Override
    public void run() {

        MultiplePermissionsListener feedbackViewMultiplePermissionListener =
                new MultiplePermissionListener(mCallback);

        allPermissionsListener =
                new CompositeMultiplePermissionsListener(feedbackViewMultiplePermissionListener,
                        SnackbarOnAnyDeniedMultiplePermissionsListener.Builder.with(rootView,
                                R.string.advertencia_permisos)
                                .withDuration(Snackbar.LENGTH_INDEFINITE)
                                .withButton("Aceptar", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        activity.finish();
                                    }
                                })
                                .build());

        errorListener = new ErrorListener();


        // notify on main thread that the update was successful
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                Dexter.withActivity(activity)
                        .withPermissions(common.getPermissions())
                        .withListener(allPermissionsListener)
                        .withErrorListener(errorListener)
                        .check();

            }
        });
    }
}
