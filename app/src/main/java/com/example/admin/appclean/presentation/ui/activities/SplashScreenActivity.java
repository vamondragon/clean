package com.example.admin.appclean.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.example.admin.appclean.R;
import com.example.admin.appclean.domain.impl.ThreadExecutor;
import com.example.admin.appclean.presentation.presenters.GetInformationFBPresenter;
import com.example.admin.appclean.presentation.presenters.impl.GetInformationFBPresenterImpl;
import com.example.admin.appclean.storage.UserRepositoryImpl;
import com.example.admin.appclean.threading.MainThreadImpl;
import com.example.admin.appclean.utils.PreferencesUtil;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity implements
        GetInformationFBPresenter.View {

    private long splashDelay = 2000;
    private Context context;
    private PreferencesUtil preferencesUtil;

    private GetInformationFBPresenter getInformationFBPresenter;
    private CallbackManager callbackManager;

    @BindView(R.id.login_button)
    LoginButton loginButton;


    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getInformationFBPresenter.resume();
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        if (!preferencesUtil.getStringPrefrences("authToken").isEmpty() &&
                !preferencesUtil.getStringPrefrences("userID").isEmpty()) {
            loginButton.setVisibility(View.INVISIBLE);
        } else {
            loginButton.setReadPermissions("email");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        context = this;
        callbackManager = CallbackManager.Factory.create();

        preferencesUtil = new PreferencesUtil(this, "InformationFB");

        if (!preferencesUtil.getStringPrefrences("authToken").isEmpty() &&
                !preferencesUtil.getStringPrefrences("userID").isEmpty()) {
            loginButton.setVisibility(View.INVISIBLE);
        } else {
            loginButton.setReadPermissions("email");
        }

        {//Bloque que invoca los Presentadores para la validacion de permisos y login con FB
            getInformationFBPresenter = new GetInformationFBPresenterImpl(
                    ThreadExecutor.getInstance(),
                    MainThreadImpl.getInstance(),
                    this,
                    new UserRepositoryImpl(this),
                    loginButton,
                    callbackManager);
            getInformationFBPresenter.getInformationFB();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void onDataRetrieve(Bundle bFacebookData, String mensaje) {

        //Si Bundle no es nulo y mensaje si, no importa no hay error que mostrar
        if (bFacebookData != null && mensaje == null) {
            preferencesUtil.setStringPrefrences("authToken", bFacebookData.getString("authToken"));
            preferencesUtil.setStringPrefrences("userID", bFacebookData.getString("userID"));

            Intent intent = new Intent(context, FormularioRegistro.class);
            intent.putExtras(bFacebookData);
            startActivity(intent);
        }

        // Si el Bundle es nulo y el mensaje no, ocurrio un error se debe mostrar la imagen
        if (bFacebookData == null && mensaje != null) {
            System.out.println("bFacebookData error: " + mensaje);
        }
    }
}
