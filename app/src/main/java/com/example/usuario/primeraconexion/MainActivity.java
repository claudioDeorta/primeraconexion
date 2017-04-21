package com.example.usuario.primeraconexion;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private DataBaseManager manager ;
    Cursor cursor ;
    private ListView lista;
    SimpleCursorAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DataBaseManager(this);
        lista = (ListView) findViewById(R.id.ListView);

        manager.insertar("claudio","deorta");
        manager.insertar("maru","brufau");



        cursor= manager.cargarCursorContactos();
        String [] from = new String [] {manager.CN_NAME,manager.CN_PHONE};
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};

        adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);

        lista.setAdapter(adapter);


    }




}
