package com.example.heriana.javaaplikasi.login;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractor implements LoginContract.Intractor {

    private LoginContract.onLoginListener mOnLoginListener;

    public LoginInteractor(LoginContract.onLoginListener onLoginListener){
        this.mOnLoginListener = onLoginListener;
    }
    @Override
    public void performFirebaseLogin(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        mOnLoginListener.onSuccess(task.getResult().toString());
                    }
                    else {
                        mOnLoginListener.onFailure(task.getException().toString());
                    }
                });
    }
}
