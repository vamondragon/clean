package com.example.admin.appclean.storage;

import android.content.Context;
import android.os.Bundle;

import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.domain.repository.UserRepository;
import com.example.admin.appclean.storage.converters.StorageModelConverter;
import com.example.admin.appclean.storage.model.UserStorageModel;
import com.example.admin.appclean.utils.Singleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserRepositoryImpl implements UserRepository {

    private Context context;
    private List<UserGenericModel> userGenericModels;
    private Realm realm;

    public UserRepositoryImpl(Context context) {
        this.context = context;

    }

    @Override
    public void insert(final UserGenericModel userGenericModel) {

        int ultimoID = getNextKey();

        UserStorageModel dbItem
                = StorageModelConverter.convertToStorageModel(userGenericModel);

        dbItem.setId(ultimoID);

        if (Singleton.getInstance().getRealm() == null) {
            Realm.init(context);
            realm = Realm.getDefaultInstance();
        } else {
            realm = Singleton.getInstance().getRealm();
        }

        realm.beginTransaction();
        realm.insertOrUpdate(dbItem);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public int getNextKey() {
        try {

            if (Singleton.getInstance().getRealm() == null) {
                Realm.init(context);
                realm = Realm.getDefaultInstance();
            } else {
                realm = Singleton.getInstance().getRealm();
            }

            return realm.where(UserStorageModel.class).max("id").intValue() + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void update(UserGenericModel userGenericModel) {
    }

    @Override
    public UserGenericModel getUserById(long id) {
        return null;
    }

    @Override
    public List<UserGenericModel> getAllUsers() {

        if (Singleton.getInstance().getRealm() == null) {
            Realm.init(context);
            realm = Realm.getDefaultInstance();
        } else {
            realm = Singleton.getInstance().getRealm();
        }

        RealmResults<UserStorageModel> userStorageModelRealmResults = realm.where(UserStorageModel.class).findAll();

        try {
            userGenericModels = StorageModelConverter.convertListToDomainModel(userStorageModelRealmResults);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userGenericModels;
    }

    @Override
    public void delete(UserGenericModel userGenericModel) {

    }

    @Override
    public Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();

        try {

            String id = object.getString("id");

            URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
            bundle.putString("profile_pic", profile_pic.toString());

            bundle.putString("idFacebook", id);

            if (object.has("email"))
                bundle.putString("email", object.getString("email"));

            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));

            if (object.has("middle_name"))
                bundle.putString("middle_name", object.getString("middle_name"));

            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));

            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));

        } catch (JSONException e) {
            bundle.putString("error", "JSONException: " + e.toString());
        } catch (MalformedURLException e) {
            bundle.putString("error", "MalformedURLException: " + e.toString());
        }

        return bundle;
    }
}
