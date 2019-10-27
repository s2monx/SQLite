package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

public class AuthorActi extends AppCompatActivity {
    EditText et_Matacgia, etHoten, etDiachi, etEmail;
    Button btnSave, btnSelect, btnExit, btnDelete, btnUpdate;
    GridView gvItem;
    DBHelper dbHelper;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Dialog d = new Dialog(AuthorActi.this);
        d.setContentView(R.layout.activity_author);
        Anhxa(d);
        //nut save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setId_author(Integer.parseInt(et_Matacgia.getText().toString()));
                author.setAddress(etDiachi.getText().toString());
                author.setName(etHoten.getText().toString());
                author.setEmail(etEmail.getText().toString());
                if (dbHelper.insertAuthor(author)) {
                    Toast.makeText(AuthorActi.this, "Da luu thanh cong", Toast.LENGTH_SHORT).show();
                    clear();
                }
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
                }
                else{
                    Author author = dbHelper.getAuthor(Integer.parseInt(et_Matacgia.getText().toString()));
                    list.add(author.getId_author() + "");
                    list.add(author.getName());
                    list.add(author.getAddress());
                    list.add(author.getEmail());
                }
                adapter = new ArrayAdapter<String>(AuthorActi.this, android.R.layout.simple_expandable_list_item_1, list);
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
                String id = et_Matacgia.getText().toString();
                try {
                    if (!id.isEmpty()) {
                        int idkq = Integer.parseInt(id);
                        dbHelper.deleteAuthor(idkq);
                        adapter.notifyDataSetChanged();
                        clear();
                        Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(AuthorActi.this, "Ma tac gia khong ton tai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        d.show();
        Window w = d.getWindow();
        w.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    private void clear() {
        et_Matacgia.setText(null);
        etDiachi.setText(null);
        etHoten.setText(null);
        etEmail.setText(null);
        et_Matacgia.requestFocus();
    }

    public void Anhxa(Dialog d){
        et_Matacgia = d.findViewById(R.id.etMatacgia);
        etDiachi = d.findViewById(R.id.etDiachi);
        etEmail = d.findViewById(R.id.etEmail);
        etHoten = d.findViewById(R.id.etHoten);
        btnSave = d.findViewById(R.id.btnSave);
        btnSelect = d.findViewById(R.id.btnSelect);
        btnExit = d.findViewById(R.id.btnExit);
        btnDelete = d.findViewById(R.id.btnDelete);
        btnUpdate = d.findViewById(R.id.btnUpdate);
        gvItem = d.findViewById(R.id.gvItem);
        dbHelper = new DBHelper(this);
    }
}
