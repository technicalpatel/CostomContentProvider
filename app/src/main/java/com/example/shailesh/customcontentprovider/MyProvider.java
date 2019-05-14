package com.example.shailesh.customcontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyProvider extends ContentProvider
{
    final static String PROVIDER_NAME="com.example.shailesh.customcontentprovider/MyProvider";
    final static String URL="content://"+PROVIDER_NAME+"/student";
    static final Uri content_uri=Uri.parse(URL);
    SQLiteDatabase db;
    @Override
    public boolean onCreate()
    {

        MyHelper helper=new MyHelper(getContext());
        db=helper.getWritableDatabase();
        return (db!=null)?false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Cursor cursor;
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables("student");
        cursor=qb.query(db,projection,selection,selectionArgs,null,
                null,sortOrder);
        Log.d("RETURN FINAL","RETURN FINAL");
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values)
    {
        db.insert("student",null,values);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs)
    {
        db.delete("student",selection,selectionArgs);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues values,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs)
    {
        db.update("student",values,selection,selectionArgs);
        return 0;
    }
}
