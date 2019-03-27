package com.example.user.privacy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class comment extends AppCompatActivity {
    EditText e1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        e1=(EditText)findViewById(R.id.comment);
        b1=(Button)findViewById(R.id.send);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmt=e1.getText().toString();
                Toast.makeText(getApplicationContext(),cmt,Toast.LENGTH_LONG).show();
            }
        });

    }
}
