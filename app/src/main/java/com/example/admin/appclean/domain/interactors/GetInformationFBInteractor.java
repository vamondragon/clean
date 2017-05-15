package com.example.admin.appclean.domain.interactors;

import android.content.Intent;
import android.os.Bundle;

import com.example.admin.appclean.domain.interactors.base.Interactor;

public interface GetInformationFBInteractor extends Interactor {

    interface Callback {
        void onRetrieveInformation(Bundle bFacebookData, String mensaje);
    }

}
