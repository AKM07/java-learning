package com.example.heriana.javaaplikasi.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.heriana.javaaplikasi.compiler.CompilerActivity;
import com.example.heriana.javaaplikasi.lesson.LessonCategoryActivity;
import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.lesson_card)
    CardView lessonCard;
    @BindView(R.id.question_card)
    CardView scoreCard;
    @BindView(R.id.compiler_card)
    CardView compilerCard;

    SharedPreferences mySPrefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Learning Java");
        }

        mySPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = mySPrefs.edit();

        lessonCard.setOnClickListener(v -> {
            Intent a = new Intent(HomeActivity.this, LessonCategoryActivity.class);
            startActivity(a);
        });

        scoreCard.setOnClickListener(v -> {
            Intent a = new Intent(HomeActivity.this, LessonCategoryActivity.class);
            startActivity(a);
        });

        compilerCard.setOnClickListener(v -> {
            Intent c = new Intent(HomeActivity.this, CompilerActivity.class);
            startActivity(c);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            mySPrefs.edit().putBoolean("logged", false).apply();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

