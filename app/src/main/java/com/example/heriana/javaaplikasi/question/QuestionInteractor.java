package com.example.heriana.javaaplikasi.question;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.heriana.javaaplikasi.model.AnswerChoice;
import com.example.heriana.javaaplikasi.model.QuestionFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by AKM on 7/2/2019.
 */
public class QuestionInteractor implements QuestionContract.Intractor {

    private QuestionContract.onGetQuestionListener onGetQuestionListener;

    public QuestionInteractor(QuestionContract.onGetQuestionListener onGetQuestionListener){
        this.onGetQuestionListener = onGetQuestionListener;
    }

    @Override
    public void performGetQuestion(Activity activity, int order) {
        try {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference ref = reference.child("question/" + order);
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    QuestionFirebase questionFirebase = dataSnapshot.getValue(QuestionFirebase.class);
                    onGetQuestionListener.onGetQuestionSuccess(questionFirebase);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    onGetQuestionListener.onGetQuestionFailure(databaseError.getMessage());
                }
            };

            ref.addValueEventListener(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void performGetAnswerChoice(Activity activity, int order) {
        try {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference ref = reference.child("question/" + order + "/answerChoice");
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    AnswerChoice questionFirebase = dataSnapshot.getValue(AnswerChoice.class);
                    onGetQuestionListener.onGetAnswerChoiceSuccess(questionFirebase);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    onGetQuestionListener.onGetAnswerChoiceFailure(databaseError.getMessage());
                }
            };

            ref.addValueEventListener(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void performGetAllQuestion(Activity activity) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = reference.child("question");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                onGetQuestionListener.onGetAllQuestionSuccess(dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                onGetQuestionListener.onGetAllQuestionFailure(databaseError.getMessage());
            }
        };

        ref.addValueEventListener(listener);
    }
}
