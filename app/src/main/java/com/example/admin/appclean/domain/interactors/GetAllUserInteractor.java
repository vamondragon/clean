package com.example.admin.appclean.domain.interactors;

import com.example.admin.appclean.domain.interactors.base.Interactor;
import com.example.admin.appclean.models.UserGenericModel;

import java.util.List;

public interface GetAllUserInteractor extends Interactor {

    interface Callback {
        void onUserRetrieved(List<UserGenericModel> userGenericModelList);
    }

}
