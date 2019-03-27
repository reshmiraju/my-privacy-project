package com.example.user.privacy;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class Search_frnd extends AppCompatActivity implements AdapterView.OnItemClickListener {
EditText e1;
ListView l1;
public static int pos;
    SharedPreferences sh;
    String url="",lid,nn;
   public static ArrayList<String> uname,mail,phn,pic,ulid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_frnd);
        e1=(EditText)findViewById(R.id.frnd);
        l1=(ListView)findViewById(R.id.list);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lid=sh.getString("lid","");
        url=sh.getString("url","")+"Search_frnd";
        searchFriend("");
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nn=e1.getText().toString();
                searchFriend(nn);

            }

            @Override
            public void afterTextChanged(Editable editable) {

              //  searchFriend(e1.getText().toString());
            }
        });
    }

    private void searchFriend(String name){
        JSONObject json=new JSONObject();
        JSONParser jsonParser=new JSONParser();

        try {
            ArrayList<NameValuePair> para =new ArrayList<NameValuePair>();
            para.add(new BasicNameValuePair("userid",lid));
            para.add(new BasicNameValuePair("name",name));
            //Toast.makeText(this, para+"", Toast.LENGTH_SHORT).show();
            json=jsonParser.makeHttpRequest(url,"GET",para);
            String res=json.getString("status");
            if(res.equalsIgnoreCase("1"))
            {
                //Toast.makeText(Search_frnd.this,"success",Toast.LENGTH_LONG).show();
                uname=new ArrayList<String>();
                mail=new ArrayList<String>();
                phn=new ArrayList<String>();
                pic=new ArrayList<String>();
                ulid=new ArrayList<String>();
                JSONArray ja=json.getJSONArray("result");
                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo=ja.getJSONObject(i);
                    uname.add(jo.getString("uname"));
                    mail.add(jo.getString("mail"));
                    phn.add(jo.getString("phn"));
                    pic.add(jo.getString("pic"));
                    ulid.add(jo.getString("ulid"));
                }
                ArrayAdapter<String> ad=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,uname);
                l1.setAdapter(ad);
                l1.setOnItemClickListener(this);

            }
            else
            {
                Toast.makeText(Search_frnd.this,"failed",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        pos=i;
        final CharSequence[] options = {"Details", "Report","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(Search_frnd.this);
        builder.setTitle("");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Details"))
                {
                    Intent i=new Intent(getApplicationContext(),Request.class);
                    startActivity(i);

                }

                else if (options[item].equals("Report"))
                {
                    Intent i=new Intent(getApplicationContext(),Report.class);
                    startActivity(i);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();


    }
}
