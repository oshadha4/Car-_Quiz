package com.example.mobilecw2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.file.Paths;
import java.util.ArrayList;

public class searchfilm extends AppCompatActivity {

    ///creating variables
    EditText searchtxt;
    Button searchbtn;
    ArrayList<String>  title, year, director, actors , rating, review;
    dataBaseManager dbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfilm);

        searchtxt = findViewById(R.id.searchtxt);
        searchbtn = findViewById(R.id.searchbtn);

        dbase = new dataBaseManager(searchfilm.this);
        title = new ArrayList<>();
        year = new ArrayList<>();
        director = new ArrayList<>();
        actors = new ArrayList<>();
        rating = new ArrayList<>();
        review= new ArrayList<>();

        storeData();

        searchbtn.setOnClickListener(new View.OnClickListener() {            //search btn on click listner
            @Override
            public void onClick(View v) {
                String word =   searchtxt.getText().toString().toLowerCase();

                StringBuffer buffer = new StringBuffer();

                for(int i=0; i<title.size(); i++){                        //for loop for accepting capitals
                    title.set(i, title.get(i).toLowerCase());
                }

                if(title.contains(word)){
                    int index = title.indexOf(word);
                    String details = "Title : "+title.get(index) +"  Year : "+year.get(index)+"  Director : "+director.get(index)+"  Actors : "+actors.get(index)+"  Rating : "+rating.get(index)+"  Review : "+review.get(index)+"\n\n";
                    buffer.append(details);

                }

                if(director.contains(word)){
                    int index = director.indexOf(word);
                    String details = "Title : "+title.get(index)+"  Year : "+year.get(index)+"  Director : "+director.get(index)+"  Actors : "+actors.get(index)+"  Rating : "+rating.get(index)+"  Review : "+review.get(index)+"\n\n";

                    buffer.append(details);

                }
                if(actors.contains(word)){
                    int index = actors.indexOf(word);
                    String details = "Title : "+title.get(index)+"  Year : "+year.get(index)+"  Director : "+director.get(index)+"  Actors : "+actors.get(index)+"  Rating : "+rating.get(index)+"  Review : "+review.get(index)+"\n\n";

                    buffer.append(details);

                }




                AlertDialog.Builder builder = new AlertDialog.Builder(searchfilm.this);            //creating a alert dialog box
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
    }

    private void storeData() {                //store data method
        Cursor cursor = dbase.readAllData();
        if(cursor.getCount()== 0){
            Toast.makeText(this,"NO Data",Toast.LENGTH_SHORT).show();

        } else{
            while (cursor.moveToNext()){
                title.add(cursor.getString(0));
                year.add(cursor.getString(1));
                director.add(cursor.getString(2));
                actors.add(cursor.getString(3));
                rating.add(cursor.getString(4));
                review.add(cursor.getString(5));
            }
        }
    }

}