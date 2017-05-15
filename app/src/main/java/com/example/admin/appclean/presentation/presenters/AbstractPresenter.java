package com.example.admin.appclean.presentation.presenters;


import com.example.admin.appclean.domain.executor.Executor;
import com.example.admin.appclean.domain.executor.MainThread;

public abstract class AbstractPresenter {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}
