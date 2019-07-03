package com.example.heriana.javaaplikasi.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.home.HomeActivity;
import com.example.heriana.javaaplikasi.register.RegistrationActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.sign_in_button)
    Button signInButton;
    @BindView(R.id.register_link)
    TextView registerLink;
    @BindView(R.id.email_input)
    TextInputEditText emailInput;
    @BindView(R.id.password_input)
    TextInputEditText passwordInput;

    private LoginPresenter mLoginPresenter;
    ProgressDialog mProgressDialog;

    SharedPreferences mySPrefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();

        mySPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = mySPrefs.edit();

        if (mySPrefs.getBoolean("logged", false)) {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }
    }


    private void initViews() {
        mLoginPresenter = new LoginPresenter(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in..");

        signInButton.setOnClickListener(v -> checkLoginDetails());

        registerLink.setOnClickListener(v -> moveToRegistrationActivity());
    }

    private void moveToRegistrationActivity() {
        Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);
    }

    private void checkLoginDetails() {
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
        mProgressDialog.show();
        mLoginPresenter.login(this, email, password);
    }

    @Override
    public void onLoginSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
        mySPrefs.edit().putBoolean("logged", true).apply();
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
