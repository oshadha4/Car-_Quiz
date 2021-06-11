package com.example.mobilecw2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button insertData, display, edit, search, ratings, favourite; //creating variable names

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertData = findViewById(R.id.register);
        insertData.setOnClickListener(new View.OnClickListener() { //creating on click listner to register data
            @Override
            public void onClick(View v) {
                startdb();
            }
        });

        display = findViewById(R.id.displaybtn);
        display.setOnClickListener(new View.OnClickListener() { //creating on click listner to display data
            @Override
            public void onClick(View v) {
                display();
            }
        });

        edit = findViewById(R.id.editbtn);
        edit.setOnClickListener(new View.OnClickListener() { //creating on click listner to edit data
            @Override
            public void onClick(View v) {
                edit();
            }
        });

        search = findViewById(R.id.searchbtn);
        search.setOnClickListener(new View.OnClickListener() { //creating on click listner to search data
            @Override
            public void onClick(View v) {
                search();
            }
        });

        ratings = findViewById(R.id.ratingsbtn);
        ratings.setOnClickListener(new View.OnClickListener() { //creating on click listner to rating data
            @Override
            public void onClick(View v) {
                ratings();
            }
        });

        favourite = findViewById(R.id.favouritebtn);
        favourite.setOnClickListener(new View.OnClickListener() { //creating on click listner to favourite button
            @Override
            public void onClick(View v) {
                favourite();
            }
        });
    }



    public void startdb() {               //creating new intent
        new dataBaseManager(this);
        Intent intent = new Intent(this, com.example.mobilecw2.InsertData.class);
        startActivity(intent);
    }

    public void display() {                       //creating new intent
        new dataBaseManager(this);
        Intent intent2 = new Intent(this, com.example.mobilecw2.display.class);
        startActivity(intent2);

    }

    public void edit() {                              //creating new intent
        new dataBaseManager(this);
        Intent intent3 = new Intent(this, com.example.mobilecw2.Edit.class);
        startActivity(intent3);
    }
    public void search() {                        //creating new intent
        new dataBaseManager(this);
        Intent intent4 = new Intent(this, com.example.mobilecw2.searchfilm.class);
        startActivity(intent4);
    }
    public void ratings(){                       //creating new intent
        new dataBaseManager(this);
        Intent intent5 = new Intent(this, com.example.mobilecw2.Ratings.class);
        startActivity(intent5);
    }
    private void favourite() {                         //creating new intent
        new dataBaseManager(this);
        Intent intent6 = new Intent(this, com.example.mobilecw2.Favourite.class);
        startActivity(intent6);
    }

}