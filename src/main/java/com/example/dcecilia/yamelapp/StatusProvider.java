package com.example.dcecilia.yamelapp;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class StatusProvider extends ContentProvider {
    private DbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return (dbHelper != null) ? true : false;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insertWithOnConflict(StatusContract.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        if (id > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
            return ContentUris.withAppendedId(uri, id);
        } else {
            return null;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(StatusContract.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri( getContext().getContentResolver(), uri);

        return cursor;
    }
}


class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, StatusContract.DB_NAME, null, StatusContract.DB_VERSION);
    }

    /** Called only once to create the database first time. */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s (%s int primary key, %s int, %s text, %s text)",
                StatusContract.TABLE, StatusContract.Columns._ID, StatusContract.Columns.CREATED_AT, StatusContract.Columns.USER, StatusContract.Columns.TEXT);
        Log.d("DbHelper", "SQL: "+sql);
        db.execSQL(sql);
    }

    /** Called every time oldVersion != newVersion. */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Typically you do ALTER TABLE ..., but we don't have previous userbase
        db.execSQL("drop table if exists " + StatusContract.TABLE);
        this.onCreate(db);
    }
}