package com.example.mydbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Student>students = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        textView = findViewById(R.id.textView);
        try {
            //SQLiteDatabase database = databaseHelper.getReadableDatabase();
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            ContentValues cval = new ContentValues();
            cval.put("email","info@polimdo.ac.id");
            database.update("students",cval,"email=?",new String[]{"rangga@yahoo.com"});
            /* Perintah untuk menghapus record*/
            /*
            int jlhbaris = database.delete("students","name=?",new String[]{"Umar wardani"});
            Toast.makeText(this, "Jumlah baris yang dihapus" + jlhbaris, Toast.LENGTH_SHORT).show();

             */
//
//            ContentValues values = new ContentValues();
//            values.put("name","Budi sanjaya");
//            values.put("email","rangga@yahoo.com");
//            database.insert("students",null,values);
            //Cursor cursor = database.query("students",null,null,null,null,null,null);
            //Cursor cursor = database.query("students",null,"name=? or name=?",new String[]{"Rangga Saerang","Veltie Wensen"},null,null,null);
            Cursor cursor = database.rawQuery("select * from students",null);
            if(null != cursor){
                if(cursor.moveToFirst()){
                    for(int i=0; i<cursor.getCount();i++){
                        Student s = new Student();
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                        String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                        s.setId(id);
                        s.setName(name);
                        s.setEmail(email);
                        students.add(s);
                        cursor.moveToNext();
                    }
                    cursor.close();
                    database.close();
                }
                else{
                    cursor.close();
                    database.close();
                }
            }
            else{
                database.close();
            }

        } catch (SQLException e) {

        }
        String text = "";
        for(Student s : students){
            text+= s.getId()+"\n"+s.getName()+"\n"+s.getEmail()+"*****\n";
        }
        textView.setText(text);
    }
}