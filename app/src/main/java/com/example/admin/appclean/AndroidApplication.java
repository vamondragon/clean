package com.example.admin.appclean;

import android.app.Application;

import com.example.admin.appclean.network.ServiceGenerator;
import com.example.admin.appclean.utils.Singleton;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;


public class AndroidApplication extends Application {

    AndroidApplication mAndroidApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        //Configuracion de la base de datos
        Realm.init(this);
        Singleton.getInstance().setRealm(Realm.getDefaultInstance());

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);

        //Configuracion nombre de la aplicacion
        String appName = this.getString(this.getApplicationInfo().labelRes);
        Singleton.getInstance().setAppName(appName);


        if (BuildConfig.DEBUG_MODE) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
        }
    }
}
