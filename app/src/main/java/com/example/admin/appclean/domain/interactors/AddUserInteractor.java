package com.example.admin.appclean.domain.interactors;

import com.example.admin.appclean.domain.interactors.base.Interactor;

public interface AddUserInteractor extends Interactor {

    interface Callback {
        void onUserAdded();
    }
}
