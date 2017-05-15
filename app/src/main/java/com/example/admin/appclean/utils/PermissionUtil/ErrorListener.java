package com.example.admin.appclean.utils.PermissionUtil;

import com.example.admin.appclean.utils.LogUtil;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequestErrorListener;

public class ErrorListener implements PermissionRequestErrorListener {
    @Override
    public void onError(DexterError error) {
        LogUtil.Error("Error al solicitar los permisos: ", error.toString());
    }
}