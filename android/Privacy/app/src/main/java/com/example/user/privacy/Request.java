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
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class Request extends AppCompatActivity {
    ImageView v1;
    EditText e1,e2;
    Button b1;
    SharedPreferences sh;
    String url="",lid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        v1=(ImageView)findViewById(R.id.img);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.email);
        b1=(Button)findViewById(R.id.send);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lid=sh.getString("lid","");

        url=sh.getString("url","")+"send_request";
        e1.setText(Search_frnd.uname.get(Search_frnd.pos));
        e2.setText(Search_frnd.mail.get(Search_frnd.pos));
        String pic=Search_frnd.pic.get(Search_frnd.pos);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmplt=e1.getText().toString();
                JSONObject json=new JSONObject();
                JSONParser jsonParser=new JSONParser();
                try {
                    ArrayList<NameValuePair> para =new ArrayList<>();
                    para.add(new BasicNameValuePair("userid",lid));
                    para.add(new BasicNameValuePair("toid",Search_frnd.ulid.get(Search_frnd.pos)));
                    json=jsonParser.makeHttpRequest(url,"GET",para);
                    String res=json.getString("status");
                    if(res.equalsIgnoreCase("1"))
                    {
                        Toast.makeText(Request.this, "Request sended", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),user_home.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(Request.this, "failed", Toast.LENGTH_SHORT).show();
                    }


                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
