package com.example.studentmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName, etAge, etAddress;
    Button button, showStudent;
    StudentdatabaseSource studentdatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        studentdatabaseSource = new StudentdatabaseSource (this);
        StudentModel studentModel;

        etName = findViewById (R.id.etname);
        etAge = findViewById (R.id.etage);
        etAddress = findViewById (R.id.etaddress);

        button = findViewById (R.id.btn1);
        showStudent = findViewById (R.id.show_student);

        //student list update
        studentModel = (StudentModel) getIntent ().getSerializableExtra ("Student");


        if (studentModel != null) {
            button.setText ("Update Button");
            //2nd step
            etName.setText (studentModel.getName ());
            etAge.setText (studentModel.getAge () + " ");
            etAddress.setText (studentModel.getAddress ());

        }

        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                // student update list

                if (studentModel != null) {

                    String updateName = etName.getText ().toString ();
                    int updateAge = Integer.valueOf (etAge.getText ().toString ());
                    String updateAddress = etAddress.getText ().toString ();
                    int id = studentModel.getId ();

                    StudentModel updateStudentModel = new StudentModel (id, updateName, updateAge, updateAddress);
                    Boolean updateStaus = studentdatabaseSource.UpdateStudent (updateStudentModel);

                    if (updateStaus) {
                        Toast.makeText (MainActivity.this, "Update Successfull", Toast.LENGTH_SHORT).show ();
                    } else
                        Toast.makeText (MainActivity.this, "Not Update", Toast.LENGTH_SHORT).show ();


                } else {


                    StudentModel studentModel = new StudentModel (etName.getText ().toString (), Integer.valueOf (etAge.getText ().toString ()), etAddress.getText ().toString ());

                    boolean status = studentdatabaseSource.AddStudent (studentModel);
                    if (status) {
                        Toast.makeText (MainActivity.this, "Saved", Toast.LENGTH_SHORT).show ();
                    } else
                        Toast.makeText (MainActivity.this, "Not saved ", Toast.LENGTH_SHORT).show ();
                }
            }
        });
        // show data
        showStudent.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, StudentListActivity.class);
                startActivity (intent);

            }
        });

    }


    }
