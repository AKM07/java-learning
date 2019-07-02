package com.example.heriana.javaaplikasi.lesson;

import android.app.Activity;

import com.example.heriana.javaaplikasi.model.Lesson;

import java.io.File;
import java.util.List;

/**
 * Created by AKM on 7/1/2019.
 */
public interface LessonContract {

    interface View {
        void onGetLessonSuccess(List<Lesson> lessonList);

        void onGetLessonFailure(String message);

        void onGetProgrammingSuccess(List<Lesson> lessonList);

        void onGetProgrammingFailure(String message);

        void onGetPDFFileSuccess(File file);

        void onGetPDFFileFailure(String message);
    }

    interface Presenter {
        void getLessons(Activity activity);

        void getProgrammingLessons(Activity activity);

        void getFilePDF(Activity activity, String fileUrl);
    }

    interface Intractor {
        void performGetLessons(Activity activity);

        void performGetProgrammingLessons(Activity activity);

        void performGetPDFFile(Activity activity, String fileUrl);
    }

    interface onGetLessonListener {
        void onSuccess(List<Lesson> lessonList);

        void onFailure(String message);

        void onGetProgrammingSuccess(List<Lesson> lessonList);

        void onGetProgrammingFailure(String message);

        void onGetPDFFileSuccess(File file);

        void onGetPDFFileFailure(String message);
    }
}
