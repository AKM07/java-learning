package com.example.heriana.javaaplikasi.compiler;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.heriana.javaaplikasi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompilerActivity extends AppCompatActivity implements CompileTask.CompileListener {

    ProgressDialog mProgressDialog;
    @BindView(R.id.compile_input)
    EditText compileInput;
    @BindView(R.id.btn_compile)
    Button btnCompile;
    @BindView(R.id.compile_result)
    TextView compileResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compiler);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btnCompile.setOnClickListener(v -> {
            new CompileTask(this).setListener(this).execute(compileInput.getText().toString());
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
    public void onCompilePreExecute() {
        mProgressDialog = new ProgressDialog(CompilerActivity.this);
        mProgressDialog.setMessage("Please wait..");

        mProgressDialog.show();
    }

    @Override
    public void onCompilePostExecute(String output) {
        mProgressDialog.dismiss();
        if (output != null) {
            compileResult.setText(output);
        }
    }

    @Override
    public void onCompileCancelled(String output) {
        mProgressDialog.dismiss();
    }
}
