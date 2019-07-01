package com.example.heriana.javaaplikasi.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import com.example.heriana.javaaplikasi.Activities.CompilerActivity;
import com.example.heriana.javaaplikasi.lesson.MenuMateriActivity;
import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.question.SoalActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.lesson_card)
    CardView lessonCard;
    @BindView(R.id.question_card)
    CardView questionCard;
    @BindView(R.id.score_card)
    CardView scoreCard;
    @BindView(R.id.compiler_card)
    CardView compilerCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Learning Java");
        }

        lessonCard.setOnClickListener(v -> {
            Intent a = new Intent(HomeActivity.this, MenuMateriActivity.class);
            startActivity(a);
        });

        questionCard.setOnClickListener(v -> {
            Intent b = new Intent(HomeActivity.this, SoalActivity.class);
            startActivity(b);
        });

        scoreCard.setOnClickListener(v -> {
            Intent a = new Intent(HomeActivity.this, MenuMateriActivity.class);
            startActivity(a);
        });

        compilerCard.setOnClickListener(v -> {
            Intent c = new Intent(HomeActivity.this, CompilerActivity.class);
            startActivity(c);
        });

    }
}

