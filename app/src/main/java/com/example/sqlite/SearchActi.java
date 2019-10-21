package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class SearchActi extends AppCompatActivity {
    DBHelper dbHelper;
    EditText etTimkiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dbHelper = new DBHelper(this);
//        onClick();
        etTimkiem = findViewById(R.id.etTimkiem);
    }

//    private void onClick() {
//        ArrayList<String> list_string = new ArrayList<>();
//        list_string = dbHelper.getBookAuthor(Integer.parseInt(etTimkiem.toString()));
//    }

}
