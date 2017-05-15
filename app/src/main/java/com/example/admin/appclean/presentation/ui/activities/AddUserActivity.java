package com.example.admin.appclean.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.appclean.R;
import com.example.admin.appclean.domain.impl.ThreadExecutor;
import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.presentation.presenters.MainUserPresenter;
import com.example.admin.appclean.presentation.presenters.impl.MainUserPresenterImpl;
import com.example.admin.appclean.presentation.ui.activities.base.BaseActivity;
import com.example.admin.appclean.storage.UserRepositoryImpl;
import com.example.admin.appclean.threading.MainThreadImpl;
import com.example.admin.appclean.utils.Common;
import com.example.admin.appclean.utils.Constants;
import com.example.admin.appclean.utils.Validaciones;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddUserActivity extends BaseActivity implements MainUserPresenter.View {

    @BindView(R.id.nombre)
    EditText nombre;

    @BindView(R.id.apellid_paterno)
    EditText apellidoPaterno;

    @BindView(R.id.apellido_materno)
    EditText apellidoMaterno;

    @BindView(R.id.telefono_contacto)
    EditText telefonoContacto;

    @BindView(R.id.correo_electronico)
    EditText correoElectronico;

    Validaciones validaciones;
    Common common;
    ActionBar mToolbar;

    private MainUserPresenterImpl mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);


        initElements();

        Log.e(Constants.TAG, "onCreate");
    }

    @Override
    public void initElements() {

        this.getSupportActionBar().setTitle("Agregar Cliente");

        validaciones = new Validaciones();
        common = new Common(this);

        Log.e(Constants.TAG, "initElements");
        mMainPresenter = new MainUserPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl(this)
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_close_add_user:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.resume();
    }


    @OnClick(R.id.register_button)
    public void registrarNuevo() {

        String mNombre = validaciones.getValorString(nombre);
        String mApellidoPaterno = validaciones.getValorString(apellidoPaterno);
        String mApellidoMaterno = validaciones.getValorString(apellidoMaterno);
        String mCorreoElectronico = validaciones.getValorString(correoElectronico);
        String mPhone = validaciones.getValorString(telefonoContacto);
        Date mDate = common.getCurrentDate();

        if (!mNombre.isEmpty()
                && !mApellidoPaterno.isEmpty()
                && !mApellidoMaterno.isEmpty()
                && !mCorreoElectronico.isEmpty()
                && !mPhone.isEmpty()) {

            onClicAddClient(new UserGenericModel(mNombre, mApellidoPaterno, mApellidoMaterno, mCorreoElectronico, mPhone, mDate));

        } else {
            Toast.makeText(this, "Validar campos", Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void showClients(List<UserGenericModel> userGenericModels) {

    }

    @Override
    public void onClicAddClient(UserGenericModel userGenericModel) {
        mMainPresenter.addNewClient(userGenericModel);
    }

    @Override
    public void onClientAdded() {
        Toast.makeText(this, "El cliente se inserto correctamente", Toast.LENGTH_LONG).show();
        onBackPressed();

    }
}
