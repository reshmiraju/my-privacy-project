package com.example.user.privacy;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class View_request extends AppCompatActivity {
    ListView l1;
    SharedPreferences sh;
    String url="",lid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);
        l1=(ListView)findViewById(R.id.list);
        lid=sh.getString("lid","");
        url=sh.getString("url","")+"view_frnd_request";
        JSONObject json=new JSONObject();
        JSONParser jsonParser=new JSONParser();
            try {
                ArrayList<NameValuePair> para =new ArrayList<NameValuePair>();
                para.add(new BasicNameValuePair("userid",lid));
                //Toast.makeText(this, para+"", Toast.LENGTH_SHORT).show();
                json=jsonParser.makeHttpRequest(url,"GET",para);
                String res=json.getString("status");

            }catch (Exception e)
        {

        }



    }
}
