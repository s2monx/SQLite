package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    SQLiteDatabase openorcreateDB = new SQLiteDatabase("",);
    EditText etMaso, etTieude, etTacgia;
    Button btnSave, btnSelect, btnExit, btnDelete, btnUpdate;
    GridView gvItem;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        //nut save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setId(Integer.parseInt(etMaso.getText().toString()));
                book.setTitle(etTieude.getText().toString());
                book.setAuthor(etTacgia.getText().toString());
                if (dbHelper.insertBook(book))
                    Toast.makeText(MainActivity.this, "Da luu thanh cong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Luu khong thanh cong", Toast.LENGTH_SHORT).show();

            }
        });
        //nut select
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                ArrayList<Book> booklist = new ArrayList<>();
                if (etMaso.getText().toString().equalsIgnoreCase("")) {
                    booklist = dbHelper.getAllBook();
                    for (Book b : booklist) {
                        list.add(b.getId() + "");
                        list.add(b.getTitle());
                        list.add(b.getAuthor());
                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, list);
//                    gvItem.setAdapter(adapter);
                }
                else{
                    Book book = dbHelper.getBook(Integer.parseInt(etMaso.getText().toString()));

                        list.add(book.getId() + "");
                        list.add(book.getTitle());
                        list.add(book.getAuthor());


                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, list);
                gvItem.setAdapter(adapter);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    public void Anhxa(){
        etMaso = findViewById(R.id.etMaso);
        etTieude = findViewById(R.id.etTieude);
        etTacgia = findViewById(R.id.etTacgia);
        btnSave = findViewById(R.id.btnSave);
        btnSelect = findViewById(R.id.btnSelect);
        btnExit = findViewById(R.id.btnExit);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        gvItem = findViewById(R.id.gvItem);

        dbHelper = new DBHelper(this);
    }

//    public boolean updateBook(int i, String title){
//
//    }
}
