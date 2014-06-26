package com.course.gameswap;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	private final static int DATABASE_VERSION = 1;
	private final static String DATABASE_NAME = "gameswap";
	
	private final static String WANTED_TABLE_NAME = "wanted_tbl";
	private final static String HAVE_TABLE_NAME = "have_tbl";
	private final static String GAMES_TABLE_NAME = "games_tbl";
	
	private final static String KEY_ID = "id";
	private final static String NAME_COLUMN = "name";
	private final static String KEY_PHONE = "phone";

	DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE = "CREATE TABLE " + WANTED_TABLE_NAME + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + NAME_COLUMN + " TEXT," + KEY_PHONE
				+ " TEXT" + ")";
		db.execSQL(CREATE_TABLE);
		
		CREATE_TABLE = "CREATE TABLE " + HAVE_TABLE_NAME + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + NAME_COLUMN + " TEXT," + KEY_PHONE
				+ " TEXT" + ")";
		db.execSQL(CREATE_TABLE);
		
		CREATE_TABLE = "CREATE TABLE " + GAMES_TABLE_NAME + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + NAME_COLUMN + " TEXT," + KEY_PHONE
				+ " TEXT" + ")";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + GAMES_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + WANTED_TABLE_NAME);
		onCreate(db);
	}

	// add contact
	public void addHaveGame(GameBean game) {
		SQLiteDatabase db = this.getWritableDatabase();

		if (game != null && !isGamedAlreadyAddedToHave(game.getName()) ){
			ContentValues values = new ContentValues();
			values.put(KEY_ID, game.getID());
			values.put(NAME_COLUMN, game.getName());
			values.put(KEY_PHONE, game.getPhoneNumber());
	
			db.insert(HAVE_TABLE_NAME, null, values);
			db.close();
		}
	}
	
	public void addGame(GameBean game) {
		SQLiteDatabase db = this.getWritableDatabase();

		if (game != null && !isGamedAlreadyAddedToGames(game.getID()) ){
			ContentValues values = new ContentValues();
			values.put(KEY_ID, game.getID());
			values.put(NAME_COLUMN, game.getName());
			values.put(KEY_PHONE, game.getPhoneNumber());
	
			db.insert(GAMES_TABLE_NAME, null, values);
			db.close();
		}
	}

	// get single contact
	public GameBean getGame(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(GAMES_TABLE_NAME, new String[] { KEY_ID, NAME_COLUMN,
				KEY_PHONE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		GameBean contact = new GameBean(cursor.getInt(0),
				cursor.getString(1), cursor.getString(2));
		
		return contact;

	}

	// update contact
	public int updateGame(GameBean contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(NAME_COLUMN, contact.getName());
		values.put(KEY_PHONE, contact.getPhoneNumber());

		return db.update(GAMES_TABLE_NAME, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}
	
	// delete contact
	public void deleteContact(GameBean contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(GAMES_TABLE_NAME, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}

	// get all contacts
	public List<GameBean> getAllGames() {
		List<GameBean> contactsList = new ArrayList<GameBean>();
		String sellectQuery = "SELECT * FROM " + GAMES_TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(sellectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				GameBean contacts = new GameBean();
				contacts.setID(Integer.parseInt(cursor.getString(0)));
				contacts.setName(cursor.getString(1));
				contacts.setPhoneNumber(cursor.getString(2));

				contactsList.add(contacts);
			} while (cursor.moveToNext());
		}
		return contactsList;
	}

	public List<GameBean> getHaveGames() {
		List<GameBean> contactsList = new ArrayList<GameBean>();
		String sellectQuery = "SELECT * FROM " + HAVE_TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(sellectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				GameBean contacts = new GameBean();
				contacts.setID(Integer.parseInt(cursor.getString(0)));
				contacts.setName(cursor.getString(1));
				contacts.setPhoneNumber(cursor.getString(2));

				contactsList.add(contacts);
			} while (cursor.moveToNext());
		}
		return contactsList;
	}
	
	public List<GameBean> getWantedGames() {
		List<GameBean> contactsList = new ArrayList<GameBean>();
		String sellectQuery = "SELECT * FROM " + WANTED_TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(sellectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				GameBean contacts = new GameBean();
				contacts.setID(Integer.parseInt(cursor.getString(0)));
				contacts.setName(cursor.getString(1));
				contacts.setPhoneNumber(cursor.getString(2));

				contactsList.add(contacts);
			} while (cursor.moveToNext());
		}
		return contactsList;
	}

	// get contacts count
	public boolean isGamedAlreadyAddedToHave(String name) {
		String countQuery = "SELECT * FROM " + HAVE_TABLE_NAME + " where name = '" + name + "'";
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(countQuery, null);
		if (cursor.moveToFirst())
			return true;
		
		return false;
	}
	
	public boolean isGamedAlreadyAddedToGames(int id) {
		String countQuery = "SELECT * FROM " + GAMES_TABLE_NAME + " where id = " + id;
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(countQuery, null);
		if (cursor.moveToFirst())
			return true;
		
		return false;
	}
	

	public int getGamesCount() {
		String countQuery = "SELECT * FROM " + GAMES_TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(countQuery, null);
		
		return cursor.getCount();
	}
	
	public int getHaveCount() {
		String countQuery = "SELECT * FROM " + HAVE_TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(countQuery, null);
		
		return cursor.getCount();
	}
	
	public int getWantedCount() {
		String countQuery = "SELECT * FROM " + WANTED_TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(countQuery, null);
		
		return cursor.getCount();
	}
}
