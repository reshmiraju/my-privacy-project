package com.example.user.privacy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class Update extends AppCompatActivity {
    ImageView e1;
    RadioButton r1,r2;
    EditText e3,e4,e5,e6,e7,e8,e9,e10;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
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
        b1=(Button)findViewById(R.id.register);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e3.getText().toString();
                String dob=e4.getText().toString();
                String plc=e5.getText().toString();
                String post=e6.getText().toString();
                String pin=e7.getText().toString();
                String dis=e8.getText().toString();
                String email=e9.getText().toString();
                String phone=e10.getText().toString();

            }
        });
    }
}
