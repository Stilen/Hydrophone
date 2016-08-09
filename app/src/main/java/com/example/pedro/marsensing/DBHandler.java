package com.example.pedro.marsensing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "marsensing1";
    // Contacts table name
    private static final String TABLE_CONFIGS = "configs";
    private static final String TABLE_RECORDINGS = "recordings";
    private static final String TABLE_CAMPAIGN = "campaigns";
    private static final String TABLE_USERS = "users";
    // Configs Table Columns names
    private static final String CONFIG_ID = "id";
    private static final String CONFIG_NAME = "name";
    private static final String CONFIG_GAIN = "gain";
    private static final String CONFIG_INTERVAL = "interval";
    private static final String CONFIG_MODE = "mode";
    private static final String CONFIG_DUR = "duration";
    private static final String CONFIG_NFILES = "nfiles";
    private static final String CONFIG_ADC = "adc";
    private static final String CONFIG_BITS = "bits";
    // Records Table Columns names
    private static final String RECORDING_ID = "id";
    private static final String RECORDING_NAME = "name";
    private static final String RECORDING_TIME = "time";
    private static final String RECORDING_MODE = "mode";
    private static final String RECORDING_PLACE = "place";

    private static final String CREATE_TABLE_CONFIGS = "CREATE TABLE " + TABLE_CONFIGS + "("
            + CONFIG_ID + " INTEGER PRIMARY KEY, " + CONFIG_NAME + " TEXT, " + CONFIG_GAIN + " INTEGER, "
            + CONFIG_INTERVAL + " INTEGER, " + CONFIG_MODE + " INTEGER, " + CONFIG_DUR + " INTEGER, "
            + CONFIG_NFILES + " INTEGER, " + CONFIG_ADC + " INTEGER, " + CONFIG_BITS + " INTEGER" + ");";

    private static final String CREATE_TABLE_RECORDINGS = "CREATE TABLE" + TABLE_RECORDINGS + "("
            + RECORDING_ID + " INTEGER PRIMARY KEY," + RECORDING_NAME + " TEXT," + RECORDING_TIME
            + " TIME," + RECORDING_MODE + " INTEGER, " + RECORDING_PLACE + " TEXT" + ")";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONFIGS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONFIGS);
        onCreate(db);
    }

    //--------------------------------CONFIGS------------------------------//
    // Adding new config
    public void addConfig(Config config) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Data: ", CREATE_TABLE_CONFIGS);
        ContentValues values = new ContentValues();
        values.put(CONFIG_NAME, config.getName()); // Config Name
        values.put(CONFIG_GAIN,config.getGain());
        values.put(CONFIG_INTERVAL,config.getInterval());
        values.put(CONFIG_MODE,config.getMode());
        values.put(CONFIG_DUR,config.getDuration());
        values.put(CONFIG_NFILES,config.getNfiles());
        values.put(CONFIG_ADC,config.getAdc());
        values.put(CONFIG_BITS,config.getBits());
        db.insert(TABLE_CONFIGS, null, values);
        db.close(); // Closing database connection
    }

    // Getting one config
    public Config getConfig(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONFIGS, new String[]{CONFIG_ID,
                        CONFIG_NAME,CONFIG_GAIN,CONFIG_INTERVAL,CONFIG_MODE,CONFIG_DUR,CONFIG_NFILES,CONFIG_ADC,CONFIG_BITS}, CONFIG_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Config config = new Config(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),
                Integer.parseInt(cursor.getString(7)),Integer.parseInt(cursor.getString(7)));
        return config;
    }

    public Config getConfigByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONFIGS, new String[]{CONFIG_ID,
                        CONFIG_NAME,CONFIG_GAIN,CONFIG_INTERVAL,CONFIG_MODE,CONFIG_DUR,CONFIG_NFILES,CONFIG_ADC,CONFIG_BITS}, CONFIG_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Config config = new Config(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),
                Integer.parseInt(cursor.getString(7)),Integer.parseInt(cursor.getString(7)));
        return config;
    }

    // Getting All Configs
    public List<Config> getAllConfigs() {
        List<Config> configList = new ArrayList<Config>();
        String selectQuery = "SELECT * FROM " + TABLE_CONFIGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Config config = new Config();
                config.setId(Integer.parseInt(cursor.getString(0)));
                config.setName(cursor.getString(1));
                config.setGain(Integer.parseInt(cursor.getString(2)));
                config.setInterval(Integer.parseInt(cursor.getString(3)));
                config.setMode(Integer.parseInt(cursor.getString(4)));
                config.setDuration(Integer.parseInt(cursor.getString(5)));
                config.setNfiles(Integer.parseInt(cursor.getString(6)));
                config.setAdc(Integer.parseInt(cursor.getString(7)));
                config.setBits(Integer.parseInt(cursor.getString(8)));
// Adding config to list
                configList.add(config);
            } while (cursor.moveToNext());
        }
        return configList;
    }

    // Getting configs Count
    public int getConfigsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONFIGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating a config
    public int updateConfig(Config config) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CONFIG_NAME, config.getName()); // Config Name
        values.put(CONFIG_GAIN,config.getGain());
        values.put(CONFIG_INTERVAL,config.getInterval());
        values.put(CONFIG_MODE,config.getMode());
        values.put(CONFIG_DUR,config.getDuration());
        values.put(CONFIG_NFILES,config.getNfiles());
        values.put(CONFIG_ADC,config.getAdc());
        values.put(CONFIG_BITS,config.getBits());

// updating row
        return db.update(TABLE_CONFIGS, values, CONFIG_ID + " = ?",
                new String[]{String.valueOf(config.getId())});
    }

    // Deleting a config
    public void deleteConfig(Config config) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONFIGS, CONFIG_ID + " = ?",
                new String[]{String.valueOf(config.getId())});
        db.close();
    }

    //--------------------------------RECORDINGS------------------------------//

    //--------------------------------CAMPAIGNS------------------------------//

    //--------------------------------USERS------------------------------//
}