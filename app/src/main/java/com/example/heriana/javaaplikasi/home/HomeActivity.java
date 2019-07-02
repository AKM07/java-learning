package com.example.heriana.javaaplikasi.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import com.example.heriana.javaaplikasi.Activities.CompilerActivity;
import com.example.heriana.javaaplikasi.lesson.LessonCategoryActivity;
import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.question.QuestionActivity;

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
            Intent a = new Intent(HomeActivity.this, LessonCategoryActivity.class);
            startActivity(a);
        });

        questionCard.setOnClickListener(v -> {
            Intent b = new Intent(HomeActivity.this, QuestionActivity.class);
            startActivity(b);
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
}

