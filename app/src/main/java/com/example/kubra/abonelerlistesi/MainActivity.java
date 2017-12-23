package com.example.kubra.abonelerlistesi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText isimEt,emailEt;
    Button button;
    RecyclerView recyclerView;
    SQLiteDatabase database;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isimEt= (EditText) findViewById(R.id.editTextName);
        emailEt= (EditText) findViewById(R.id.editTextEmail);
        button= (Button) findViewById(R.id.button);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);

        database=new AboneDbHelper(this).getWritableDatabase();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
Cursor list=getAboneListesi();
        adapter=new Adapter(list);
Log.d("list",getAboneListesi().toString());
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id= (long) viewHolder.itemView.getTag();
                aboneSil(id);
                adapter.listeyiGüncelle(getAboneListesi());
            }
        }).attachToRecyclerView(recyclerView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isim=isimEt.getText().toString();
                String email=emailEt.getText().toString();
                if (isimEt.getText().length() == 0 ||
                        emailEt.getText().length() == 0) {
                    return;
                }
                aboneListesineEkle(isim,email);
                    adapter.listeyiGüncelle(getAboneListesi());
                   isimEt.getText().clear();
                    emailEt.getText().clear();
                }

        });
    }

    private long aboneListesineEkle(String isim, String email) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(AboneContract.AboneEntry.COLUMN_ABONE_NAME,isim);
        contentValues.put(AboneContract.AboneEntry.COLUMN_EMAIL,email);
        long rowId = database.insert(AboneContract.AboneEntry.TABLE_NAME,null,contentValues);
        return rowId;
    }

    public Cursor getAboneListesi() {
      return database.query(AboneContract.AboneEntry.TABLE_NAME,
                null,null,null,null,null,null);

    }

    public boolean aboneSil(long id){
      return  database.delete(AboneContract.AboneEntry.TABLE_NAME, AboneContract.AboneEntry._ID+"="+id,null)>0;
    }
}
