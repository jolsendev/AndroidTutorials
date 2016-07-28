package com.example.root.sqlitetest1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test1.sb", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("DROP TABLE contacts;");
        sqLiteDatabase.execSQL("create table contacts(name text, phone integer, email text);");
        sqLiteDatabase.execSQL("insert into contacts values('Jamie', 123334, 'Jamie@mmm.com');");
        sqLiteDatabase.execSQL("insert into contacts values('Tim', 6666666, 'Tim@mmm.com');");
        sqLiteDatabase.execSQL("insert into contacts values('Fred', 987654, 'Fred@mmm.com');");
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM contacts", null);
        if(query.moveToFirst()){
            do{
                //Cycle through all records
                String name = query.getString(0);
                int phone = query.getInt(1);
                String email = query.getString(2);
                Toast.makeText(getBaseContext(), "Name = "+name+", Phone = "+phone+", Email = "+email, Toast.LENGTH_LONG).show();
            }while (query.moveToNext());

        }else{
            Toast.makeText(getBaseContext(), "Error connecting to database", Toast.LENGTH_LONG).show();
        }

        sqLiteDatabase.close();



    }
}
