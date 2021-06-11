package com.example.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertData extends AppCompatActivity {
    EditText title, year, director, actors, rating, review;  //creating edit text variables
    Button create;                                             //creating button text variables
    dataBaseManager dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        title = findViewById(R.id.txt1);
        year = findViewById(R.id.txt2);
        director = findViewById(R.id.txt3);
        actors = findViewById(R.id.txt4);
        rating = findViewById(R.id.txt5);
        review = findViewById(R.id.txt6);
        dbHelper = new dataBaseManager(this);

        create = findViewById(R.id.buttonInsert); //connecting btn variable with btn id

        create.setOnClickListener(new View.OnClickListener() {       //create btn on click listner
            @Override
            public void onClick(View v) {
                int yearing = Integer.parseInt(year.getText().toString());
                int ratings = Integer.parseInt(rating.getText().toString());

                if (yearing>2021 || yearing<1895 && ratings<1 || ratings>10) {        //validation for  year and rating
                    year.setTextColor(Color.RED);
                    year.setText("Out Of Range");
                    rating.setTextColor(Color.RED);
                    rating.setText("Out Of Range");

                }

                String titleTXT = title.getText().toString();
                String yearTXT = year.getText().toString();
                String directorTXT = director.getText().toString();
                String actorsTXT = actors.getText().toString();
                String ratingTXT = rating.getText().toString();
                String reviewTXT = review.getText().toString();

                Boolean checkinsertdata = dbHelper.insertData(titleTXT,yearTXT,directorTXT,actorsTXT,ratingTXT,reviewTXT);
                if(checkinsertdata == true){
                    Toast.makeText(InsertData.this,"New Entry inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InsertData.this,"New Entry not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}