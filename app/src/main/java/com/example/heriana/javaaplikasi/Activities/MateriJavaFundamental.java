package com.example.heriana.javaaplikasi.Activities;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.heriana.javaaplikasi.Core.JavaFundamental.DbHelper;
import com.example.heriana.javaaplikasi.R;

public class MateriJavaFundamental extends Activity {
    private DbHelper dbhelper;
    private SQLiteDatabase db = null;
    private ListView listContent = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhelper = new DbHelper(this);
        db = dbhelper.getWritableDatabase();
        dbhelper.delAllData(db);
        dbhelper.generateData(db);
        setContentView(R.layout.actvity_materijavafundamental);
        listContent = (ListView) findViewById(R.id.materi1list);
        isDataListView();
    }

    private void isDataListView() {
        Cursor materi1Cursor;

        materi1Cursor = dbhelper.fetchAllWisata(db);

        startManagingCursor( materi1Cursor);
        /*Create an array to specify the fields we want to display in the list (only the 'NAMA' column in this case) */
        String[] from = new String[]{dbhelper.NAMA};
        /* and an array of the fields we want to bind those fields to (in this case just the textView 'txtwisata' from our new row.xml layout above) */
        int[] to = new int[]{R.id.txtmateri};

        /* Now create a simple cursor adapter.. */
        SimpleCursorAdapter materiAdapter =
                new SimpleCursorAdapter(this, R.layout.row, materi1Cursor, from, to);

        /* and assign it to our Spinner widget */
        listContent.setAdapter(materiAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            db.close();
        }catch (Exception e){

        }
    }

}
