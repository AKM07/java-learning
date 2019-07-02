package com.example.heriana.javaaplikasi.lesson;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.model.Lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GBS Ari on 7/1/2019.
 */
public class LessonFundamentalAdapter extends RecyclerView.Adapter<LessonFundamentalAdapter.LessonCategoryViewHolder> {

    private Context context;
    private List<Lesson> lessonList = new ArrayList<>();

    public LessonFundamentalAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LessonCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_lesson_category, viewGroup, false);
        return new LessonCategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonCategoryViewHolder lessonCategoryViewHolder, int i) {
        Lesson item = lessonList.get(i);
        lessonCategoryViewHolder.lessonName.setText(item.getName());
        lessonCategoryViewHolder.lessonContent.setText(item.getContent());

        lessonCategoryViewHolder.itemView.setOnClickListener(v -> {
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

    public class LessonCategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView lessonName;
        private TextView lessonContent;
        public LessonCategoryViewHolder(View itemView) {
            super(itemView);
            lessonName = itemView.findViewById(R.id.lesson_name);
            lessonContent = itemView.findViewById(R.id.lesson_content);
        }
    }
}
