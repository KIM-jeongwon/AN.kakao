package com.bitcamp.app.kakao.mapper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 1027 on 2018-02-06.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="kakao.db";
    public static final String TABLE_NAME="member";
    public static final String column_1="userid";
    public static final String column_2="password";
    public static final String column_3="name";
    public static final String column_4="email";
    public static final String column_5="phone";
    public static final String column_6="profile_photo";
    public static final String column_7="address";

    public DatabaseHelper(Context context) {
        super(context, "bitcamp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE kakao.db");
        onCreate(db);
    }
}