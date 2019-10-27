package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et_Matacgia, etHoten, etDiachi, etEmail;
    Button btnSave, btnSelect, btnDelete, btnUpdate;
    GridView gvItem;
    DBHelper dbHelper;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnThongtinsach:
                Intent intent_Thongtinsach = new Intent(MainActivity.this, BookActi.class);
                startActivity(intent_Thongtinsach);
                return true;
            case R.id.mnThongtintacgia:
                Intent intent_Thongtintacgia = new Intent(MainActivity.this, AuthorActi.class);
                startActivity(intent_Thongtintacgia);
                return true;
           /* case R.id.mnTimkiem:
                Intent intent_Timkiem = new Intent(MainActivity.this, BookActi.class);
                startActivity(intent_Timkiem);
                return true;*/
            default: return super.onOptionsItemSelected(item);
        }

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

