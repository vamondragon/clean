package com.example.admin.appclean.domain.interactors.impl;

import android.os.Bundle;

import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;
import com.example.admin.appclean.domain.interactors.GetInformationFBInteractor;
import com.example.admin.appclean.domain.interactors.base.AbstractInteractor;
import com.example.admin.appclean.domain.repository.UserRepository;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class GetInformationFBInteractorImpl extends AbstractInteractor implements GetInformationFBInteractor {

    private GetInformationFBInteractorImpl.Callback mCallback;
    private CallbackManager callbackManager;
    private UserRepository mUserRepository;
    private LoginButton loginButton;

    public GetInformationFBInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                          UserRepository userRepository,
                                          LoginButton loginButton,
                                          CallbackManager callbackManager,
                                          Callback mCallback) {
        super(threadExecutor, mainThread);
        this.callbackManager = callbackManager;
        this.loginButton = loginButton;
        this.mUserRepository = userRepository;
        this.mCallback = mCallback;
    }


    @Override
    public void run() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {

                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                // Get facebook data from login
                                Bundle bFacebookData = mUserRepository.getFacebookData(object);

                                if (bFacebookData != null) {
                                    if (bFacebookData.getString("error") == null) {
                                        bFacebookData.putString("authToken", loginResult.getAccessToken().getToken());
                                        bFacebookData.putString("userID", loginResult.getAccessToken().getUserId());
                                        mCallback.onRetrieveInformation(bFacebookData, null);
                                    } else {
                                        mCallback.onRetrieveInformation(null, bFacebookData.getString("error"));
                                    }
                                }
                            }
                        });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, email, first_name, middle_name, last_name, gender"); // Parámetros que pedimos a facebook
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        mCallback.onRetrieveInformation(null, "El usuario cancelo");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        mCallback.onRetrieveInformation(null, exception.toString() + " ");
                    }
                });


            }

        });


    }
}
