package com.example.myebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myebook.Models.DataModel;

import org.parceler.Parcels;

public class StoryDetails extends AppCompatActivity {

    private TextView title, description, moral;
    private ImageView thumbnail;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);

        initView();
        Parcelable parcelable = getIntent().getParcelableExtra("data");
        DataModel dataModel = Parcels.unwrap(parcelable);

        Toast.makeText(this, dataModel.getTitle(), Toast.LENGTH_SHORT).show();

        Glide.with(this)
                .load(dataModel.getthumbnail_url())
                .centerCrop()
                .placeholder(R.drawable.lion_and_mouse)
                .into(thumbnail);

        title.setText(dataModel.getTitle());
        description.setText(dataModel.getDescription());
        moral.setText(dataModel.getMoral());

        handleRating();
    }

    private void handleRating(){

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext()+getPackageName());
                if(v > 4){
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(playStoreIntent);
                }else {
                    Toast.makeText(StoryDetails.this, "Thank you!", Toast.LENGTH_SHORT).show();
//                    Intent goHomePage = new Intent(StoryDetails.this, MainActivity.class);
//                    startActivity(goHomePage);
                }
            }
        });
    }


    private void initView() {

        title = findViewById(R.id.storyTitle);
        description = findViewById(R.id.storyDescription);
        moral = findViewById(R.id.storyMoral);

        thumbnail = findViewById(R.id.storyThumbnail);
        ratingBar = findViewById(R.id.ratingBar);
    }

}