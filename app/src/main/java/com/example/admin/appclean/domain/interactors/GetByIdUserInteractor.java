package com.example.admin.appclean.domain.interactors;

import com.example.admin.appclean.domain.interactors.base.Interactor;
import com.example.admin.appclean.models.UserGenericModel;

public interface GetByIdUserInteractor extends Interactor {

    interface Callback {
        void onUserRetrieved(UserGenericModel userGenericModel);
        void noUserFound();
    }
}
