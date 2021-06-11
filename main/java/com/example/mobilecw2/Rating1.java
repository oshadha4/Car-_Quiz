package com.example.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Rating1 extends AppCompatActivity {

    //creating variables
    ImageView imgview;
    String picture_url;
    Bitmap mvImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating1);

        imgview = findViewById(R.id.imgview);

        Intent intent = getIntent(); //new intent
        String Film_name = intent.getStringExtra("name");

        new Thread(new ratingRunnable(Film_name)).start();
    }

    class ratingRunnable implements Runnable {
        String name_requested;

        ratingRunnable(String name) {
            name_requested = name;
        }

        @Override
        public void run() {
            StringBuilder stb = new StringBuilder("");
            StringBuilder id = new StringBuilder("");
            StringBuilder title = new StringBuilder("");
            StringBuilder description = new StringBuilder("");

            try {
                // make the connection and receive the input stream
                URL url = new URL("https://imdb-api.com/en/API/SearchMovie/k_783f4uy0/" +
                        name_requested.trim());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                // read all lines in a stringbuilder
                String line;
                while ((line = bf.readLine()) != null) {
                    stb.append(line);
                }

                /* do the JSON parsing */
                JSONObject json = new JSONObject(stb.toString());
                JSONArray jsonArray = json.getJSONArray("results");

                // find the matching cocktail name entry and extract recipe
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_drink = jsonArray.getJSONObject(i);
                    String Film_name = json_drink.getString("title");
                    if (Film_name.toLowerCase().equals(name_requested.toLowerCase())) {
                        id.append(json_drink.getString("id"));
                        title.append(json_drink.getString("title"));
                        description.append(json_drink.getString("description"));
                        picture_url = json_drink.getString("image");
                    }

                }

            } catch (MalformedURLException er) {
                er.printStackTrace();
            } catch (IOException er) {
                er.printStackTrace();
            } catch (JSONException er) {
                er.printStackTrace();
            }

            mvImg = getBitmap();

            /* update the textview with the recipe */
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imgview.setImageBitmap(mvImg);

                }
            });
        }


        // retrieve a bitmap image from the URL in JSON
        Bitmap getBitmap() {
            Bitmap bitmap = null;
            try {
                URL url = new URL(picture_url);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedInputStream bfstream = new BufferedInputStream(con.getInputStream());

                bitmap = BitmapFactory.decodeStream(bfstream);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            return bitmap;  //returning the bitmap
        }
    }
}