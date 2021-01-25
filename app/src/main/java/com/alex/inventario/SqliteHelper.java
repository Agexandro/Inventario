package com.alex.inventario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelper {
    private SQLiteDatabase database;
    private InventoryOpenHelper inventoryOpenHelper;

    public SqliteHelper(Context context){
        inventoryOpenHelper = new InventoryOpenHelper(context) ;
        database = inventoryOpenHelper.getWritableDatabase() ;
    }

    ///Holi
    public void saveItem(String name, String details, String lote, String caducity, String quantity, String key) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqliteConstants.COL_NAME, name);
        contentValues.put(SqliteConstants.COL_DETAILS, details);
        contentValues.put(SqliteConstants.COL_LOTE, lote);
        contentValues.put(SqliteConstants.COL_CADUCITY, caducity);
        contentValues.put(SqliteConstants.COL_QUANTITY, quantity);
        contentValues.put(SqliteConstants.COL_KEY, key);

        database.insert(SqliteConstants.TABLE_NAME, null, contentValues);
    }

    public Cursor getAllItems() {
        return database.rawQuery("SELECT*FROM "+ SqliteConstants.TABLE_NAME, null);
    }

    private class InventoryOpenHelper extends SQLiteOpenHelper {
        public InventoryOpenHelper(@Nullable Context context) {
            super(context, SqliteConstants.DB_NAME, null, SqliteConstants.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "CREATE TABLE "+SqliteConstants.TABLE_NAME+"("+
                            SqliteConstants.COL_ID + " INTEGER PRIMARY KEY," +
                            SqliteConstants.COL_NAME + " TEXT," +
                            SqliteConstants.COL_DETAILS + " TEXT," +
                            SqliteConstants.COL_LOTE + " TEXT," +
                            SqliteConstants.COL_CADUCITY + " TEXT," +
                            SqliteConstants.COL_QUANTITY + " INTEGER," +
                            SqliteConstants.COL_KEY + " INTEGER" + ")"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + SqliteConstants.TABLE_NAME);
            onCreate(db);
        }
    }
}
