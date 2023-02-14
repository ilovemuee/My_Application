package com.example.myapplication;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.widget.Toast;
import org.w3c.dom.Text;
class MainHelper extends SQLiteOpenHelper {
    public MainHelper(Context context){
        super(context,"userdata.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(id text primary key,question text,answer text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public boolean insertData(String id, String question, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("question", question);
        contentValues.put("answer",answer);
        long result = db.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean update(String id, String question, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", question);
        contentValues.put("dob", answer);
        long result = db.update("Userdetails", contentValues, "id=?", new String[]{id});
        db.close();
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteUserdata(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Userdetails","question=?",new String[]{id});
        db.close();
        if(result == -1){
            return true;
        }
        else{
            return false;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from Userdetails",null);
        return result;

    }
}
