package com.example.user.privacy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    SharedPreferences sh;
    String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.username);
        e2=(EditText)findViewById(R.id.password);
        b1=(Button)findViewById(R.id.login);
        b2=(Button)findViewById(R.id.register);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"login";
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(),Register.class);
                startActivity(in);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=e1.getText().toString();
                String pwd=e2.getText().toString();
                JSONObject json=new JSONObject();
                JSONParser jsonParser=new JSONParser();
                try {
                    ArrayList<NameValuePair> para =new ArrayList<NameValuePair>();
                    para.add(new BasicNameValuePair("uname",uname));
                    para.add(new BasicNameValuePair("pwd",pwd));
                    json=jsonParser.makeHttpRequest(url,"GET",para);
                    String res=json.getString("status");
                    if (res.equalsIgnoreCase("1"))
                    {
                        Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                        String lid=json.getString("lid");
                        SharedPreferences.Editor ed=sh.edit();
                        ed.putString("lid",lid);
                        ed.commit();
                        Intent i=new Intent(getApplicationContext(),user_home.class);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"invalid user",Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
