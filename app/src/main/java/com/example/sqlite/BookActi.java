package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActi extends AppCompatActivity {

    EditText etMasach, etTieude, etMatacgia;
    Button btnSave, btnSelect, btnExit, btnDelete, btnUpdate;
    GridView gvItem;
    DBHelper dbHelper;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Dialog d = new Dialog(BookActi.this);
        d.setContentView(R.layout.activity_book);

        Anhxa(d);
        //nut save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setId_book(Integer.parseInt(etMasach.getText().toString()));
                book.setTitle(etTieude.getText().toString());
                book.setId_author(Integer.parseInt(etMatacgia.getText().toString()));
                boolean s = dbHelper.insertBook(book);
                if (s) {
                    Toast.makeText(BookActi.this, "Da luu thanh cong", Toast.LENGTH_SHORT).show();
                    clear();
                }
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
                adapter = new ArrayAdapter<String>(BookActi.this, android.R.layout.simple_expandable_list_item_1, list);
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
                String id = etMasach.getText().toString();
                try {
                    if (!id.isEmpty()) {
                        int idkq = Integer.parseInt(id);
                        dbHelper.deleteBook(idkq);
                        adapter.notifyDataSetChanged();
                        clear();
                        Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Ma sach khong ton tai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setId_book(Integer.parseInt(etMasach.getText().toString()));
                book.setTitle(etTieude.getText().toString());
                book.setId_author(Integer.parseInt(etMatacgia.getText().toString()));
                if (dbHelper.updateBook(book))
                    Toast.makeText(BookActi.this,
                            "Author updated successfully", Toast.LENGTH_SHORT).show();
            }
        });
        d.show();
        Window w = d.getWindow();
        w.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    private void clear() {
        etMasach.setText(null);
        etTieude.setText(null);
        etMatacgia.setText(null);
        etMasach.requestFocus();
    }

    public void Anhxa(Dialog d){
        etMasach = d.findViewById(R.id.etMaSach);
        etTieude = d.findViewById(R.id.etTieude);
        etMatacgia = d.findViewById(R.id.etMaTacgia);
        btnSave = d.findViewById(R.id.btnSave);
        btnSelect = d.findViewById(R.id.btnSelect);
        btnExit = d.findViewById(R.id.btnExit);
        btnDelete = d.findViewById(R.id.btnDelete);
        btnUpdate = d.findViewById(R.id.btnUpdate);
        gvItem = d.findViewById(R.id.gvItem);

        dbHelper = new DBHelper(this);
    }
}
