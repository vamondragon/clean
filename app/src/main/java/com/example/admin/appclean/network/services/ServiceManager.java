package com.example.admin.appclean.network.services;

import com.example.admin.appclean.network.ServiceGenerator;
import com.example.admin.appclean.network.model.ApiError;
import com.example.admin.appclean.network.model.GeneralInformation.GeneralInformation;
import com.example.admin.appclean.utils.ErrorUtil;
import com.example.admin.appclean.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceManager {

    private NetworkInterfaces networkInterfaces;

    public ServiceManager() {
        networkInterfaces = ServiceGenerator.createService(NetworkInterfaces.class);
    }

    public void consultarInformacionGeneral() {

        networkInterfaces.getDataAutoalert().enqueue(new Callback<GeneralInformation>() {

            public void onResponse(Call<GeneralInformation> call, Response<GeneralInformation> response) {

                System.out.println("response:    "   + response.body().toString());

                if (response.isSuccessful()) {
                    EventBus.getDefault().post(new OnRequestCallback(response.body().getListGeneralInformation()));
                } else {
                    ApiError error = ErrorUtil.parseError(response);
                    EventBus.getDefault().post(error);
                }
            }

            public void onFailure(Call<GeneralInformation> call, Throwable t) {
                LogUtil.Info("Ocurrio un error en la llamada al servicio: " + t.toString());
            }
        });
    }
}
