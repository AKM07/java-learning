package com.example.heriana.javaaplikasi.question;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.model.Answer;
import com.example.heriana.javaaplikasi.model.AnswerChoice;
import com.example.heriana.javaaplikasi.model.QuestionFirebase;
import com.example.heriana.javaaplikasi.score.SkorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity implements QuestionContract.View {

    @BindView(R.id.radio0)
    RadioButton radio0;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radio3)
    RadioButton radio3;
    @BindView(R.id.question_text)
    TextView questionText;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;
    @BindView(R.id.btn_done)
    Button btnDone;

    private int order = 1;
    ProgressDialog mProgressDialog;
    private List<Answer> choosedAnswers = new ArrayList<>();
    private List<QuestionFirebase> questions = new ArrayList<>();
    QuestionPresenter presenter;
    long total = 0;
    double correctCount = 0;
    String currentQuestion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        ButterKnife.bind(this);

        presenter = new QuestionPresenter(this);

        presenter.performGetAllQuestion(this);
        presenter.getQuestion(this, order);
        presenter.getAnswerChoice(this, order);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading..");

        btnNext.setOnClickListener(v -> {
            if (radio0.isChecked()) {
                choosedAnswers.add(new Answer(currentQuestion, "A"));
                radio0.setChecked(false);
                loadNextQuestion();
            } else if (radio1.isChecked()) {
                choosedAnswers.add(new Answer(currentQuestion, "B"));
                radio1.setChecked(false);
                loadNextQuestion();
            } else if (radio2.isChecked()) {
                choosedAnswers.add(new Answer(currentQuestion, "C"));
                radio2.setChecked(false);
                loadNextQuestion();
            } else if (radio3.isChecked()) {
                choosedAnswers.add(new Answer(currentQuestion, "D"));
                radio3.setChecked(false);
                loadNextQuestion();
            } else {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Silahkan pilih jawaban terlebih dahulu");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "OK",
                        (dialog, id) -> dialog.cancel());
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

            if (order == total) {
                btnNext.setVisibility(View.GONE);
                btnDone.setVisibility(View.VISIBLE);
            }
        });

        btnDone.setOnClickListener(v -> {
            for (QuestionFirebase firebase : questions) {
                for (Answer answer : choosedAnswers) {
                    if (firebase.getQuestion().equals(answer.getQuestion())) {
                        if (answer.getAnswer().equals(firebase.getAnswer())) {
                            correctCount++;
                        }
                    }
                }
            }

            Intent intent = new Intent(QuestionActivity.this, SkorActivity.class);
            intent.putExtra("score", correctCount);
            intent.putExtra("total", total);
            startActivity(intent);
            finish();
        });
    }

    private void loadNextQuestion() {
        order++;
        mProgressDialog.show();
        presenter.getQuestion(QuestionActivity.this, order);
        presenter.getAnswerChoice(QuestionActivity.this, order);
    }

    @Override
    public void onGetQuestionSuccess(QuestionFirebase questionFirebase) {
        if (questionFirebase != null) {
            currentQuestion = questionFirebase.getQuestion();
            questionText.setText(questionFirebase.getQuestion());
            questions.add(questionFirebase);
        }
    }

    @Override
    public void onGetQuestionFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetAnswerChoiceSuccess(AnswerChoice answerChoice) {
        mProgressDialog.dismiss();
        if (answerChoice != null) {
            radio0.setText(answerChoice.getA().toString());
            radio1.setText(answerChoice.getB().toString());
            radio2.setText(answerChoice.getC().toString());
            radio3.setText(answerChoice.getD().toString());
        }
    }

    @Override
    public void onGetAnswerChoiceFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetAllQuestionSuccess(long total) {
        this.total = total;
    }

    @Override
    public void onGetAllQuestionFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Progress anda akan hilang, anda yakin?");
            builder1.setCancelable(true);
            builder1.setPositiveButton("YA", (dialog, which) -> {
                dialog.cancel();
                QuestionActivity.this.finish();
            });
            AlertDialog alert11 = builder1.create();
            alert11.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Progress anda akan hilang, anda yakin?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("YA", (dialog, which) -> {
            dialog.cancel();
            QuestionActivity.this.finish();
        });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
