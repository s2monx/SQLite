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

public class AuthorActi extends AppCompatActivity {
    EditText et_Matacgia, etHoten, etDiachi, etEmail;
    Button btnSave, btnSelect, btnExit, btnDelete, btnUpdate;
    GridView gvItem;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        Anhxa();
        //nut save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setId_author(Integer.parseInt(et_Matacgia.getText().toString()));
                author.setAddress(etDiachi.getText().toString());
                author.setName(etHoten.getText().toString());
                author.setEmail(etEmail.getText().toString());
                if (dbHelper.insertAuthor(author)==1)
                    Toast.makeText(AuthorActi.this, "Da luu thanh cong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AuthorActi.this, "Luu khong thanh cong", Toast.LENGTH_SHORT).show();

            }
        });
        //nut select
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                ArrayList<Author> authorlist = new ArrayList<>();
                if (et_Matacgia.getText().toString().equalsIgnoreCase("")) {
                    authorlist = dbHelper.getAllAuthor();
                    for (Author a : authorlist) {
                        list.add(a.getId_author() + "");
                        list.add(a.getName());
                        list.add(a.getAddress());
                        list.add(a.getEmail());
                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, list);
//                    gvItem.setAdapter(adapter);
                }
                else{
                    Author author = dbHelper.getAuthor(Integer.parseInt(et_Matacgia.getText().toString()));

                    list.add(author.getId_author() + "");
                    list.add(author.getName());
                    list.add(author.getAddress());
                    list.add(author.getEmail());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AuthorActi.this, android.R.layout.simple_expandable_list_item_1, list);
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
        et_Matacgia = findViewById(R.id.etMatacgia);
        etDiachi = findViewById(R.id.etDiachi);
        etEmail = findViewById(R.id.etEmail);
        etHoten = findViewById(R.id.etHoten);
        btnSave = findViewById(R.id.btnSave);
        btnSelect = findViewById(R.id.btnSelect);
        btnExit = findViewById(R.id.btnExit);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        gvItem = findViewById(R.id.gvItem);

        dbHelper = new DBHelper(this);
    }
}
