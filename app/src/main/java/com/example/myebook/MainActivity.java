
package com.example.myebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myebook.Models.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    ArrayList<DataModel> resourceData = new ArrayList<DataModel>();
    private ImageView appCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInit();
        setRecyclerView();
        try {
            getJSONData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getJSONData() throws IOException, JSONException {

        String json;
        InputStream is = getAssets().open("short_story.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        json = new String(buffer,"UTF-8");
        JSONArray array = new JSONArray(json);



        for (int i = 0; i < array.length(); i++){

            JSONObject jsonObject = array.getJSONObject(i);
            resourceData.add(new DataModel(jsonObject.getString("title"), jsonObject.getString("description"), jsonObject.getString("moral"),  jsonObject.getString("image")));
        }

        Collections.shuffle(resourceData);

    }

    public void setRecyclerView(){
        RecyclerAdapter adapter = new RecyclerAdapter(this, resourceData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    public void appCart(View view){

        Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Nehal+App+Studio");
        Intent goToPalyStoreProfilePage = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(goToPalyStoreProfilePage);
    }

    public void viewInit(){

//        filterSpinner = findViewById(R.id.filterSpinner);
        recyclerView = findViewById(R.id.recyclerView);
        appCart = findViewById(R.id.appCart);
    }
}