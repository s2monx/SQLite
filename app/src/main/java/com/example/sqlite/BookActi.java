package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActi extends AppCompatActivity {
    EditText etMasach, etTieude, etMatacgia;
    Button btnSave, btnSelect, btnExit, btnDelete, btnUpdate;
    GridView gvItem;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Anhxa();
        //nut save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setId_book(Integer.parseInt(etMasach.getText().toString()));
                book.setTitle(etTieude.getText().toString());
                book.setId_author(Integer.parseInt(etMatacgia.getText().toString()));
                if (dbHelper.insertBook(book) == 1)
                    Toast.makeText(BookActi.this, "Da luu thanh cong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BookActi.this, "Luu khong thanh cong", Toast.LENGTH_SHORT).show();

            }
        });
        //nut select
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                ArrayList<Book> booklist = new ArrayList<>();
                if (etMasach.getText().toString().equalsIgnoreCase("")) {
                    booklist = dbHelper.getAllBook();
                    for (Book b : booklist) {
                        list.add(b.getId_book() + "");
                        list.add(b.getTitle());
                        list.add(b.getId_author() + "");
                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, list);
//                    gvItem.setAdapter(adapter);
                }
                else{
                    Book book = dbHelper.getBook(Integer.parseInt(etMasach.getText().toString()));

                    list.add(book.getId_book() + "");
                    list.add(book.getTitle());
                    list.add(book.getId_author() +"");


                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookActi.this, android.R.layout.simple_expandable_list_item_1, list);
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
        etMasach = findViewById(R.id.etMaSach);
        etTieude = findViewById(R.id.etTieude);
        etMatacgia = findViewById(R.id.etMatacgia);
        btnSave = findViewById(R.id.btnSave);
        btnSelect = findViewById(R.id.btnSelect);
        btnExit = findViewById(R.id.btnExit);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        gvItem = findViewById(R.id.gvItem);

        dbHelper = new DBHelper(this);
    }
}
