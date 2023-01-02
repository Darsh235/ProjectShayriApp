package com.example.shayariapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {


    static int ShayariShow;
    private static final String TAG = "MyDatabase";
    private static String DB_NAME = "Shayaridb.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private boolean mNeedUpdate = false;

    ArrayList<ModalClass> CategoryList = new ArrayList<>();
    ArrayList<ShayariModalClass> ShayariList = new ArrayList<>();

    private Context mContext;

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        if (Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }


    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    //    TODO copy file
    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }


    //    TODO update database
    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }


    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }


    public ArrayList<ModalClass> readData() {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from Category";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                Log.e(TAG, "readData: -----");
                int ID = c.getInt(0);
                String Category = c.getString(1);


                ModalClass model = new ModalClass(ID, Category);
                CategoryList.add(model);
            } while (c.moveToNext());


        } else {
            Log.e(TAG, "readData: ==========");
        }

        return CategoryList;
    }


    public ArrayList<ShayariModalClass> showData() {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from ShayariTb where CategoryID=" + ShayariShow;
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                Log.e(TAG, "readData: -----");
                int ID = c.getInt(0);
                String Quote = c.getString(1);
                int CateId = c.getInt(2);
                int Status = c.getInt(3);


                ShayariModalClass model = new ShayariModalClass(ID, Quote, CateId,Status);
                ShayariList.add(model);
            } while (c.moveToNext());


        } else {
            Log.e(TAG, "readData: ==========");
        }

        return ShayariList;



    }


    public ArrayList<ShayariModalClass> homedata() {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from ShayariTb";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                Log.e(TAG, "readData: -----");
                int ID = c.getInt(0);
                String Quote = c.getString(1);
                int CateId = c.getInt(2);
                int Status = c.getInt(3);


                ShayariModalClass model = new ShayariModalClass(ID, Quote, CateId,Status);
                ShayariList.add(model);
            } while (c.moveToNext());


        } else {
            Log.e(TAG, "readData: ==========");
        }
        return ShayariList;

    }

    public ArrayList<ShayariModalClass> likedata() {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from ShayariTb where Status=1";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                Log.e(TAG, "readData: -----");
                int ID = c.getInt(0);
                String Quote = c.getString(1);
                int CateId = c.getInt(2);
                int Status = c.getInt(3);


                ShayariModalClass model = new ShayariModalClass(ID, Quote, CateId,Status);
                ShayariList.add(model);
            } while (c.moveToNext());


        } else {
            Log.e(TAG, "readData: ==========");
        }
        return ShayariList;

    }

    void editstatus(int like, int getid){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues  fill = new ContentValues();
        fill.put("Status",like);
        db.update("ShayariTb",fill,"ShayariID=?",new String[]{String.valueOf(getid)});
    }
}

