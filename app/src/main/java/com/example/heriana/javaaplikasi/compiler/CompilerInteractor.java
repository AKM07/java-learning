package com.example.heriana.javaaplikasi.compiler;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by GBS Ari on 7/3/2019.
 */
public class CompilerInteractor implements CompilerContract.Intractor {

    private CompilerContract.onCompilerListener listener;

    public CompilerInteractor(CompilerContract.onCompilerListener listener) {
        this.listener = listener;
    }

    @Override
    public void performCompile(Activity activity, String script) {
        String clientId = "3a76d38f9a708cb7a2aff57136b70191"; //Replace with your client ID
        String clientSecret = "d5f675a8b12648818b0ef962c34adaf85ea5b760ce75090cc02c5af7d206f974"; //Replace with your client Secret
        String language = "java";
        String versionIndex = "0";
        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script +
                    "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\"} ";

            Log.e("input Jdoodle", input);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String output;
            System.out.println();
            while ((output = bufferedReader.readLine()) != null) {
                Log.e("Output from JDoodle", output);
                listener.onSuccess(output);
            }

            connection.disconnect();
        } catch (MalformedURLException e) {
            listener.onFailure(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            listener.onFailure(e.getMessage());
            e.printStackTrace();
        }
    }
}
