package com.example.admin.appclean.network.services;

import com.example.admin.appclean.network.model.GeneralInformation.GeneralInformation;
import com.example.admin.appclean.network.model.GeneralInformation.GeneralInformationItem;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * A REST interface describing how services will be synced with the backend.
 */
public interface NetworkInterfaces {

    String GENERAL_DATA_AUTOALERT = "/MBFSTA03/V01";

    @GET(GENERAL_DATA_AUTOALERT)
    Call<GeneralInformation> getDataAutoalert();


}