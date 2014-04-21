package com.alvaromerino.android.sqlite.handlers;

import java.util.ArrayList;
import java.util.List;

import com.alvaromerino.android.sqlite.data.Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "UsuariosDB";
	private static final String TABLE_USERS = "users";
	private static final String KEY_ID = "id";
	private static final String KEY_USER = "user";
	private static final String KEY_PASSWORD = "password";
	private static final String[] columnas = { KEY_ID, KEY_USER, KEY_PASSWORD };
    private SQLiteDatabase database;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        database = this.getWritableDatabase();
    }

    public void close() {
        if (database != null) database.close();
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableUsers = "CREATE TABLE users (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  "
                + KEY_USER + " TEXT, " + KEY_PASSWORD + " TEXT);";
        db.execSQL(sqlCreateTableUsers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDropTableUsers = "DROP TABLE IF EXISTS " + TABLE_USERS;
        db.execSQL(sqlDropTableUsers);
        onCreate(db);
    }

    public void addUsuario(Usuario usuario) {
    	ContentValues values = new ContentValues();
    	values.put(KEY_USER, usuario.getUser());
    	values.put(KEY_PASSWORD, usuario.getPassword());
    	database.insert(TABLE_USERS, null, values);
    }
    
    public Usuario getUsuario(int id) {
    	
    	Cursor cursor = database.query(TABLE_USERS, columnas, KEY_ID + "=?", 
    									new String[] { String.valueOf(id) }, null, null, null, null);
    	
    	Usuario usuario = null;

    	if (cursor.moveToFirst()) 
    	{
			usuario = new Usuario(Integer.parseInt(cursor.getString(0)), 
    			cursor.getString(1), cursor.getString(2));
    	}
    	
    	return usuario;
    }
    
    public Usuario getUsuario(String username) {
    	
    	Cursor cursor = database.query(TABLE_USERS, columnas, KEY_USER + "=?", 
    									new String[] { String.valueOf(username) }, null, null, null, null);
    	
    	Usuario usuario = null;
    	if (cursor.moveToFirst()) {
    	
			usuario = new Usuario(Integer.parseInt(cursor.getString(0)), 
    			cursor.getString(1), cursor.getString(2));
    	}
    	
    	return usuario;
    }
    
    public List<Usuario> getUsuarios(String username) {
    	
    	List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    	String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USER + 
    			" LIKE '" + username + "%'";
    	Cursor cursor = database.rawQuery(query, null);
    	
    	if (cursor.moveToFirst()) {
    		do {
    			int id = Integer.parseInt(cursor.getString(0));
    			String user = cursor.getString(1);
    			String password = cursor.getString(2);
    			Usuario usuario = new Usuario(id, user, password);
    			listaUsuarios.add(usuario);
    		} while (cursor.moveToNext());
    	}
    	
    	return listaUsuarios;
    }
    
    public List<Usuario> getAllUsuarios() {
    	
    	List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    	String query = "SELECT * FROM " + TABLE_USERS;
    	Cursor cursor = database.rawQuery(query, null);
    	
    	if (cursor.moveToFirst()) {
    		do {
    			int id = Integer.parseInt(cursor.getString(0));
    			String user = cursor.getString(1);
    			String password = cursor.getString(2);
    			Usuario usuario = new Usuario(id, user, password);
    			listaUsuarios.add(usuario);
    		} while (cursor.moveToNext());
    	}
    	
    	return listaUsuarios;
    }
    
    public int updateUsuario(Usuario usuario) {
    	ContentValues values = new ContentValues();
    	values.put(KEY_USER, usuario.getUser());
    	values.put(KEY_PASSWORD, usuario.getPassword());
    	
    	return database.update(TABLE_USERS, values, KEY_ID + "=?", 
    			new String[] { String.valueOf(usuario.getId()) });
    }
    
    public void deleteUsuario(Usuario usuario) {
    	database.delete(TABLE_USERS, KEY_ID + "=?",
    			new String[] { String.valueOf(usuario.getId()) });
    }
    
    public int getUsuariosCount() {
    	String query = "SELECT * FROM " + TABLE_USERS;
    	Cursor cursor = database.rawQuery(query, null);
    	cursor.close();
    	return cursor.getCount();
    }

}
