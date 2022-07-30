package com.example.studentmanagment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class studentdatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public  static  final  String DATABASE_NAME = "student.db";
    public static  final int DATABASE_VIRSION = 1;

    public static final  String STUDENT_TABLE = "studemt table";
    public static final  String COL_id = "id";
    public static final String COL_NAME = "name";
    public static final String COl_AGE = "age";
    public  static final String Col_ADDRESS = "address";


    public static final String CREATE_TABLE = "create table"+ STUDENT_TABLE+"("+COL_id+"INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_NAME + "TEXT, "+COl_AGE+ "INTEGER, "+ Col_ADDRESS + "TEXT" + ")";

    public studentdatabaseHelper( Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VIRSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
 db.execSQL (CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL ("Drop table if exists" + STUDENT_TABLE);
this.onCreate (db);
    }



}
