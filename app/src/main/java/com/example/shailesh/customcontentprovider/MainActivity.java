package com.example.shailesh.customcontentprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText name,city,enrollment;
    Button insert,delete,update,viewall;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.studentname);
        city=findViewById(R.id.studentcity);
        enrollment=findViewById(R.id.studentenrollment);
        insert=findViewById(R.id.insert);
        delete=findViewById(R.id.delete);
        update=findViewById(R.id.update);
        viewall=findViewById(R.id.viewall);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv=new ContentValues();
                cv.put("name","updatename");
                getContentResolver().update(MyProvider.content_uri,cv,"_id=8",
                        null);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentResolver().delete(MyProvider.content_uri,"_id=?",
                        new String[]{name.getText().toString()});
                Toast.makeText(getApplicationContext(),"RECORD DELETED",Toast.LENGTH_LONG).show();
            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Viewall.class);
                startActivity(i);
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv=new ContentValues();
                cv.put("name",name.getText().toString());
                cv.put("city",city.getText().toString());
                cv.put("enrollment",enrollment.getText().toString());
                getContentResolver().insert(MyProvider.content_uri,cv);
                Toast.makeText(getApplicationContext(),"RECORD INSERTED",Toast.LENGTH_LONG).show();
                clear();
            }
        });
    }

    private void clear()
    {
        name.setText("");
        city.setText("");
        enrollment.setText("");
    }
}
