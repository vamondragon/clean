package com.example.admin.appclean.presentation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.admin.appclean.R;
import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.presentation.presenters.MainUserPresenter;

import java.util.ArrayList;
import java.util.List;

public class ClientAdapter extends RecyclerSwipeAdapter<ClientAdapter.ClientViewHolder> implements MainUserPresenter.View {

    private String correoElectronico;
    private String personName;

    private Context mContext;
    public final MainUserPresenter.View mView;
    private List<UserGenericModel> userGenericModelList;

    public ClientAdapter(MainUserPresenter.View view, Context context) {
        mView = view;
        mContext = context;
        userGenericModelList = new ArrayList<>();
    }

    @Override
    public void showClients(List<UserGenericModel> userGenericModels) {

    }

    @Override
    public void onClicAddClient(UserGenericModel users) {

    }

    @Override
    public void onClientAdded() {

    }


    public static class ClientViewHolder extends RecyclerView.ViewHolder {

        SwipeLayout swipeLayout;
        TextView personName, personEmail, btnDelete, btnEdit;

        ClientViewHolder(View itemView) {
            super(itemView);
            personName = (TextView) itemView.findViewById(R.id.tv_name);
            personEmail = (TextView) itemView.findViewById(R.id.tv_correo_electronico);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            swipeLayout.setClickToClose(true);

            btnDelete = (TextView) itemView.findViewById(R.id.btDelete);
            btnEdit = (TextView) itemView.findViewById(R.id.btEdit);
        }
    }


    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ClientViewHolder clientViewHolder = new ClientViewHolder(v);
        return clientViewHolder;
    }


    public void addNewClient(@NonNull List<UserGenericModel> userList) {
        // clean up old data
        if (userGenericModelList != null) {
            userGenericModelList.clear();
        }
        userGenericModelList = userList;

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ClientViewHolder holder, final int position) {

        correoElectronico = userGenericModelList.get(position).getCorreoElectronico();
        personName = userGenericModelList.get(position).getFullName();

        holder.personName.setText(!personName.isEmpty() ? personName : "XXXXX YYYYY JJJJJ");
        holder.personEmail.setText(!correoElectronico.isEmpty() ? correoElectronico : "XXXXX@xxxx.xxx");

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }


    @Override
    public int getItemCount() {
        return userGenericModelList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
}