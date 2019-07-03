package com.example.heriana.javaaplikasi.compiler;

import android.app.Activity;

/**
 * Created by GBS Ari on 7/3/2019.
 */
public interface CompilerContract {
    interface View {
        void onSuccess(String output);

        void onFailure(String message);

    }

    interface Presenter {
        void compile(Activity activity, String script);
    }

    interface Intractor {
        void performCompile(Activity activity, String script);
    }

    interface onCompilerListener {
        void onSuccess(String output);

        void onFailure(String message);
    }
}
