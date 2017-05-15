package com.example.admin.appclean.storage.converters;

import com.example.admin.appclean.models.UserGenericModel;
import com.example.admin.appclean.storage.model.UserStorageModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StorageModelConverter {

    public static UserStorageModel convertToStorageModel(UserGenericModel userGenericModel) {
        UserStorageModel userStorageModelConvert = new UserStorageModel();
        userStorageModelConvert.setId(userGenericModel.getId());
        userStorageModelConvert.setNombre(userGenericModel.getNombre());
        userStorageModelConvert.setApellidoPaterno(userGenericModel.getApellidoPaterno());
        userStorageModelConvert.setApellidoMaterno(userGenericModel.getApellidoMaterno());
        userStorageModelConvert.setDate(userGenericModel.getDate());
        userStorageModelConvert.setCorreoElectronico(userGenericModel.getCorreoElectronico());
        userStorageModelConvert.setTelefono(userGenericModel.getTelefono());
        userStorageModelConvert.setId(userGenericModel.getId());

        return userStorageModelConvert;
    }

    public static UserGenericModel convertToDomainModel(UserStorageModel userStorageModel) {

        String nombre = userStorageModel.getNombre();
        String apellidoPaterno = userStorageModel.getApellidoPaterno();
        String aApellidoMaterno = userStorageModel.getApellidoMaterno();
        String password = userStorageModel.getTelefono();
        String phone = userStorageModel.getCorreoElectronico();
        Date date = userStorageModel.getDate();
        long id = userStorageModel.getId();

        UserGenericModel userGenericModelConvert = new UserGenericModel(
                id, nombre, apellidoPaterno, aApellidoMaterno, password, phone, date);

        return userGenericModelConvert;
    }


    public static List<UserGenericModel> convertListToDomainModel(List<UserStorageModel> userStorageModel) {
        List<UserGenericModel> convertedUserGenericModel = null;

        if (userStorageModel != null && userStorageModel.size() > 0) {
            convertedUserGenericModel = new ArrayList<>();
            for (UserStorageModel userStorageModelEach : userStorageModel) {
                System.out.println("userStorageModelEach: " + userStorageModelEach.getNombre());
                convertedUserGenericModel.add(convertToDomainModel(userStorageModelEach));
            }
        }

        return convertedUserGenericModel;
    }


    public static List<UserStorageModel> convertListToStorageModel(List<UserGenericModel> userGenericModelList) {
        List<UserStorageModel> convertedUserStorageModel = null;

        if (userGenericModelList != null && userGenericModelList.size() > 0) {
            convertedUserStorageModel = new ArrayList<>();

            for (UserGenericModel userGenericModelEach : userGenericModelList) {
                convertedUserStorageModel.add(convertToStorageModel(userGenericModelEach));
            }

            // cleanup
            userGenericModelList.clear();
            userGenericModelList = null;
        }

        return convertedUserStorageModel;
    }
}
