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

public class Complaint extends AppCompatActivity {
EditText e1;
Button b1,b3,b5;
    SharedPreferences sh;
    String url="",lid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        e1=(EditText)findViewById(R.id.complaints);
        b1=(Button)findViewById(R.id.send);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lid=sh.getString("lid","");
        url=sh.getString("url","")+"complaint";
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmplt=e1.getText().toString();
                JSONObject json=new JSONObject();
                JSONParser jsonParser=new JSONParser();
                try {
                    ArrayList<NameValuePair> para =new ArrayList<NameValuePair>();
                    para.add(new BasicNameValuePair("userid",lid));
                    para.add(new BasicNameValuePair("complaint",cmplt));
                    json=jsonParser.makeHttpRequest(url,"GET",para);
                    String res=json.getString("status");
                    if(res.equalsIgnoreCase("1"))
                    {
                        Toast.makeText(Complaint.this, "success", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),user_home.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(Complaint.this, "failed", Toast.LENGTH_SHORT).show();
                    }

                    }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
