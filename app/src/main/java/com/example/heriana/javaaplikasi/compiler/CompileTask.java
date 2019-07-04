package com.example.heriana.javaaplikasi.compiler;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by AKM on 7/3/2019.
 */
public class CompileTask extends AsyncTask<String, Void, String> {

    private CompileListener listener;
    private Context context;

    public CompileTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        listener.onCompilePreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String script = strings[0];
        String clientId = "3a76d38f9a708cb7a2aff57136b70191"; //Replace with your client ID
        String clientSecret = "d5f675a8b12648818b0ef962c34adaf85ea5b760ce75090cc02c5af7d206f974"; //Replace with your client Secret
        String language = "java";
        String versionIndex = "0";
        String result = "";
        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{clientId: " + clientId + ",clientSecret:" + clientSecret + ",script:" + script + ",stdIn:" + "Stdin" +
                    ",language:" + language + ",versionIndex:" + versionIndex + "}";

            Log.e("input Jdoodle", input);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e("Error", String.valueOf(connection.getResponseCode()));
                listener.onCompileCancelled("Cek Kembali Inputan Anda");
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String output;
            System.out.println();
            while ((output = bufferedReader.readLine()) != null) {
                Log.e("Output from JDoodle", output);
                result = output;
            }

            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        listener.onCompilePostExecute(s);
    }


    @Override
    protected void onCancelled(String s) {
        listener.onCompileCancelled(s);
    }

    public CompileTask setListener(CompileListener listener) {
        this.listener = listener;
        return this;
    }

    public interface CompileListener {
        void onCompilePreExecute();

        void onCompilePostExecute(String output);

        void onCompileCancelled(String output);
    }
}
