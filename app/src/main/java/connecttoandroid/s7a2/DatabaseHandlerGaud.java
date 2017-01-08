package connecttoandroid.s7a2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandlerGaud extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "GaudDbFile";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_DATETIME = "datentime";


    public DatabaseHandlerGaud(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT,"
                + KEY_DATETIME + " TEXT"
                + ")"

                ;
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addContact(GaudDbFile contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone
        values.put(KEY_DATETIME, contact.getDatenTime()); // Contact datetime

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //db.close(); // Closing database connection
    }

    // Getting single contact
    GaudDbFile getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS,
                new String[] { KEY_ID, KEY_NAME, KEY_PH_NO , KEY_DATETIME },
                KEY_ID + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null
        );

        if (cursor != null)
            cursor.moveToFirst();

        GaudDbFile contact = new GaudDbFile(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
        // return contact
        return contact;
    }













    //////////////////////////////////////////

    GaudDbFile getContact(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS,
                new String[] { KEY_ID, KEY_NAME, KEY_PH_NO , KEY_DATETIME },
                KEY_NAME + "=?",
                new String[] {  name },
                null, null, null, null
        );

        if (cursor != null)
            cursor.moveToFirst();

        GaudDbFile contact = new GaudDbFile(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
        // return contact
        return contact;
    }

    ///////////////////////////////////////////
    // Getting All Contacts
    public List<GaudDbFile> getAllContacts() {
        List<GaudDbFile> contactList = new ArrayList<GaudDbFile>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {


            do {
                GaudDbFile contact = new GaudDbFile();
              //  contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
              //  contact.setPhoneNumber(cursor.getString(2));
              //  contact.setDatenTime(cursor.getString(3));


                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public String[] getAllCountries1()
    {
        List<GaudDbFile> contactList = new ArrayList<GaudDbFile>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String[] str = new String[cursor.getCount()];
        int i = 0;

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {


            do {
                GaudDbFile contact = new GaudDbFile();
                //  contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                //  contact.setPhoneNumber(cursor.getString(2));
                //  contact.setDatenTime(cursor.getString(3));

                str[i] = cursor.getString(1);
                i++;

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return str;
    }

        // Updating single contact
    public int updateContact(GaudDbFile contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
        values.put(KEY_DATETIME, contact.getDatenTime());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(GaudDbFile contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        //return cursor.getCount();
        return count;
    }

    public void deleteAllData()
    {
//	    SQLiteDatabase sdb= this.getWritableDatabase();
//	    sdb.delete("SQLiteDatabase", null, null);

        SQLiteDatabase db = this.getWritableDatabase(); //get database
        db.execSQL("DELETE FROM " +TABLE_CONTACTS); //delete all rows in a table
        db.close();

    }

}
