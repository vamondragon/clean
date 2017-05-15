package com.example.admin.appclean.utils;

import com.example.admin.appclean.network.ServiceGenerator;
import com.example.admin.appclean.network.model.ApiError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtil {

    public static ApiError parseError(Response<?> response) {

        Converter<ResponseBody, ApiError> converter =
                ServiceGenerator.retrofit()
                        .responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            LogUtil.Error("ErrorUtil: " + e.toString() + "");
            error = new ApiError(-1, Constants.GENERIC_ERROR);
        }
        return error;
    }
}
