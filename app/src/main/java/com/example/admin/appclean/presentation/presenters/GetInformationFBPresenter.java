package com.example.admin.appclean.presentation.presenters;

import android.os.Bundle;

public interface GetInformationFBPresenter extends BasePresenter  {

    interface View  {
        //Elemento de la vista que lanza
        void onDataRetrieve(Bundle bFacebookData, String mensaje);
    }

    //Retorno del objeto
    void getInformationFB();

}
