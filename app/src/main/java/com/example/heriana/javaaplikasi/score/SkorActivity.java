package com.example.heriana.javaaplikasi.score;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.heriana.javaaplikasi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkorActivity extends AppCompatActivity {
    @BindView(R.id.label_quiz_score)
    TextView labelQuizScore;
    @BindView(R.id.label_total_correct)
    TextView labelTotalCorrect;
    @BindView(R.id.buttonHome)
    LinearLayout buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasilsoal);
        ButterKnife.bind(this);

        double score = getIntent().getDoubleExtra("score", 0);
        long total = getIntent().getLongExtra("total", 0);

        Log.e("totals", String.valueOf(total));
        Log.e("scores", String.valueOf(score));


        double totalScore = score * 10;
        double totalResult = total * 10;

        Log.e("totalScore", String.valueOf(totalScore).replaceAll(".0", ""));
        Log.e("totalResult", String.valueOf(totalResult).replaceAll(".0", ""));

        labelQuizScore.setText(String.valueOf(totalScore));
        labelTotalCorrect.setText(String.valueOf(totalResult));

    }

}

