package com.example.studentmanagment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class StudentdatabaseSource {

// studentdatabaseHelper studentdatabasehelper;
//SQLiteOpenHelper sqLiteOpenHelper;
//StudentModel studentModel;
//SQLiteDatabase sqLiteDatabase;
//
//    public StudentdatabaseSource(Context context){
//
// studentdatabasehelper = new studentdatabaseHelper (context);
//
//    }
//
//    public  void open(){
//
//
//        sqLiteDatabase = studentdatabasehelper.getWritableDatabase ();
//    }
//    public  void close(){
//
//        studentdatabasehelper.close ();
//    }
//    public boolean AddStudent(StudentModel studentModel){
//
//        this.open ();
//
//        ContentValues contentValues = new ContentValues ();
//
//        contentValues.put (studentdatabaseHelper.COL_NAME,studentModel.getName ());
//        contentValues.put (studentdatabaseHelper.COl_AGE, studentModel.getAge ());
//        contentValues.put (studentdatabaseHelper.Col_ADDRESS, studentModel.getAddress ());
//
//
//      Long insertRow =  sqLiteDatabase.insert (studentdatabaseHelper.STUDENT_TABLE,null, contentValues);
//
//      this.close ();
//      if(insertRow > 0){
//          return true;
//      }else
//          return false;
//
//    }
    studentdatabaseHelper studentdatabasehelper;
    SQLiteOpenHelper sqLiteOpenHelper;
    StudentModel studentModel;
    SQLiteDatabase sqLiteDatabase;
    public StudentdatabaseSource(Context context){
         studentdatabasehelper = new studentdatabaseHelper (context);

    }
    public  void open(){
        sqLiteDatabase = studentdatabasehelper.getWritableDatabase ();
    }
    public void close(){
        studentdatabasehelper.close ();

    }
    public boolean AddStudent(StudentModel studentModel){

        this.open ();

        ContentValues contentValues = new ContentValues ();
        contentValues.put (studentdatabaseHelper.COL_NAME, studentModel.getName ());
        contentValues.put (studentdatabaseHelper.COl_AGE, studentModel.getAge ());
        contentValues.put (studentdatabaseHelper.Col_ADDRESS, studentModel.getAddress());
        contentValues.put (studentdatabaseHelper.COL_id, studentModel.getId ());
        Long insertRow = sqLiteDatabase.insert (studentdatabaseHelper.STUDENT_TABLE, null, contentValues);
        this.close ();
        if(insertRow > 0){
            return true;
        }else
            return false;
    }
    //student list update 3rd step

    public boolean UpdateStudent(StudentModel studentModel) {
        this.open ();

        ContentValues contentValues = new ContentValues ();
        contentValues.put (studentdatabaseHelper.COL_NAME, studentModel.getName ());
        contentValues.put (studentdatabaseHelper.COl_AGE, studentModel.getAge ());
        contentValues.put (studentdatabaseHelper.Col_ADDRESS, studentModel.getAddress ());

      int updateRow =   sqLiteDatabase.update (studentdatabaseHelper.STUDENT_TABLE, contentValues, studentdatabaseHelper.COL_id + " =?",
                new String[]{String.valueOf (studentModel.getId ())});
      this.close ();

      if(updateRow > 0){
          return  true;
      }else
          return  false;


    }

    //table show coding

    public ArrayList<StudentModel>  getAllStudent(){
         this.open ();
        ArrayList<StudentModel> arrayList = new ArrayList<> ();
        //Select from student_table

        Cursor cursor = sqLiteDatabase.query(Boolean.parseBoolean (studentdatabasehelper.STUDENT_TABLE),null,null,null,null,null,null,null,null);

        if(cursor.moveToFirst ()){
            do {
            String name = cursor.getString (cursor.getColumnIndex (studentdatabasehelper.COL_NAME));
            int age = cursor.getInt (cursor.getColumnIndex (studentdatabasehelper.COl_AGE));
            String address = cursor.getString (cursor.getColumnIndex (studentdatabasehelper.Col_ADDRESS));
             int id = cursor.getInt(cursor.getColumnIndex (studentdatabasehelper.COL_id));

            StudentModel studentModel = new StudentModel (name, id,age,address);


            arrayList.add (studentModel);
            }
            while (cursor.moveToNext ());

            }
            this.close ();
            cursor.close ();

          return  arrayList;
    }


}
