package com.example.admin.appclean.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.View;

import com.example.admin.appclean.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Common {

    private static final long MIN_CLICK_INTERVAL = 2 * 1000;
    Context context;

    public Common(Context context) {
        this.context = context;
    }

    public static Drawable tintMyDrawable(Drawable drawable, int color) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    public String getAppVersionCode() {
        PackageInfo pInfo = null;
        String version = "";

        try {
            pInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
        }

        version = pInfo.versionName;


        if (BuildConfig.DEBUG_MODE) {
            version = version + " " + "D";
        }

        return version;
    }

    public List<String> getPermissions() {
        List<String> permissionLis = new ArrayList<>();

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);

            if (info.requestedPermissions != null) {
                for (String permission : info.requestedPermissions) {
                    permissionLis.add(permission);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.Error("Error al obtener los permisos del archivo manifest: " + e.toString());
        }

        return permissionLis;
    }

    public void preventDoubleClick(final View view) {
        view.setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, MIN_CLICK_INTERVAL);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    public boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public String getCurrentDateFormat(String format) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return (sdf.format(cal.getTime()));
    }

    public Date getCurrentDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return (cal.getTime());
    }

    public String getCurrentTimeFormat() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return cal.getTime().toString();
    }


}
