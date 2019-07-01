package com.example.heriana.javaaplikasi.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.login.LoginActivity;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements  RegistrationContract.View {

    @BindView(R.id.email_input)
    TextInputEditText emailInput;
    @BindView(R.id.password_input)
    TextInputEditText passwordInput;
    @BindView(R.id.register_button)
    Button registerButton;
    @BindView(R.id.login_link)
    TextView loginLink;

    private RegistrationPresenter mRegisterPresenter;
    ProgressDialog mPrgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mRegisterPresenter = new RegistrationPresenter(this);

        mPrgressDialog = new ProgressDialog(this);
        mPrgressDialog.setMessage("Please wait, Adding profile to database.");

        registerButton.setOnClickListener(v -> checkRegistrationDetails());

        loginLink.setOnClickListener(v -> moveToLoginActivity());
    }


    private void moveToLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void checkRegistrationDetails() {
        if (!TextUtils.isEmpty(emailInput.getText().toString()) && !TextUtils.isEmpty(passwordInput.getText().toString())) {
            initLogin(emailInput.getText().toString(), passwordInput.getText().toString());
        } else {
            if (TextUtils.isEmpty(emailInput.getText().toString())) {
                emailInput.setError("Please enter a valid email");
            }
            if (TextUtils.isEmpty(passwordInput.getText().toString())) {
                passwordInput.setError("Please enter password");
            }
        }
    }

    private void initLogin(String email, String password) {
        mPrgressDialog.show();
        mRegisterPresenter.register(this, email, password);
    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistrationFailure(String message) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
