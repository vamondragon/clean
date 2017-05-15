package com.example.admin.appclean.network.model.GeneralInformation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GeneralInformation {


    @SerializedName("listGeneralInformation")
    @Expose
    private List<GeneralInformationItem> listGeneralInformation = null;

    public List<GeneralInformationItem> getListGeneralInformation() {
        return listGeneralInformation;
    }

    public void setListGeneralInformation(List<GeneralInformationItem> listGeneralInformation) {
        this.listGeneralInformation = listGeneralInformation;
    }

}
