package com.example.admin.appclean.presentation.ui.activities;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.admin.appclean.R;
import com.example.admin.appclean.domain.impl.ThreadExecutor;
import com.example.admin.appclean.presentation.adapters.OnboardAdapter;
import com.example.admin.appclean.presentation.presenters.PermissionsPresenter;
import com.example.admin.appclean.presentation.presenters.impl.PermissionsPresenterImpl;
import com.example.admin.appclean.presentation.ui.activities.base.BaseActivity;
import com.example.admin.appclean.threading.MainThreadImpl;
import com.example.admin.appclean.utils.Common;
import com.example.admin.appclean.utils.Enum;
import com.example.admin.appclean.utils.PreferencesUtil;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.rd.Orientation;
import com.rd.PageIndicatorView;

import butterknife.BindView;
import butterknife.OnClick;

public class Onboarding extends BaseActivity implements ViewPager.OnPageChangeListener
        , PermissionsPresenter.View {

    int color1, color2, color3;
    int[] colorList;

    ArgbEvaluator evaluator = new ArgbEvaluator();

    private OnboardAdapter mSectionsPagerAdapter;

    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.intro_btn_next)
    ImageButton mNextBtn;

    @BindView(R.id.intro_btn_skip)
    Button mSkipBtn;

    @BindView(R.id.intro_btn_finish)
    Button mFinishBtn;

    @BindView(R.id.pageindicatorview)
    PageIndicatorView indicatorView;


    int page = 0;


    //Variables para el manejo de los permisos.
    private PermissionsPresenter mMainPresenter;
    private MultiplePermissionsListener allPermissionsListener;

    //Almacenamiento de la Preferencias.
    private PreferencesUtil preferencesUtil;

    //
    private Boolean isTutorialEnd, isPermissionAcept;

    //View principal para atachar el snakbar
    @BindView(android.R.id.content)
    ViewGroup rootView;


    //Control del flujo de los elementos
    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.resume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferencesUtil = new PreferencesUtil(this, this.getResources().getString(R.string.preference_name));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_trans80));
        }

        setContentView(R.layout.activity_onboarding);

        initElements();
    }

    @Override
    public void initElements() {

        color1 = ContextCompat.getColor(getContext(), R.color.cyan);
        color2 = ContextCompat.getColor(getContext(), R.color.orange);
        color3 = ContextCompat.getColor(getContext(), R.color.green);

        colorList = new int[]{color1, color2, color3};

        mSectionsPagerAdapter = new OnboardAdapter(getSupportFragmentManager());

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
            mNextBtn.setImageDrawable(
                    Common.tintMyDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_right_24dp), Color.WHITE)
            );

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        indicatorView.setViewPager(mViewPager);
        indicatorView.setOrientation(Orientation.HORIZONTAL);
        indicatorView.setRadius(5);

        mViewPager.setCurrentItem(page);
        mViewPager.addOnPageChangeListener(this);


        {//Bloque que invoca los Presentadores para la validacion de permisos y login con FB
            mMainPresenter = new PermissionsPresenterImpl(
                    ThreadExecutor.getInstance(),
                    MainThreadImpl.getInstance(),
                    this,
                    allPermissionsListener,
                    rootView,
                    this
            );
            mMainPresenter.permissionsRequets();
        }

        isTutorialEnd = preferencesUtil.getBooleanPrefrences(Enum.ConfiguracionApp.TERMINO_TUTORIAL.toString());
        isPermissionAcept = preferencesUtil.getBooleanPrefrences(Enum.ConfiguracionApp.ACEPTO_PERMISOS.toString());

        if (isTutorialEnd && isPermissionAcept) iniciarActividad(this, MainActivity.class);
    }

    @Override
    public void onPermissionsRequets(boolean continuarFlujo) {
        if (continuarFlujo) {
            preferencesUtil.setBooleanPrefrences(Enum.ConfiguracionApp.ACEPTO_PERMISOS.toString(), true);
        }
    }

    @OnClick(R.id.intro_btn_next)
    public void siguientePagina(View view) {
        page += 1;
        mViewPager.setCurrentItem(page, true);
    }

    @OnClick(R.id.intro_btn_skip)
    public void saltarTutorial(View view) {
        preferencesUtil.setBooleanPrefrences(Enum.ConfiguracionApp.TERMINO_TUTORIAL.toString(), true);
        iniciarActividad(this, MainActivity.class);
    }

    @OnClick(R.id.intro_btn_finish)
    public void terminarTutorial(View view) {
        preferencesUtil.setBooleanPrefrences(Enum.ConfiguracionApp.TERMINO_TUTORIAL.toString(), true);
        iniciarActividad(this, MainActivity.class);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int colorUpdate = (Integer) evaluator.evaluate(positionOffset, colorList[position], colorList[position == 2 ? position : position + 1]);
        mViewPager.setBackgroundColor(colorUpdate);
    }

    @Override
    public void onPageSelected(int position) {
        page = position;
        switch (position) {
            case 0:
                mViewPager.setBackgroundColor(color1);
                break;
            case 1:
                mViewPager.setBackgroundColor(color2);
                break;
            case 2:
                mViewPager.setBackgroundColor(color3);
                break;
        }
        mNextBtn.setVisibility(position == 2 ? View.GONE : View.VISIBLE);
        mFinishBtn.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private void iniciarActividad(Context context, Class<?> intentClass) {
        startActivity(new Intent(context, intentClass));
        finish();
    }
}
