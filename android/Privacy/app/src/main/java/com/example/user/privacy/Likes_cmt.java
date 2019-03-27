package com.example.user.privacy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Likes_cmt extends AppCompatActivity {
    ImageView v1;
    EditText e1,e2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes_cmt);
        v1=(ImageView)findViewById(R.id.img);
        e1=(EditText)findViewById(R.id.content);
        e2=(EditText)findViewById(R.id.location);
        b1=(Button)findViewById(R.id.like);
        b2=(Button)findViewById(R.id.cmt);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cnt=e1.getText().toString();
                String lcn=e2.getText().toString();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(),comment.class);
                startActivity(in);
            }
        });
    }
}
