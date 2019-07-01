package com.example.heriana.javaaplikasi.Core.JavaFundamental;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbmateri1";
    public static final String NAMA = "nama";
    public static final String KEY_ID = "_id";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // method createTable untuk membuat table MATERI1
    public void createTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS MATERI1");
        db.execSQL("CREATE TABLE if not exists MATERI1 (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nama TEXT);");
    }

    // method generateData untuk mengisikan data ke table MATERI1.
    public void generateData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(NAMA, "1. Java Introduction");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "2. History of Java");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "3. Features of Java");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "4. How to set path in Java");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "5. JDK, JRE, JVM");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "6. Java Variables");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "7. Data Types in Java");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "8. Operators in Java");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "9. Java if-else statement");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "10. Java Switch statement");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "11. For loops in Java");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "12. Java do-while Loop");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "13. Java Break Statement");
        db.insert("MATERI1", NAMA, cv);
        cv.put(NAMA, "14. Java Command Line Arguments");
        db.insert("MATERI1", NAMA, cv);
    }

    // method delAllAdata untuk menghapus data di table MATERI1.
    public void delAllData(SQLiteDatabase db) {
        db.delete("MATERI1", null, null);
    }

    public Cursor fetchAllWisata(SQLiteDatabase db) {
        return db.query("MATERI1", new String[] { KEY_ID, NAMA }, null, null,
                null, null, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
