package com.khirulnizam.json;

//GITHUB bit.ly/gitjson

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    private EditText txtusername, txtpassword;
    private Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind to UI
        txtusername= (EditText)findViewById(R.id.txtusername);
        txtpassword= (EditText)findViewById(R.id.txtpassword);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);
    }//end onCreate

    //capture username/password
    String username, password;
    public void onClick(View v){

        String msg="Welcome ";
        msg = msg+txtusername.getText().toString();
        msg=msg+"\nPassword ";
        msg =msg+txtpassword.getText().toString();
        //popup
        Toast.makeText(getApplicationContext(),msg,
                Toast.LENGTH_LONG).show();

        username=txtusername.getText().toString();
        password=txtpassword.getText().toString();
        //invoke UserLoginTask
        new UserLoginTask(username, password).execute();
    }//end onClick
    //

    //logVerification
    private void loginVerification(String svrmsg){
        String savemsg="";
        //if new record successfully saved
        Toast.makeText(this, "svrmsg:"+svrmsg,Toast.LENGTH_LONG).show();
        if (svrmsg.equals("error")){
            savemsg="Email/password not match!";
        }
        else {//save failed
            savemsg="Welcome "+svrmsg;
        }

        //**********common dialog box
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("User Login");
        builder1.setMessage(savemsg);
        builder1.setCancelable(false);
        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();

        //call ListUsers page if servermessage is success
        if (svrmsg.equals("error")){

        }
        else{
            Intent i=new Intent(this,Listing.class);
            startActivity(i);
        }

    }//end loginVerification

    //UserLoginTask
    public class UserLoginTask extends AsyncTask<String, Void, String> {

        private final String mUsername;
        private final String mPassword;

        UserLoginTask(String username, String password) {
            mUsername = username;
            mPassword = password;
        }

        @Override
        protected void onPreExecute(){
            //showProgress(true);
        }//onPreExecute

        @Override
        protected String doInBackground(String... arg0) {

            try {

                //URL url = new URL("http://192.168.0.186/json101/login.php"); // here is your URL path
                URL url = new URL("http://khirulnizam.com/training/login.php");
                JSONObject postDataParams = new JSONObject();
                //add name pair values to the connection

                postDataParams.put("uname", mUsername);
                postDataParams.put("pwd", mPassword);

                Log.e("params",postDataParams.toString());
                Log.e("URL",url.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();
                Log.e("responseCode", "responseCode "+responseCode);
                if (responseCode == HttpsURLConnection.HTTP_OK) {//code 200 connection OK
                    //this part is to capture the server response
                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));
                    //Log.e("response",conn.getInputStream().toString());

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    do{
                        sb.append(line);
                        Log.e("MSG sb",sb.toString());
                    }while ((line = in.readLine()) != null) ;

                    in.close();
                    Log.e("response",conn.getInputStream().toString());
                    Log.e("textmessage",sb.toString());
                    return sb.toString();//server response message

                }
                else {

                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                //error on connection
                return new String("Exception: " + e.getMessage());
            }
        }//end doInBackground

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(), result,
            //Toast.LENGTH_LONG).show();
            //return result;
            //call method to handle after verification


            //mAuthTask = null;
            //showProgress(false);

            loginVerification(result);
        }//end onPostExecute

        @Override
        protected void onCancelled() {
            //mAuthTask = null;
            //showProgress(false);
        }//end onCancelled
    }
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){
            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }//end getPostDataString
        //Log.i("result",result.toString());
        return result.toString();
    }//end UserLoginTask

}//end MainActivity