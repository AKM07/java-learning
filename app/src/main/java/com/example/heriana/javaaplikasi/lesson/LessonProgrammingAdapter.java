package com.example.heriana.javaaplikasi.lesson;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.model.Lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKM on 7/1/2019.
 */
public class LessonProgrammingAdapter extends RecyclerView.Adapter<LessonProgrammingAdapter.LessonProgrammingViewHolder>{

    private Context context;
    private List<Lesson> lessonList = new ArrayList<>();

    public LessonProgrammingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LessonProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_lesson_programming, viewGroup, false);
        return new LessonProgrammingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonProgrammingViewHolder lessonProgrammingViewHolder, int i) {
        Lesson item = lessonList.get(i);
        lessonProgrammingViewHolder.lessonName.setText(item.getName());
        lessonProgrammingViewHolder.lessonContent.setText(item.getContent());
        Log.e("item.getFileUrl()", item.getFileUrl());
        lessonProgrammingViewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, LessonReadPDFActivity.class);
            intent.putExtra("fileUrl", item.getFileUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    void addLessons(List<Lesson> lessons) {
        this.lessonList = lessons;
        notifyDataSetChanged();
    }

    public class LessonProgrammingViewHolder extends RecyclerView.ViewHolder {
        private TextView lessonName;
        private TextView lessonContent;

        public LessonProgrammingViewHolder(View itemView) {
            super(itemView);
            lessonName = itemView.findViewById(R.id.lesson_name);
            lessonContent = itemView.findViewById(R.id.lesson_content);
        }
    }
}
