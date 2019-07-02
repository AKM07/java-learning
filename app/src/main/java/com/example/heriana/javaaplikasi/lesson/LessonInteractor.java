package com.example.heriana.javaaplikasi.lesson;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.heriana.javaaplikasi.helpers.Helpers;
import com.example.heriana.javaaplikasi.model.Lesson;
import com.example.heriana.javaaplikasi.model.PDF;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AKM on 7/1/2019.
 */
public class LessonInteractor implements LessonContract.Intractor {

    private LessonContract.onGetLessonListener onGetLessonListener;
    private List<Lesson> lessons = new ArrayList<>();

    public LessonInteractor(LessonContract.onGetLessonListener onGetLessonListener){
        this.onGetLessonListener = onGetLessonListener;
    }
    @Override
    public void performGetLessons(Activity activity) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = reference.child("lesson/javaFundamental");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Lesson lesson = snapshot.getValue(Lesson.class);
                    lessons.add(lesson);
                }
                if (lessons.size() == dataSnapshot.getChildrenCount()) {
                    onGetLessonListener.onSuccess(lessons);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                onGetLessonListener.onFailure(databaseError.getMessage());
            }
        };

        ref.addValueEventListener(listener);
    }

    @Override
    public void performGetProgrammingLessons(Activity activity) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = reference.child("lesson/javaProgramming");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("lessonSizeProg", String.valueOf(dataSnapshot.getChildrenCount()));

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Lesson lesson = snapshot.getValue(Lesson.class);
                    lessons.add(lesson);
                    Log.e("lesson", String.valueOf(lesson));

                }

                if (lessons.size() == dataSnapshot.getChildrenCount()) {
                    onGetLessonListener.onGetProgrammingSuccess(lessons);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("databaseError", String.valueOf(databaseError.toException()));
                onGetLessonListener.onGetProgrammingFailure(databaseError.getMessage());
            }
        };

        ref.addValueEventListener(listener);
    }

    @Override
    public void performGetPDFFile(Activity activity, String fileUrl) {
        try {

            String fileName = "java_" + Helpers.formatDate(new Date());

            URL url = new URL(fileUrl);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(false);
            c.connect();
            String PATH = "data/data/" + activity.getPackageName() + "/javaLearning/";
            File file = new File(PATH);
            file.mkdir();
            File outputFile = new File(file, fileName);

            Log.e("out", outputFile.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(outputFile);
            int status = c.getResponseCode();
            InputStream is;
            if (status != HttpURLConnection.HTTP_OK)
                is = c.getErrorStream();
            else
                is = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();

            if (status == 200) {
                onGetLessonListener.onGetPDFFileSuccess(outputFile);
                PDF pdf = Select.from(PDF.class).where(Condition.prop("URL").eq(fileUrl)).first();
                if (pdf == null) {
                    PDF savePdf = new PDF();
                    savePdf.setUrl(fileUrl);
                    savePdf.setPath(outputFile.getPath());
                    savePdf.setCreatedDate(new Date());
                    savePdf.save();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            onGetLessonListener.onGetPDFFileFailure(e.getMessage());
        }
    }
}
