package com.example.audiosabe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String  DATABASE_NAME = "MiBase";
    public static final String TABLE_NAME = "audios";
    public static final String TABLE_NAME2 = "barras";
    public static final String COL_4 = "ID";
    public static final String COL_5 = "numero";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "nombre";
    public static final String COL_3 =  "numero";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null , 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" ("+COL_1+" INTEGER PRIMARY KEY , "+COL_2+"  TEXT , "+COL_3+" INTEGER )");
        db.execSQL("CREATE TABLE " + TABLE_NAME2 +" ("+COL_4+" INTEGER PRIMARY KEY , "+COL_5+" INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);//me hace ruido
    }

    public boolean existe(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String juan = id;
        String query = "SELECT * from "+TABLE_NAME+ " where numero = "+juan;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() <= 0){
            return false;
        }
        return true;
    }
    //esta funcion hay que eitarla pero por ahira la dejo asi porque no la voy a usar
    public Cursor getTodo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor gettextbd(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String juan = Integer.toString(id);
        Cursor res = db.rawQuery("select nombre from "+TABLE_NAME+" WHERE numero = "+ juan ,null);
        return res;
    }

    public Cursor getkey(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String juan = Integer.toString(id);
        Cursor res = db.rawQuery("select ID from "+TABLE_NAME+" WHERE numero = "+ juan ,null);
        return res;
    }

    public Cursor getkey2(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String juan = Integer.toString(id);
        Cursor res = db.rawQuery("select ID from "+TABLE_NAME2+" WHERE numero = "+ juan ,null);
        return res;
    }

    public Cursor getNumero(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String juan = Integer.toString(id);
        Cursor res = db.rawQuery("select numero from "+TABLE_NAME2+" WHERE ID = "+ juan ,null);
        return res;
    }

    public boolean insertData2(int id,int numero) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4,id);
        contentValues.put(COL_5,numero);

        long result = db.insert(TABLE_NAME2,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean existe2(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String juan = id;
        String query = "SELECT * from "+TABLE_NAME2+ " where numero = "+juan;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() <= 0){
            return false;
        }
        return true;
    }

    public boolean insertData(int id,String nombre,int numero) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,numero);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateNombre(int id,String uri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3,id);
        contentValues.put(COL_2,uri);
        String juan = Integer.toString(id);

        db.update(TABLE_NAME, contentValues, "numero = ?",new String[] {juan });
        return true;
    }



}
