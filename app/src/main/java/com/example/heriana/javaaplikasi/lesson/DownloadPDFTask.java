package com.example.heriana.javaaplikasi.lesson;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.heriana.javaaplikasi.helpers.Helpers;
import com.example.heriana.javaaplikasi.model.PDF;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by AKM on 7/2/2019.
 */
public class DownloadPDFTask extends AsyncTask<String, Void, File> {

    private DownloadPDFListener listener;

    private Context context;

    public DownloadPDFTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        listener.onDownloadPDFPreExecute();
    }

    @Override
    protected File doInBackground(String... strings) {
        String fileUrl = strings[0];

        try {

            String fileName = "javaLearn_" + Helpers.formatDate(new Date());

            URL url = new URL(fileUrl);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(false);
            c.connect();
            String PATH = "data/data/" + context.getPackageName() + "/javaLearn/";
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
                PDF pdf = Select.from(PDF.class).where(Condition.prop("URL").eq(fileUrl)).first();
                if (pdf == null) {
                    PDF savePdf = new PDF();
                    savePdf.setUrl(fileUrl);
                    savePdf.setPath(outputFile.getPath());
                    savePdf.setCreatedDate(new Date());
                    savePdf.save();
                }
            }

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(File file) {
        listener.onDownloadPDFPostExecute(file);
    }

    public DownloadPDFTask setListener(DownloadPDFListener listener) {
        this.listener = listener;
        return this;
    }

    public interface DownloadPDFListener {
        void onDownloadPDFPreExecute();

        void onDownloadPDFPostExecute(File file);
    }
}
