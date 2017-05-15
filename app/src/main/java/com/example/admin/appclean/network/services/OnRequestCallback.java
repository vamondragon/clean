package com.example.admin.appclean.network.services;


import com.example.admin.appclean.network.model.GeneralInformation.GeneralInformationItem;

import java.util.List;

public class OnRequestCallback {


    private List<GeneralInformationItem> generalInformationItem;


    public OnRequestCallback(List<GeneralInformationItem> generalInformationItem) {
        this.generalInformationItem = generalInformationItem;
    }

    public Object getApiResponse() {
        return generalInformationItem;
    }


}