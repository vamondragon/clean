package com.example.admin.appclean.presentation.presenters;

import com.example.admin.appclean.models.UserGenericModel;

import java.util.List;

public interface MainUserPresenter extends BasePresenter {

    interface View  {

        void showClients(List<UserGenericModel> userGenericModels);

        void onClicAddClient(UserGenericModel users);

        void onClientAdded();


    }

    void addNewClient(UserGenericModel cost);

    void getAllClients();


}
