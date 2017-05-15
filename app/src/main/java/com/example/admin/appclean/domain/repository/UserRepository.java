package com.example.admin.appclean.domain.repository;

import android.os.Bundle;

import com.example.admin.appclean.models.UserGenericModel;

import org.json.JSONObject;

import java.util.List;

public interface UserRepository {

    int getNextKey();

    void insert(UserGenericModel userGenericModel);

    void update(UserGenericModel userGenericModel);

    UserGenericModel getUserById(long id);

    List getAllUsers();

    void delete(UserGenericModel userGenericModel);

    Bundle getFacebookData(JSONObject object);
}
