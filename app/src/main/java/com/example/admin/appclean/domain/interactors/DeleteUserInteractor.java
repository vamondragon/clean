package com.example.admin.appclean.domain.interactors;

import com.example.admin.appclean.domain.interactors.base.Interactor;
import com.example.admin.appclean.models.UserGenericModel;

public interface DeleteUserInteractor extends Interactor {

    interface Callback {
        void onUserDelete(UserGenericModel userGenericModel);
        void noUserFound();
    }

}
