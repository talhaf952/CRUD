package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonadd, buttonView, buttonUpdate,buttondelete;
    EditText editname, editRollNumber;
    Switch switchIsActive;
    ListView listViewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonadd = findViewById(R.id.Add);
        buttonView = findViewById(R.id.view);
        buttonUpdate = findViewById(R.id.update);
        buttondelete=findViewById(R.id.Delete);
        editname = findViewById(R.id.name);
        editRollNumber = findViewById(R.id.Roll_Number);
        switchIsActive = findViewById(R.id.enroll);
        listViewStudent = findViewById(R.id.listview);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            Model model;

            public void onClick(View v) {
                try {
                    model = new Model(editname.getText().toString(), Integer.parseInt(editRollNumber.getText().toString()), switchIsActive.isChecked());
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
                Dbhelper db = new Dbhelper(MainActivity.this);
               boolean b=db.addstudent(model);
               if(b){
                   Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
               }
            }
        });
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dbhelper db = new Dbhelper(MainActivity.this);
                List<Model> list = db.getAllStudent();
                ArrayAdapter arra = new ArrayAdapter<Model>
                        (MainActivity.this, android.R.layout.simple_list_item_1, list);
                listViewStudent.setAdapter(arra);
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            Model model;

            public void onClick(View view) {
                try {
                    model = new Model(editname.getText().toString(), Integer.parseInt(editRollNumber.getText().toString()), switchIsActive.isChecked());
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
                Dbhelper db = new Dbhelper(MainActivity.this);
                boolean b = db.update(model);
                if (b == false) {
                    Toast.makeText(MainActivity.this, "error",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Updated",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        buttondelete.setOnClickListener(new View.OnClickListener() {
            Model model;

            public void onClick(View view) {
                try {
                    model = new Model(editname.getText().toString(), Integer.parseInt(editRollNumber.getText().toString()), switchIsActive.isChecked());
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
                Dbhelper db = new Dbhelper(MainActivity.this);
                boolean b = db.delete(model);
                if (b == false) {
                    Toast.makeText(MainActivity.this, "Error",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Deleted",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
