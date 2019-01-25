package com.khirulnizam.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    public void onClick(View v){
        String msg="Welcome ";
        msg = msg+txtusername.getText().toString();
        msg=msg+"\nPassword ";
        msg =msg+txtpassword.getText().toString();
        //popup
        Toast.makeText(getApplicationContext(),msg,
                Toast.LENGTH_LONG).show();
    }//end onClick
    //
}
