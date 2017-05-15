package com.example.admin.appclean.presentation.ui.activities.base;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.appclean.network.ServiceGenerator;
import com.example.admin.appclean.network.services.NetworkInterfaces;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    private NetworkInterfaces networkInterfaces;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        networkInterfaces = ServiceGenerator.createService(NetworkInterfaces.class);
        injectViews();
    }

    private void injectViews() {
        ButterKnife.bind(this);
    }

    public abstract void initElements();

    @Override
    public void onStart() {
        super.onStart();
        EventBusRegister();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBusRegister();
    }

    private void EventBusRegister() {
        try {
            EventBus.getDefault().register(this);
        } catch (EventBusException e) {
        }
    }

    public NetworkInterfaces getInterfaces() {
        return networkInterfaces;
    }

    public Context getContext() {
        return this;
    }

}

