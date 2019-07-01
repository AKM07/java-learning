package com.example.heriana.javaaplikasi.Core.Soal;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.heriana.javaaplikasi.question.Question;

import static com.example.heriana.javaaplikasi.Core.Soal.SoalContract.MovieEntry.KEY_ANSWER;
import static com.example.heriana.javaaplikasi.Core.Soal.SoalContract.MovieEntry.KEY_ID;
import static com.example.heriana.javaaplikasi.Core.Soal.SoalContract.MovieEntry.KEY_OPTA;
import static com.example.heriana.javaaplikasi.Core.Soal.SoalContract.MovieEntry.KEY_OPTB;
import static com.example.heriana.javaaplikasi.Core.Soal.SoalContract.MovieEntry.KEY_OPTC;
import static com.example.heriana.javaaplikasi.Core.Soal.SoalContract.MovieEntry.KEY_QUES;
import static com.example.heriana.javaaplikasi.Core.Soal.SoalContract.MovieEntry.TABLE_QUEST;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name

    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions() {
        Question q1 = new Question("Which of the following two features are supported by the NetBeans IDE ?", "NetBeans automatically runs the program once all the braces in the code are matched", "NetBeans provides a shortcut to format whitespace", "Once Netbeans spots a problem, it won't allow you to continue coding until the problem is fix ",
                "Once Netbeans spots a problem, it won't allow you to continue coding until the problem is fix");
        this.addQuestion(q1);
        Question q2 = new Question("Which is valid sintax to declare and initialize a String variable ?", "String \"x\" = Java;", "String x = Java;", "String x = \"Java\";", "String x = \"Java\";");
        this.addQuestion(q2);
        Question q3 = new Question("Which method returns the length of a String?", "length()", "findLength()", "charAt()", "length()");
        this.addQuestion(q3);
        Question q4 = new Question("You create an Employes object with a String employeeName field. What is the default value for employeeName ? ", "null", "\"Name\"", "\"default\"", "\"Name\"");
        this.addQuestion(q4);
        Question q5 = new Question("Which loop type is considered to be a post-test loop ?", "while", "do-while", "forEach", "do-while");
        this.addQuestion(q5);
        Question q6 = new Question("A reference is declared using the …. Symbol.", "&&", "&", "!", "&");
        this.addQuestion(q6);
        Question q7 = new Question("Which will contain the body of the thread ?", "run();", "start();", "main();", "run();");
        this.addQuestion(q7);
        Question q8 = new Question("How will you print \\n on the screen ?", "print(“\\n”);", "printf(‘\\n’);", "printf(“\\\\n”);", "printf(“\\\\n”);");
        this.addQuestion(q8);
        Question q9 = new Question("Which one of the following is the correct way to declare a pure virtual function ?", "virtual void Display(void){0};", "virtual void Display = 0;", "virtual void Display(void) = 0;", "virtual void Display(void) = 0;");
        this.addQuestion(q9);
        Question q10 = new Question("Which of the following is a mechanism of static polymorphism ?", "Operator overloading", "Function overloading", "All of the bove", "All of the bove");
        this.addQuestion(q10);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));

                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
