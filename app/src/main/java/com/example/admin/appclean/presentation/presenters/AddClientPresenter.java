package com.example.admin.appclean.presentation.presenters;

import java.util.Date;

public interface AddClientPresenter extends BasePresenter {

    interface View  {
        void onClientRetrieveData();
    }

    void addNewClient(String nombre, String apellidoPaterno, String aApellidoMaterno, String password, String phone, Date date);

}
