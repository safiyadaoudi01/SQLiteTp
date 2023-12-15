package com.example.projetsqllite.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.projetsqllite.beans.Marque;
import com.example.projetsqllite.beans.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private Context context;

    private static final String TABLE_USER = "user";


    private static final String Id = "id";
    private static final String First = "firstname";

    private static final String Last = "lastname";
    private static final String Email = "email";
    private static final String Pswd= "password";


    private MyDataBaseHelper helper;

    public UserService(Context context) {
        this.helper = new MyDataBaseHelper(context);
        this.context = context;
    }

    public void create(User e) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(First, e.getFirstName());
        values.put(Last, e.getLastName());
        values.put(Email, e.getEmail());
        values.put(Pswd, e.getPassword());
        long result=db.insert(TABLE_USER,
                null,
                values);
        if(result == -1){
            Toast.makeText(context, "Failed to add this user", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "new user Added Successfully!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public List<User> findAll(){
        List<User> eds = new ArrayList<>();
        String req ="select * from "+TABLE_USER;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c = db.rawQuery(req, null);
        User e = null;
        if(c.moveToFirst()){
            do{
                e = new User();
                e.setId(c.getInt(0));
                e.setFirstName(c.getString(1));
                e.setLastName(c.getString(2));
                e.setEmail(c.getString(3));
                e.setPassword(c.getString(4));
                eds.add(e);
            }while(c.moveToNext());
        }
        return eds;
    }
}
