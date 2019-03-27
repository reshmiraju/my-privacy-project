package com.example.user.privacy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Pvcy extends AppCompatActivity {
    RadioButton r1,r2,r3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvcy);
        r1=(RadioButton)findViewById(R.id.pblc);
        r2=(RadioButton)findViewById(R.id.frnd);
        r1=(RadioButton)findViewById(R.id.prtd);
        b1=(Button)findViewById(R.id.send);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type="";
                if(r1.isChecked())
                {
                    type="Public";
                }
                else if (r2.isChecked())
                {
                    type="Friend";
                }
                else
                {
                    type="Protected";
                }
            }
        });
    }

}
