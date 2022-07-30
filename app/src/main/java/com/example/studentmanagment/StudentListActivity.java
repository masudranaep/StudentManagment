package com.example.studentmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {

    StudentdatabaseSource source;
    ListView listView;
    ArrayList<StudentModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_student_list);

        listView = findViewById (R.id.studentlistView);
        source = new StudentdatabaseSource (this);
        arrayList = source.getAllStudent ();



        StudentdatabaseSource source = new StudentdatabaseSource (this);
        StudentAdapter studentAdapter = new StudentAdapter (this, arrayList);
        listView.setAdapter (studentAdapter);

//student list update

        listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentModel studentModel = arrayList.get (position);
                Intent intent = new Intent (StudentListActivity.this, MainActivity.class);
                intent.putExtra ("Student", studentModel);
                startActivity (intent);


            }
        });
    }
}