package com.example.admin.appclean.domain.interactors;

import com.example.admin.appclean.domain.interactors.base.Interactor;
import com.example.admin.appclean.models.UserGenericModel;

public interface UpdateUserInteractor extends Interactor {
    interface Callback {
        void onUserUpdated(UserGenericModel userGenericModel);
    }
}
