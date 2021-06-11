package com.example.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {

    //creating variables
    dataBaseManager db;
    ArrayList<String>listItems1;
    ArrayAdapter adpt;
    ListView userlist1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        db = new dataBaseManager(this);    //initializing the database

        listItems1 = new ArrayList<>();
        userlist1 =findViewById(R.id.listV1);

        editdata();

        userlist1.setOnItemClickListener(new AdapterView.OnItemClickListener() {               //list view on click listener
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String txt = userlist1.getItemAtPosition(position).toString();
                Intent intent = new Intent(getBaseContext(), editFilm.class);
                intent.putExtra("title", txt);
                startActivity(intent);
            }
        });

    }

    private void editdata() {          //editdata method
        Cursor cur = db.editdata();

        if(cur.getCount() == 0){
            Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while (cur.moveToNext()){
                listItems1.add(cur.getString(0));
            }

            adpt = new ArrayAdapter(this, android.R.layout.simple_list_item_1 ,listItems1);
            userlist1.setAdapter(adpt);
        }
    }
}