package com.example.admin.appclean.domain.interactors;


import com.example.admin.appclean.domain.interactors.base.Interactor;
import com.example.admin.appclean.utils.PermissionUtil.PermisionItem;

import java.util.List;

public interface PermissionsInteractor extends Interactor {

    interface Callback {
        void onPermissionGrantedDenied(List<PermisionItem> permisionItems);

    }
}
