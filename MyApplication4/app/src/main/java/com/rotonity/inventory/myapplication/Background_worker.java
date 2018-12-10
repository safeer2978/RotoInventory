package com.rotonity.inventory.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.PortUnreachableException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Duration;

public class Background_worker extends AsyncTask<String,Void,String> {

    public static String callingFunction="no";//login,add,edit,view
    Dialog dialog;// =new Dialog(this.context);
    private Context context;
   // ProgressDialog dialog= new ProgressDialog(this.context);

    Background_worker(Context ct){
        context=ct;
        dialog=new Dialog(ct);
    }


    @Override
     protected String doInBackground(String... params  ) {

       String type = params[0];
        String post_data="";
       callingFunction = params[0];

        try {
          //  String login_url = "http://192.168.1.102/check.php";
            String login_url = "https://rotonity.000webhostapp.com/func.php";


         if(type.equals("login")) {
             String user_name = params[1];
             String password = params[2];
             post_data = URLEncoder.encode("FUNCTIONtype", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8") + "&" +
                     URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                     + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
         }
            else {
                String partType = params[1];
                String description = params[2];
                String cost = params[3];
                String currentlyWith = params[4];
                String purchased = params[5];
                String qrResult = params[6];
                String what=params[7];
                post_data = URLEncoder.encode("FUNCTIONtype", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8") + "&" +
                        URLEncoder.encode("partType", "UTF-8") + "=" + URLEncoder.encode(partType, "UTF-8") + "&" +
                        URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8") + "&" +
                        URLEncoder.encode("cost", "UTF-8") + "=" + URLEncoder.encode(cost, "UTF-8") + "&" +
                        URLEncoder.encode("currentlyWith", "UTF-8") + "=" + URLEncoder.encode(currentlyWith, "UTF-8") + "&" +
                        URLEncoder.encode("purchased", "UTF-8") + "=" + URLEncoder.encode(purchased, "UTF-8") + "&" +
                        URLEncoder.encode("qrResult", "UTF-8") + "=" + URLEncoder.encode(qrResult, "UTF-8") + "&" +
                     URLEncoder.encode("what", "UTF-8") + "=" + URLEncoder.encode(what, "UTF-8");
            }
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWrter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWrter.write(post_data);
            bufferedWrter.flush();
            bufferedWrter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream, "iso-8859-1")));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;
        } catch (IOException e) {
            return "exception in worker";
        }



    // return null;
    }

    @Override
    protected void onPreExecute() {
        //dialog.setMessage("message");
       // dialog.setCancelable(false);
        //dialog.setInverseBackgroundForced(false);
        dialog.create();
        dialog.setTitle("Loading.....");
        dialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.hide();
        Global.db_result=result;
        //Toast.makeText(context,"maybeDone?",Toast.LENGTH_LONG).show();


        }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
      /*dialog.setMessage(values.toString().toLowerCase());
      dialog.show();*/
    }
}
