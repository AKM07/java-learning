package com.example.heriana.javaaplikasi.lesson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.model.Lesson;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GBS Ari on 7/1/2019.
 */
public class LessonFundamentalFragment extends Fragment {


    @BindView(R.id.lesson_list)
    RecyclerView lessonList;

    private LessonCategoryAdapter adapter;
    private List<Lesson> lessons = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_fundamental, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new LessonCategoryAdapter(getActivity());
        lessonList.setLayoutManager(llm);
        lessonList.setItemAnimator(new DefaultItemAnimator());
        lessonList.setHasFixedSize(true);
        lessonList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = reference.child("lesson/javaFundamental");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("lessonSize", String.valueOf(dataSnapshot.getChildrenCount()));

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Lesson lesson = snapshot.getValue(Lesson.class);
                    lessons.add(lesson);
                    Log.e("lesson", String.valueOf(lesson));

                    adapter.addLessons(lessons);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("databaseError", String.valueOf(databaseError.toException()));
            }
        };

        ref.addValueEventListener(listener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
