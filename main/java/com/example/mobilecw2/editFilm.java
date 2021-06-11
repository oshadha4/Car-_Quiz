package com.example.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class editFilm extends AppCompatActivity {

    //creating variables
    ArrayList<String> title, year, director, actors, rating, review;
    Button save;
    String pretitle;
    dataBaseManager dataBaseManager;
    RatingBar ratingBar;
    EditText etitle, eyear, edirector, eactors, erating, ereview;
    String sttitle, styear, stdirector, stactor, strating, streview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_film);

        pretitle = getIntent().getStringExtra("title");

        //assigning variables to the array lists
        title = new ArrayList<>();
        year = new ArrayList<>();
        director = new ArrayList<>();
        actors = new ArrayList<>();
        rating = new ArrayList<>();
        review = new ArrayList<>();

        dataBaseManager = new dataBaseManager(editFilm.this);
        editDataUp();

        if(title.contains(pretitle)){
            int i = title.indexOf(pretitle);
            sttitle = title.get(i);
            styear = year.get(i);
            stdirector = director.get(i);
            stactor = actors.get(i);
            strating = rating.get(i);
            streview = review.get(i);


        }

        dataBaseManager = new dataBaseManager(editFilm.this);

        //assigning edit texts
        etitle = findViewById(R.id.txt1);
        etitle.setText(sttitle);
        eyear = findViewById(R.id.txt2);
        eyear.setText(styear);
        edirector = findViewById(R.id.txt3);
        edirector.setText(stdirector);
        eactors = findViewById(R.id.txt4);
        eactors.setText(stactor);
        erating = findViewById(R.id.txt5);
        erating.setText(strating);
        ereview = findViewById(R.id.txt6);
        ereview.setText(streview);

        //rating bar
        ratingBar = findViewById(R.id.ratingBarEdit);
        ratingBar.setRating(Float.parseFloat(strating));

    }

    public void editDataUp(){      //editDataUp method
        Cursor crs = dataBaseManager.editDataUp();
        if(crs.getCount()== 0) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        } else{
            while (crs.moveToNext()){
                title.add(crs.getString(0));
                year.add(crs.getString(1));
                director.add(crs.getString(2));
                actors.add(crs.getString(3));
                rating.add(crs.getString(4));
                review.add(crs.getString(5));
            }
        }
    }
}