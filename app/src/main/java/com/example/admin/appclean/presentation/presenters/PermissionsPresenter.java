package com.example.admin.appclean.presentation.presenters;


public interface PermissionsPresenter extends BasePresenter {

    interface View {
        void onPermissionsRequets(boolean continuaFlujo);
    }

    void permissionsRequets();
}
