package com.example.heriana.javaaplikasi.compiler;

import android.app.Activity;

/**
 * Created by GBS Ari on 7/3/2019.
 */
public class CompilerPresenter implements CompilerContract.Presenter, CompilerContract.onCompilerListener {

    private CompilerContract.View view;
    private CompilerInteractor interactor;

    public CompilerPresenter(CompilerContract.View view) {
        this.view = view;
        interactor = new CompilerInteractor(this);
    }

    @Override
    public void compile(Activity activity, String script) {
        interactor.performCompile(activity, script);
    }

    @Override
    public void onSuccess(String output) {
        view.onSuccess(output);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }
}
