package com.example.admin.appclean.utils;

import com.example.admin.appclean.network.ServiceGenerator;

import io.realm.Realm;

public class Singleton {

    private static Singleton instance;

    private Realm realm;
    private String appName;
    private ServiceGenerator serviceGenerator;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public ServiceGenerator getServiceGenerator() {
        return serviceGenerator;
    }

    public void setServiceGenerator(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }
}
