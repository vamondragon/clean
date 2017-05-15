/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.admin.appclean.utils.PermissionUtil;

import com.example.admin.appclean.domain.interactors.PermissionsInteractor;
import com.example.admin.appclean.presentation.ui.activities.SplashScreenActivity;
import com.example.admin.appclean.utils.Constants;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

public class MultiplePermissionListener implements MultiplePermissionsListener {

    private List<PermisionItem> permisionItemList;
    private PermissionsInteractor.Callback mCallback;

    public MultiplePermissionListener(PermissionsInteractor.Callback mCallback) {
        this.mCallback = mCallback;
        permisionItemList = new ArrayList<>();
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {

        for (PermissionGrantedResponse response : report.getGrantedPermissionResponses()) {
            permisionItemList.add(new PermisionItem(response.getPermissionName(), true));
        }

        for (PermissionDeniedResponse response : report.getDeniedPermissionResponses()) {
            permisionItemList.add(new PermisionItem(response.getPermissionName(), false));
        }

        mCallback.onPermissionGrantedDenied(permisionItemList);

    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                   PermissionToken token) {
        //activity.showPermissionRationale(token);
    }
}
