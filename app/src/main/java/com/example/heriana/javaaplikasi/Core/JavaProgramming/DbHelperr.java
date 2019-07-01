package com.example.heriana.javaaplikasi.Core.JavaProgramming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperr extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbmateri2";
    public static final String NAMA = "nama";
    public static final String KEY_ID = "_id";


    public DbHelperr(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // method createTable untuk membuat table MATERI1
    public void createTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS MATERI2");
        db.execSQL("CREATE TABLE if not exists MATERI2 (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nama TEXT);");
    }

    // method generateData untuk mengisikan data ke table MATERI1.
    public void generateData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(NAMA, "1. Java OOPs Concepts");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "2. Objects And Classes in Java");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "3. Constructors in Java");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "4. Inheritance in Java");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "5. Method Overloading in Java");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "6. Method Overriding in Java");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "7. Polymorphism in Java");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "8. Abstract class in Java");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "9. Interface in Java");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "10. Java Package");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "11. Encapsulation in Java");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "12. Java Array, Java String");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "13. Java I/O Tutorial");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "14. Java Writer");
        db.insert("MATERI2", NAMA, cv);
        cv.put(NAMA, "15. Java Reader");
        db.insert("MATERI2", NAMA, cv);
    }

    // method delAllAdata untuk menghapus data di table MATERI1.
    public void delAllData(SQLiteDatabase db) {
        db.delete("MATERI2", null, null);
    }

    public Cursor fetchAllWisata(SQLiteDatabase db) {
        return db.query("MATERI2", new String[] { KEY_ID, NAMA }, null, null,
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
