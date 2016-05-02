package com.example.selfcare.selfcare;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Eng.Sara on 01/05/2016.
 */
public class RecordsTask extends AsyncTask<String  ,Void  ,String > {
    Context co;
    RecordsTask(Context context){

        co=context;
    }

    @Override
    protected String doInBackground(String... params) {
        String p_reg_url = "http://self255.3eeweb.com/gcm_main.php";////////
        String doctor_Id = params[0].toString();
        String patient_Id = params[1].toString();
        String mess = params[2].toString();
        try {
            URL url = new URL(p_reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String data = URLEncoder.encode("doctor_Id", "UTF-8") + "=" + URLEncoder.encode(doctor_Id, "UTF-8") + "&" +
                    URLEncoder.encode("patient_Id", "UTF-8") + "=" + URLEncoder.encode(patient_Id, "UTF-8") + "&" +
                    URLEncoder.encode("mess", "UTF-8") + "=" + URLEncoder.encode(mess, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            return "regesteration success";
        } catch (MalformedURLException e) {
            Toast.makeText(co, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(co, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return "outtry";
    }

    }
