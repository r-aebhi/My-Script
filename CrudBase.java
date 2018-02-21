package aebhi.addictech.samplesqlite.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import aebhi.addictech.samplesqlite.Models.StudData;

/**
 * Created by Aebhi on 21-Feb-18.
 */

public class CrudBase extends SQLiteOpenHelper {
        private static final int dbversion = 1;
        private static final String dbname = "studManager";
        private static final String table = "studs";

        // Contacts Table Columns names
        private static final String kid = "id";
        private static final String kname = "name";
        private static final String kdept = "dept";

        public CrudBase(Context context) {
            super(context, dbname, null, dbversion);
        }

        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + table + "("
                    + kid + " INTEGER PRIMARY KEY," + kname + " TEXT,"
                    + kdept + " TEXT" + ")";
            db.execSQL(CREATE_CONTACTS_TABLE);
        }

        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + table);
            onCreate(db);
        }


        public void addStud(StudData stud) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(kname, stud.getName());
            values.put(kdept, stud.getDept());
            db.insert(table, null, values);
            db.close();
        }

        // Getting single contact
        public StudData getStud(int id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(table, new String[] { kid,
                            kname, kdept }, kid + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            StudData d = new StudData(cursor.getString(0),cursor.getString(1), cursor.getString(2));
            return d;
        }

        // Getting All Contacts
        public ArrayList<StudData> getAllData() {
            ArrayList<StudData> d = new ArrayList<StudData>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + table;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    StudData d1 = new StudData(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                    d.add(d1);
                } while (cursor.moveToNext());
            }
            return d;
        }

        // Updating single contact
        public int updateStud(StudData d) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(kname, d.getName());
            values.put(kdept, d.getDept());
            return db.update(table, values, kid + " = ?",
                    new String[] { String.valueOf(d.getId()) });
        }

        // Deleting single contact
        public void deleteContact(StudData d) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(table, kid + " = ?",
                    new String[] { String.valueOf(d.getId()) });
            db.close();
        }


        // Getting contacts Count
        public int getCount() {
            String countQuery = "SELECT  * FROM " + table;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();

            return cursor.getCount();
        }

    }
