package com.example.heriana.javaaplikasi.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by AKM on 7/2/2019.
 */
public class QuestionFirebase extends SugarRecord implements Serializable {

    private String answer;
    private String question;
    private AnswerChoice answerChoice;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public AnswerChoice getAnswerChoice() {
        return answerChoice;
    }

    public void setAnswerChoice(AnswerChoice answerChoice) {
        this.answerChoice = answerChoice;
    }
}
