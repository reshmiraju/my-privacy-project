package com.example.user.privacy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
ImageView e1;
RadioButton r1,r2;
EditText e3,e4,e5,e6,e7,e8,e9,e10,e11;
    SharedPreferences sh;
    String url="";
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e3=(EditText)findViewById(R.id.name);
        r1=(RadioButton)findViewById(R.id.male);
        r2=(RadioButton)findViewById(R.id.female);
        e4=(EditText)findViewById(R.id.dob);
        e5=(EditText)findViewById(R.id.place);
        e6=(EditText)findViewById(R.id.post);
        e7=(EditText)findViewById(R.id.pin);
        e8=(EditText)findViewById(R.id.district);
        e9=(EditText)findViewById(R.id.email);
        e10=(EditText)findViewById(R.id.phone);
        e1=(ImageView)findViewById(R.id.image);
        e11=(EditText)findViewById(R.id.password);
        b1=(Button)findViewById(R.id.register);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"user_registration";
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e3.getText().toString();
                String gender="";
                if (r1.isChecked())
                {
                    gender="male";
                }
                else {
                    gender="female";
                }

                String dob=e4.getText().toString();
                String plc=e5.getText().toString();
                String post=e6.getText().toString();
                String pin=e7.getText().toString();
                String dis=e8.getText().toString();
                String email=e9.getText().toString();
                String phone=e10.getText().toString();
                String password=e11.getText().toString();

                JSONObject json=new JSONObject();
                JSONParser jsonParser=new JSONParser();
                try {
                    ArrayList<NameValuePair> para =new ArrayList<NameValuePair>();

                    para.add(new BasicNameValuePair("name",name));
                    para.add(new BasicNameValuePair("gender",gender));
                    para.add(new BasicNameValuePair("dob",dob));
                    para.add(new BasicNameValuePair("place",plc));
                    para.add(new BasicNameValuePair("post",post));
                    para.add(new BasicNameValuePair("pin",pin));
                    para.add(new BasicNameValuePair("district",dis));
                    para.add(new BasicNameValuePair("email",email));
                    para.add(new BasicNameValuePair("phone",phone));
                    para.add(new BasicNameValuePair("password",password));
                    json=jsonParser.makeHttpRequest(url,"GET",para);
                    String res=json.getString("status");
                    Toast.makeText(Register.this, res, Toast.LENGTH_SHORT).show();
                    if (res.equalsIgnoreCase("1"))
                    {
                        Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();

                        Intent i=new Intent(getApplicationContext(),user_home.class);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }






            }
        });

    }
}
