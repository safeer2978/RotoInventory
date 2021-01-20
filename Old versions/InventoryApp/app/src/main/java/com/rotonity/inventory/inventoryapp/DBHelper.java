package com.rotonity.inventory.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Roto";
    private static final String INVENTORY_TABLE_NAME = "Inventory";
    private static final String INVENTORY_INDEX = "index";
    private static final String INVENTORY_COLUMN_ID = "id";
    private static final String INVENTORY_COLUMN_PART_TYPE = "Part_type";
    private static final String INVENTORY_COLUMN_DESC = "Description";
    private static final String INVENTORY_COLUMN_COST = "Cost";
    private static final String INVENTORY_COLUMN_CURRENTLY_WITH = "Currently_With";
    private static final String INVENTORY_COLUMN_PURCHASED_BY = "Purchased_By";
    private static final String INVENTORY_COLUMN_lAST_EDITED = "Last_Edit";
    private static final String INVENTORY_COLUMN_AVAILABLE = "Available";

    private static final String INVENTORY_COLUMN_NAME="name";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + INVENTORY_TABLE_NAME + "("
                  //  + INVENTORY_INDEX + "INTEGER  PRIMARY KEY AUTOINCREMENT,"
                    +INVENTORY_COLUMN_ID+ " VARCHAR UNIQUE,"
                    + INVENTORY_COLUMN_PART_TYPE + " VARCHAR,"
                    + INVENTORY_COLUMN_DESC + " VARCHAR,"
                    + INVENTORY_COLUMN_COST + " VARCHAR,"
                    + INVENTORY_COLUMN_CURRENTLY_WITH+ " VARCHAR,"
                    +INVENTORY_COLUMN_PURCHASED_BY+ " VARCHAR,"
                    +INVENTORY_COLUMN_lAST_EDITED+ " VARCHAR,"
                    + INVENTORY_COLUMN_AVAILABLE+ " VARCHAR,"
                    + INVENTORY_COLUMN_NAME+" VARHAR"
                    + ")";




    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + INVENTORY_TABLE_NAME);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + INVENTORY_TABLE_NAME);
        onCreate(db);
    }

    public long insertItem(InventoryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(INVENTORY_COLUMN_PART_TYPE, item.getName());
        values.put(INVENTORY_COLUMN_AVAILABLE, item.getAvailability());
        values.put(INVENTORY_COLUMN_COST, item.getCost());
        values.put(INVENTORY_COLUMN_CURRENTLY_WITH,item.getCurrently_with());
        values.put(INVENTORY_COLUMN_DESC, item.getDescription());
        values.put(INVENTORY_COLUMN_ID, item.getBar_code());
        values.put(INVENTORY_COLUMN_PURCHASED_BY, item.getPurchased_by());
        values.put(INVENTORY_COLUMN_lAST_EDITED,item.getLast_edit());
        long id = db.insert(INVENTORY_TABLE_NAME, null, values);
        db.close();
        return id;
    }



    public  InventoryItem getItem(String barcode) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(INVENTORY_TABLE_NAME,
                new String[]{INVENTORY_COLUMN_ID, INVENTORY_COLUMN_AVAILABLE,
                        INVENTORY_COLUMN_lAST_EDITED,
                        INVENTORY_COLUMN_PURCHASED_BY,
                        INVENTORY_COLUMN_CURRENTLY_WITH,
                        INVENTORY_COLUMN_COST,INVENTORY_COLUMN_DESC,
                        INVENTORY_COLUMN_PART_TYPE},
                INVENTORY_COLUMN_ID + "=?",
                new String[]{barcode}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        InventoryItem item=new InventoryItem();
        try {
            item = new InventoryItem(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_ID)));//,
        }catch (Exception e){
                item.setDescription("");
        }

        // close the db connection
        cursor.close();

        return item;
    }

    public  List<InventoryItem> getAllItems() {
        List<InventoryItem> items = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + INVENTORY_TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                InventoryItem item = new InventoryItem();//,
                        //TODO cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_NAME)));
                item.setCost(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_COST)));
                item.setCurrently_with(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_CURRENTLY_WITH)));
                item.setPurchased_by(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_PURCHASED_BY)));
                item.setLast_edit(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_lAST_EDITED)));
                item.setDescription(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_DESC)));
                item.setAvailability(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_AVAILABLE)));
                items.add(item);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return items;
    }

    protected List<InventoryItem> getItemWith(String user){
        List<InventoryItem> items = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + INVENTORY_TABLE_NAME + " WHERE "+ INVENTORY_COLUMN_CURRENTLY_WITH+" LIKE '"+user+"'" ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                InventoryItem item = new InventoryItem();//,
                //TODO cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_NAME)));
                item.setCost(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_COST)));
                item.setCurrently_with(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_CURRENTLY_WITH)));
                item.setPurchased_by(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_PURCHASED_BY)));
                item.setLast_edit(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_lAST_EDITED)));
                item.setDescription(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_DESC)));
                item.setAvailability(cursor.getString(cursor.getColumnIndex(INVENTORY_COLUMN_AVAILABLE)));
                items.add(item);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return items;

    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + INVENTORY_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int updateNote(InventoryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INVENTORY_COLUMN_PART_TYPE, item.getName());
        values.put(INVENTORY_COLUMN_AVAILABLE, item.getAvailability());
        values.put(INVENTORY_COLUMN_COST, item.getCost());
        values.put(INVENTORY_COLUMN_CURRENTLY_WITH,item.getCurrently_with());
        values.put(INVENTORY_COLUMN_DESC, item.getDescription());
        values.put(INVENTORY_COLUMN_ID, item.getBar_code());
        values.put(INVENTORY_COLUMN_PURCHASED_BY, item.getPurchased_by());
        values.put(INVENTORY_COLUMN_lAST_EDITED,item.getLast_edit());

        // updating row
        return db.update(INVENTORY_TABLE_NAME, values, INVENTORY_COLUMN_ID + " = ?",
                new String[]{String.valueOf(item.getBar_code())});
    }

}
