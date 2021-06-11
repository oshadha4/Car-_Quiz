package com.example.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ratings extends AppCompatActivity {

    TextView tv;
    EditText edt;
    ImageView imgview;

    TextView tv2;
    EditText edt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        tv = findViewById(R.id.tv);
        edt = findViewById(R.id.edt);
        imgview = findViewById(R.id.imgview);
        tv2 = findViewById(R.id.tv2);
        edt2 = findViewById(R.id.edt2);
    }

    public void getNames(View view) {
        String details = edt.getText().toString();

        new Thread(new FilmNamesRunnable(details)).start();
    }

    public void getRecipe(View view) {
        String film = edt2.getText().toString();
        Intent intent1 = new Intent(this, Rating1.class);
        intent1.putExtra("name", film);
        startActivity(intent1);
    }


    class FilmNamesRunnable implements Runnable {
        String details;

        FilmNamesRunnable(String detail) {
            details = detail;
        }

        @Override
        public void run() {
            StringBuilder stb = new StringBuilder("");  // contains all json
            StringBuilder stb2 = new StringBuilder("");  // contains all drink names

            try {
                // make the connection and receive the input stream
                URL url = new URL("https://imdb-api.com/en/API/SearchMovie/k_783f4uy0/" +
                        details.trim());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                // read all lines info in a stringbuilder
                String line;
                while ((line = bf.readLine()) != null) {
                    stb.append(line);
                }

                /* do the JSON parsing */
                JSONObject json = new JSONObject(stb.toString());
                JSONArray jsonArray = json.getJSONArray("results");

                // find the cocktail names entries and put them in stb2
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_drink = jsonArray.getJSONObject(i);
                    String cocktail_name = json_drink.getString("title");
                    stb2.append(cocktail_name + "\n");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            /* update the textview with the names of cocktails containing
               the ingredient entered by the user
             */
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv.setText(stb2.toString());
                }
            });
        }
    }
}