package com.example.heriana.javaaplikasi.lesson;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.model.Lesson;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GBS Ari on 7/1/2019.
 */
public class LessonFundamentalFragment extends Fragment implements LessonContract.View {

    @BindView(R.id.lesson_list)
    RecyclerView lessonList;
    ProgressDialog mProgressDialog;

    private LessonFundamentalAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_fundamental, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new LessonFundamentalAdapter(getActivity());
        lessonList.setLayoutManager(llm);
        lessonList.setItemAnimator(new DefaultItemAnimator());
        lessonList.setHasFixedSize(true);
        lessonList.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LessonPresenter presenter = new LessonPresenter(this);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Please wait..");

        presenter.getLessons(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onGetLessonSuccess(List<Lesson> lessonList) {
        adapter.addLessons(lessonList);
    }

    @Override
    public void onGetLessonFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetProgrammingSuccess(List<Lesson> lessonList) {

    }

    @Override
    public void onGetProgrammingFailure(String message) {

    }

    @Override
    public void onGetPDFFileSuccess(File file) {

    }

    @Override
    public void onGetPDFFileFailure(String message) {

    }
}
