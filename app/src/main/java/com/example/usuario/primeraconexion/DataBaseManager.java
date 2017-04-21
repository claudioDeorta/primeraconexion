package com.example.usuario.primeraconexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by usuario on 15-abr-17.
 */

public class DataBaseManager {

    public static final String TABLE_NAME = "contactos";

    public static final String CN_ID = "id";
    public static final String CN_NAME= "nombre";
    public static final String CN_PHONE = "telefono ";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + CN_ID  + " INTEGER primary key autoincrement , "
            + CN_NAME + " TEXT NOT NULL , "
            + CN_PHONE + " TEXT);";

    private DBHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {

        helper = new DBHelper(context);
         db = helper.getWritableDatabase();

    }

    // metodo para crear los valores

    public ContentValues generarContentValues(String nombre,String telefono ){

        ContentValues valores = new ContentValues();
        valores.put(CN_NAME,nombre);
        valores.put(CN_PHONE,telefono);

        return valores ;

    }

    // metodo para insertar valores

    public void insertar (String nombre,String telefono ){

        db.insert(TABLE_NAME,null,generarContentValues(nombre,telefono));

    }


    public void eliminar (String nombre){

        db.delete(TABLE_NAME,CN_NAME + "=?", new String[]{nombre});

    }

    public void slimanrMultiple(String nom1,String nom2){

        db.delete(TABLE_NAME,CN_NAME + "IN (?,?)" , new String[]{nom1,nom2});
    }

    public void modificarTelefono(String nombre,String newTelefono){

        db.update(TABLE_NAME,generarContentValues(nombre,newTelefono),CN_NAME + "=?" ,new String[]{nombre});
    }

    public Cursor cargarCursorContactos(){

        String [] columnas = new String []{CN_ID,CN_NAME,CN_PHONE};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);

    }

}
