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

public class View_profile extends AppCompatActivity {
    ImageView e1;
    RadioButton r1,r2;
    EditText e3,e4,e5,e6,e7,e8,e9,e10;
    Button b1;
    SharedPreferences sh;
    String url="",lid="",url1="";
    JSONParser jsonParser=new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        e1=(ImageView)findViewById(R.id.view);
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
        b1=(Button)findViewById(R.id.update);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lid=sh.getString("lid","");
        url=sh.getString("url","")+"profile_view";
        url1=sh.getString("url","")+"update";
        try
        {
           JSONObject json=new JSONObject();
           ArrayList<NameValuePair> param=new ArrayList<>();
           param.add(new BasicNameValuePair("lid",lid));
           json=jsonParser.makeHttpRequest(url,"GET",param);
           String result=json.getString("status");
           if(result.equalsIgnoreCase("1"))
           {
               e3.setText(json.getString("name"));
               e4.setText(json.getString("dob"));
               e5.setText(json.getString("place"));
               e6.setText(json.getString("post"));
               e7.setText(json.getString("pin"));
               e8.setText(json.getString("district"));
               e9.setText(json.getString("email"));
               e10.setText(json.getString("phone"));
               String gen=json.getString("gender");
               if(gen.equalsIgnoreCase("male"))
               {
                   r1.setChecked(true);
               }
               else
               {
                   r2.setChecked(true);
               }
           }
           else
           {
               Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
           }
        }catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }


        //*************update***************

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
                JSONObject json=new JSONObject();

                try {
                    ArrayList<NameValuePair> para =new ArrayList<NameValuePair>();
                    para.add(new BasicNameValuePair("userid",lid));
                    para.add(new BasicNameValuePair("name",name));
                    para.add(new BasicNameValuePair("gender",gender));
                    para.add(new BasicNameValuePair("dob",dob));
                    para.add(new BasicNameValuePair("place",plc));
                    para.add(new BasicNameValuePair("post",post));
                    para.add(new BasicNameValuePair("pin",pin));
                    para.add(new BasicNameValuePair("district",dis));
                    para.add(new BasicNameValuePair("email",email));

                    //Toast.makeText(View_profile.this, para+"", Toast.LENGTH_SHORT).show();
                    json=jsonParser.makeHttpRequest(url1,"GET",para);
                    String res=json.getString("status");
                   // Toast.makeText(View_profile.this, res, Toast.LENGTH_SHORT).show();
                    if (res.equalsIgnoreCase("1"))
                    {
                        Toast.makeText(getApplicationContext(),"successfully updated",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
