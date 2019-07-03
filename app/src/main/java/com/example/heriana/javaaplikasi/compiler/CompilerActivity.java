package com.example.heriana.javaaplikasi.compiler;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.heriana.javaaplikasi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompilerActivity extends AppCompatActivity implements CompilerContract.View {

    @BindView(R.id.compile_input)
    EditText compileInput;
    @BindView(R.id.btn_compile)
    Button btnCompile;
    @BindView(R.id.compile_result)
    TextView compileResult;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compiler);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btnCompile.setOnClickListener(v -> {
            CompilerPresenter presenter = new CompilerPresenter(CompilerActivity.this);

            mProgressDialog = new ProgressDialog(CompilerActivity.this);
            mProgressDialog.setMessage("Please wait..");

            mProgressDialog.show();
            presenter.compile(CompilerActivity.this, compileInput.getText().toString());
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String output) {
        compileResult.setText(output);
    }

    @Override
    public void onFailure(String message) {

    }
}
