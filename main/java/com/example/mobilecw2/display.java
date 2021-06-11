package com.example.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class display extends AppCompatActivity {

    //creating varibales
    dataBaseManager db;
    ArrayList<String> listItem,listFavourite;
    ArrayAdapter adp;
    ListView ch1;
    Button add;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        add = findViewById(R.id.add);

        db = new dataBaseManager(this);

        ch1 = (ListView) findViewById(R.id.listV);
        ch1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);   //giving choice method

        //creating array lists
        listItem =new ArrayList<>();
        listFavourite =new ArrayList<>();

        viewData();         //calling the viewData method

        ch1.setOnItemClickListener(new AdapterView.OnItemClickListener() {        //creating on click listner to list veiw
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (listFavourite.contains(selectedItem)) {
                    listFavourite.remove(selectedItem);
                } else {
                    listFavourite.add(selectedItem);
                }
            }
        });
        }

    private void viewData() {                              //viewData method
        Cursor cursor = db.displaydata();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(0));
                Collections.sort(listItem, String.CASE_INSENSITIVE_ORDER); //alphabetic order
            }
            adp = new ArrayAdapter(this, R.layout.rowdisplay,R.id.txtlan ,listItem);
            ch1.setAdapter(adp);

        }
    }

    public void showSelectedItems(View view) {                   //show selected items method

        String items = "";
        for(String item:listFavourite){
            items+="_"+item+"\n";
        }
        Toast.makeText(this,"you have selected \n"+items,Toast.LENGTH_LONG).show();

    }
}