package com.khirulnizam.json;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
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

public class CreateUser extends AppCompatActivity
        implements View.OnClickListener{
    String uname, pwd,pwd2, userlevel="regular", fullname, email;
    EditText txtuname, txtpwd,txtpwd2,txtfullname, txtemail;
    Button btncreateuser;
    Switch txtadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //UI declarations
        txtuname = (EditText)findViewById(R.id.txtusername);
        txtpwd= (EditText)findViewById(R.id.txtpassword);
        txtpwd2= (EditText)findViewById(R.id.txtpassword2);
        txtadmin=(Switch)findViewById(R.id.txtadmin);
        //userlevel
        txtadmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    userlevel = "admin";
                } else {
                    userlevel = "regular";
                }
            }
        });

        txtfullname= (EditText)findViewById(R.id.txtfullname);
        txtemail=(EditText)findViewById(R.id.txtemail);
        btncreateuser=(Button)findViewById(R.id.bntcreateuser);
        btncreateuser.setOnClickListener(this);

    }//end onCreate

    //variables to hold newuser data
    public void onClick(View v){
        if (v.getId()==R.id.bntcreateuser) {

            uname = txtuname.getText().toString();
            //check password repeat same
            pwd = txtpwd.getText().toString();
            pwd2=txtpwd2.getText().toString();
            if (pwd.equals(pwd2)){
                pwd=pwd2;
            }//check password repeat same value
            else{
                pwd="";
            }

            //userlevel = txtadmin.getText().toString();
            fullname = txtfullname.getText().toString();
            email = txtemail.getText().toString();


            //check all fields provided with data
            if(!uname.equals("") && !pwd.equals("") &&
                    !email.equals("") && !fullname.equals("")){

                        //call the AsyncTask if username,password,email provided data
                        new SaveNewRecord().execute();
            }
        }//end btnsave
    }//onClick

    //****************************** Save record process ASYNCTASK, starts here
    public class SaveNewRecord extends AsyncTask<String, Void, String> {
        //the progressdialog
        ProgressDialog progress = new ProgressDialog(CreateUser.this);
        protected void onPreExecute(){
            //set message of the dialog
            progress.setMessage("Saving record to online database...");
            //show dialog
            progress.show();
            super.onPreExecute();
        }

        protected String doInBackground(String... arg0) {

            try {
                // here is your URL path, change the IP number to
                // point to your own server
                URL url = new URL("http://khirulnizam.com/training/createuser.php");

                JSONObject postDataParams = new JSONObject();
                //add name pair values to the connection
                postDataParams.put("uname", uname);
                postDataParams.put("pwd", pwd);
                postDataParams.put("userlevel", userlevel);
                postDataParams.put("fullname", fullname);
                postDataParams.put("email", email);

                Log.e("params",postDataParams.toString());
                Log.e("URL",url.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");//method=post
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();//execute the connection

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();
                Log.e("responseCode", "responseCode "+responseCode);
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    //code 200 connection OK
                    //code 500 server not responding
                    //code 404 file not found
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
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(), result,
            //Toast.LENGTH_LONG).show();
            //return result;
            //call method to handle after verification
            if(progress != null && progress.isShowing()){
                progress.dismiss();
            }
            saveVerification(result);//call saveVerification to display dialog
        }
    }//end

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

        }
        //Log.i("result",result.toString());
        return result.toString();
    }//end sendPostData

    public void saveVerification(String svrmsg){
        String savemsg="";
        //if new record successfully saved
        Toast.makeText(this, "svrmsg:"+svrmsg, Toast.LENGTH_LONG).show();
        if (svrmsg.equals("success")){

            savemsg="New record succesfully saved.";

        }
        else {//save attempt failed
            savemsg="Fail to save new record!";
        }

        //**********common dialog box
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Saving Record");
        builder1.setMessage(savemsg);
        builder1.setCancelable(false);
        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();
    }
    //****************************** Save record ASYNCTASK, ends here
}
