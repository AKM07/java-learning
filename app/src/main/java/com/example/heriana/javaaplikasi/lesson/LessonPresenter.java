package com.example.heriana.javaaplikasi.lesson;

import android.app.Activity;

import com.example.heriana.javaaplikasi.model.Lesson;

import java.io.File;
import java.util.List;

/**
 * Created by AKM on 7/1/2019.
 */
public class LessonPresenter implements LessonContract.Presenter, LessonContract.onGetLessonListener {

    private LessonContract.View view;
    private LessonInteractor interactor;

    public LessonPresenter(LessonContract.View view) {
        this.view = view;
        interactor = new LessonInteractor(this);
    }

    @Override
    public void getLessons(Activity activity) {
        interactor.performGetLessons(activity);
    }

    @Override
    public void getProgrammingLessons(Activity activity) {
        interactor.performGetProgrammingLessons(activity);
    }

    @Override
    public void getFilePDF(Activity activity, String fileUrl) {
        interactor.performGetPDFFile(activity, fileUrl);
    }

    @Override
    public void onSuccess(List<Lesson> lessonList) {
        view.onGetLessonSuccess(lessonList);
    }

    @Override
    public void onFailure(String message) {
        view.onGetLessonFailure(message);
    }

    @Override
    public void onGetProgrammingSuccess(List<Lesson> lessonList) {
        view.onGetProgrammingSuccess(lessonList);
    }

    @Override
    public void onGetProgrammingFailure(String message) {
        view.onGetProgrammingFailure(message);
    }

    @Override
    public void onGetPDFFileSuccess(File file) {
        view.onGetPDFFileSuccess(file);
    }

    @Override
    public void onGetPDFFileFailure(String message) {
        view.onGetPDFFileFailure(message);
    }
}
