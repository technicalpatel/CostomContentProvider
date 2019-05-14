package com.example.shailesh.customcontentprovider;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Viewall extends AppCompatActivity
{
    ListView list;
    Cursor rs;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        list=findViewById(R.id.list);

        String proj[]={"_id","name","city"};
        rs=getContentResolver().query(MyProvider.content_uri,proj,
                null,null,null);
        while (rs.moveToNext())
        {
            Toast.makeText(getApplicationContext(),rs.getString(0)+"\n"
                    +rs.getString(1)+"\n"+
                    rs.getString(2),Toast.LENGTH_LONG).show();
        }

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(getApplicationContext(),
                android.R.layout.simple_expandable_list_item_2,
                rs,new String[]{"_id","name"},new int[]{android.R.id.text1,android.R.id.text2});
        list.setAdapter(adapter);

    }
}
