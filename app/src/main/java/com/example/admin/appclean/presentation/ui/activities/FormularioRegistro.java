package com.example.admin.appclean.presentation.ui.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.appclean.R;
import com.example.admin.appclean.presentation.transformations.CropCircleTransformation;
import com.example.admin.appclean.presentation.ui.activities.base.BaseActivity;

import butterknife.BindView;


public class FormularioRegistro extends BaseActivity {

    private String email = "";
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String profilePic = null;

    @BindView(R.id.nombre_fb)
    EditText nombres;

    @BindView(R.id.apellidos_fb)
    EditText apellidos;

    @BindView(R.id.correo_electronico_fb)
    EditText correoElectronico;

    @BindView(R.id.image_profile)
    ImageView imagenPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_registro);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            email = extras.getString("email") != null ? extras.getString("email") : "";
            firstName = extras.getString("first_name") != null ? extras.getString("first_name") : "";
            middleName = extras.getString("middle_name") != null ? extras.getString("middle_name") : "";
            lastName = extras.getString("last_name") != null ? extras.getString("last_name") : "";
            profilePic = extras.getString("profile_pic") != null ? extras.getString("profile_pic") : null;
        }

        initElements();
    }

    @Override
    public void initElements() {
        this.getSupportActionBar().setTitle("Registro de Clientes");


        nombres.setText(firstName + " " + middleName);
        apellidos.setText(lastName);
        correoElectronico.setText(email);

        Glide.with(this).load(profilePic)
                .placeholder(R.drawable.background_splash)
                .error(R.drawable.background_splash)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(imagenPerfil);

    }
}
