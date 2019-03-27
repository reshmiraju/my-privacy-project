package com.example.user.privacy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IP extends AppCompatActivity {
EditText ed;
    Button b;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        ed=(EditText)findViewById(R.id.editText);
        b=(Button)findViewById(R.id.button);
       // ed.setText(sh.getString("ip",""));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip=ed.getText().toString();
                String url="http://"+ip+":8080/myprivacy/";
                sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor edt=sh.edit();
                edt.putString("ip",ip);
                edt.putString("url",url);
                edt.commit();
                Intent in=new Intent(getApplicationContext(),Login.class);
                startActivity(in);
            }
        });
    }
}
