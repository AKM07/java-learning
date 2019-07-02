package com.example.heriana.javaaplikasi.question;

import android.app.Activity;

import com.example.heriana.javaaplikasi.model.AnswerChoice;
import com.example.heriana.javaaplikasi.model.QuestionFirebase;

import java.util.List;

/**
 * Created by AKM on 7/2/2019.
 */
public interface QuestionContract {

    interface View {
        void onGetQuestionSuccess(QuestionFirebase questionFirebase);

        void onGetQuestionFailure(String message);

        void onGetAnswerChoiceSuccess(AnswerChoice answerChoice);

        void onGetAnswerChoiceFailure(String message);

        void onGetAllQuestionSuccess(long total);

        void onGetAllQuestionFailure(String message);
    }

    interface Presenter {
        void getQuestion(Activity activity, int order);

        void getAnswerChoice(Activity activity, int order);

        void performGetAllQuestion(Activity activity);
    }

    interface Intractor {
        void performGetQuestion(Activity activity, int order);

        void performGetAnswerChoice(Activity activity, int order);

        void performGetAllQuestion(Activity activity);
    }

    interface onGetQuestionListener {
        void onGetQuestionSuccess(QuestionFirebase questionFirebase );

        void onGetQuestionFailure(String message);

        void onGetAnswerChoiceSuccess(AnswerChoice answerChoice);

        void onGetAnswerChoiceFailure(String message);

        void onGetAllQuestionSuccess(long total);

        void onGetAllQuestionFailure(String message);
    }
}
