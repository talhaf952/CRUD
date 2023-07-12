package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Dbhelper extends SQLiteOpenHelper {
    public static final String student_id="student_id";
    public static final String student_name="student_name";
    public static final String student_roll="student_rollnumber";
    public static final String student_enroll="is_enrolled";
    public static final String student_table="StudentTable";

    public Dbhelper(@Nullable Context context) {
        super(context,"MyDB.db" ,null,1);
    }
    public void onCreate(SQLiteDatabase db){
      String create_table = "CREATE TABLE " + student_table + "(" +student_id
                 + " Integer PRIMARY KEY AUTOINCREMENT, " + student_name+ " Text, "
                + student_roll + " Int, " + student_enroll + " BOOL) ";
        db.execSQL(create_table);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldversio,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + student_table);
        onCreate(db);
    }
    public boolean addstudent(Model model){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(student_name,model.getName());
        cv.put(student_roll,model.getRollnumber());
        cv.put(student_enroll,model.isEnroll());
        try{
        db.insert(student_table,null,cv);

        }
        catch(Exception e)
        {
            return false;
        }
        db.close();
        return true;
    }
    public boolean update(Model model){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(student_name,model.getName());
        cv.put(student_roll,model.getRollnumber());
        cv.put(student_enroll,model.isEnroll());
       try{
           db.update(student_table,cv,student_roll+"="+model.getRollnumber(),null);
       }
       catch(Exception e)
       {
        return false;
       }
        db.close();
       return true;
    }
    public boolean delete(Model model){
        SQLiteDatabase db=this.getWritableDatabase();
        try{
            db.delete(student_table,student_roll+"="+model.getRollnumber() ,null);}
        catch(Exception e){
            return false;
        }
        db.close();
        return true;
    }
    public ArrayList<Model> getAllStudent()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursorCourses=db.rawQuery("Select * FROM "+student_table,null);
        ArrayList<Model> Studentdata=new ArrayList<>();
        if(cursorCourses.moveToFirst()){
            do{
                Studentdata.add(new Model(cursorCourses.getString(1),
                        cursorCourses.getInt(2),cursorCourses.getInt(3)==1?true:false));
            }
            while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return Studentdata;
    }
}
