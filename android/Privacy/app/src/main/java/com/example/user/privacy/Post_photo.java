package com.example.user.privacy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Post_photo extends AppCompatActivity {
ImageView e1;
EditText e2,e3;
Button b1,b2,b3,b4,b5,b6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_photo);
        e1=(ImageView)findViewById(R.id.img);
        e2=(EditText)findViewById(R.id.content);
        e3=(EditText)findViewById(R.id.location);
        b1=(Button)findViewById(R.id.post);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cnt=e2.getText().toString();
                String lctn=e3.getText().toString();
                Toast.makeText(getApplicationContext(),cnt,Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),lctn,Toast.LENGTH_LONG).show();
            }
        });
    }
}
