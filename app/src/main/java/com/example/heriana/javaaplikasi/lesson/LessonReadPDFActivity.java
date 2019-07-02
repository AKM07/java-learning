package com.example.heriana.javaaplikasi.lesson;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.heriana.javaaplikasi.R;
import com.example.heriana.javaaplikasi.model.Lesson;
import com.example.heriana.javaaplikasi.model.PDF;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AKM on 7/1/2019.
 */
public class LessonReadPDFActivity extends AppCompatActivity implements DownloadPDFTask.DownloadPDFListener {


    @BindView(R.id.pdf_view)
    PDFView pdfView;
    @BindView(R.id.progressbar_pdf)
    ProgressBar progressbarPdf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_read_pdf);
        ButterKnife.bind(this);

        String fileUrl = getIntent().getStringExtra("fileUrl");

        PDF pdf = Select.from(PDF.class).where(Condition.prop("URL").eq(fileUrl)).first();
        if (pdf != null) {
            File file = new File(pdf.getPath());
            showPdf(file);
        } else {
            new DownloadPDFTask(this).setListener(this).execute(fileUrl);
        }
    }

    public void showPdf(File file) {
        pdfView.fromFile(file)
                .enableDoubletap(true)
                .enableSwipe(true)
                .onLoad(nbPages -> {
                })
                .defaultPage(0)
                .enableAntialiasing(true)
                .load();
    }

    @Override
    public void onDownloadPDFPreExecute() {
        pdfView.setVisibility(View.GONE);
        progressbarPdf.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDownloadPDFPostExecute(File file) {
        if (file != null){
            pdfView.setVisibility(View.VISIBLE);
            progressbarPdf.setVisibility(View.GONE);
            showPdf(file);
        }
    }
}
