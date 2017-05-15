package com.example.admin.appclean.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;
import com.example.admin.appclean.R;
import com.example.admin.appclean.domain.impl.ThreadExecutor;
import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.network.model.ApiError;
import com.example.admin.appclean.network.services.OnRequestCallback;
import com.example.admin.appclean.network.services.ServiceManager;
import com.example.admin.appclean.presentation.adapters.ClientAdapter;
import com.example.admin.appclean.presentation.presenters.MainUserPresenter;
import com.example.admin.appclean.presentation.presenters.impl.MainUserPresenterImpl;
import com.example.admin.appclean.presentation.ui.activities.base.BaseActivity;
import com.example.admin.appclean.storage.UserRepositoryImpl;
import com.example.admin.appclean.threading.MainThreadImpl;
import com.example.admin.appclean.utils.LogUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements MainUserPresenter.View {

    private MainUserPresenterImpl mMainPresenter;
    private ClientAdapter mAdapter;


    private ServiceManager sManager;

    @BindView(R.id.client_recycle_view)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
    }

    @Override
    public void initElements() {

        this.getSupportActionBar().setTitle("Clean Architecture");

        mMainPresenter = new MainUserPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl(this)
        );

        mAdapter = new ClientAdapter(this, this);

        // setup recycler view
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adapter:
        mAdapter.setMode(Attributes.Mode.Single);

        mRecyclerView.setAdapter(mAdapter);


        sManager = new ServiceManager();
        pruebasServicios();
    }

    private void pruebasServicios() {
        sManager.consultarInformacionGeneral();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_user:
                startActivity(
                        new Intent(MainActivity.this, AddUserActivity.class));

                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMainPresenter != null) {
            mMainPresenter.resume();
            //mMainPresenter.getAllClients();
        }
    }

    @Override
    public void showClients(List<UserGenericModel> userGenericModels) {

        if (userGenericModels != null) {
            mAdapter.addNewClient(userGenericModels);
        }
    }

    @Override
    public void onClicAddClient(UserGenericModel cost) {
    }

    @Override
    public void onClientAdded() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OnRequestCallback event) {
        String json = new Gson().toJson(event.getApiResponse());
        LogUtil.Info("Descargando con EventBus:   " + json);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorService(ApiError apiError) {
        Toast.makeText(this, apiError.message(), Toast.LENGTH_SHORT).show();
    }


}
