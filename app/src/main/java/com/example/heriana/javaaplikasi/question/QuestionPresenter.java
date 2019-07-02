package com.example.heriana.javaaplikasi.question;

import android.app.Activity;

import com.example.heriana.javaaplikasi.model.AnswerChoice;
import com.example.heriana.javaaplikasi.model.QuestionFirebase;

import java.util.List;

/**
 * Created by AKM on 7/2/2019.
 */
public class QuestionPresenter implements QuestionContract.Presenter, QuestionContract.onGetQuestionListener {

    private QuestionContract.View view;
    private QuestionInteractor interactor;

    public QuestionPresenter(QuestionContract.View view) {
        this.view = view;
        interactor = new QuestionInteractor(this);
    }


    @Override
    public void onGetQuestionSuccess(QuestionFirebase questionFirebase) {
        view.onGetQuestionSuccess(questionFirebase);
    }

    @Override
    public void onGetQuestionFailure(String message) {
        view.onGetQuestionFailure(message);
    }

    @Override
    public void onGetAnswerChoiceSuccess(AnswerChoice answerChoice) {
        view.onGetAnswerChoiceSuccess(answerChoice);
    }


    @Override
    public void onGetAnswerChoiceFailure(String message) {
        view.onGetAnswerChoiceFailure(message);
    }

    @Override
    public void onGetAllQuestionSuccess(long total) {
        view.onGetAllQuestionSuccess(total);
    }

    @Override
    public void onGetAllQuestionFailure(String message) {
        view.onGetQuestionFailure(message);
    }

    @Override
    public void getQuestion(Activity activity, int order) {
        interactor.performGetQuestion(activity, order);
    }

    @Override
    public void getAnswerChoice(Activity activity, int order) {
        interactor.performGetAnswerChoice(activity, order);
    }

    @Override
    public void performGetAllQuestion(Activity activity) {
        interactor.performGetAllQuestion(activity);
    }
}
