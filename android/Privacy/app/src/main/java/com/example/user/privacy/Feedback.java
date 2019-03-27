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

public class Feedback extends AppCompatActivity {
EditText e1;
Button b1;
    SharedPreferences sh;
    String url="",lid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        e1=(EditText)findViewById(R.id.feedback);
        b1=(Button)findViewById(R.id.send);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lid=sh.getString("lid","");
        url=sh.getString("url","")+"feedback";
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fdbk=e1.getText().toString();
                JSONObject json=new JSONObject();
                JSONParser jsonParser=new JSONParser();
                try {
                    ArrayList<NameValuePair> para =new ArrayList<NameValuePair>();
                    para.add(new BasicNameValuePair("feedback",fdbk));
                    para.add(new BasicNameValuePair("userid",lid));
                    json=jsonParser.makeHttpRequest(url,"GET",para);
                    String res=json.getString("status");
                    if(res.equalsIgnoreCase("1"))
                    {
                        Toast.makeText(Feedback.this,"success",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplicationContext(),user_home.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(Feedback.this,"failed",Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
