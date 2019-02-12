package com.example.milan.friendsrmilan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class activity_profile extends AppCompatActivity {

    Friend retrievedFriend;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        ImageView picture = findViewById(R.id.picture);
        TextView name = findViewById(R.id.Name);
        TextView bio = findViewById(R.id.Bio);

        picture.setImageResource(retrievedFriend.getDrawableId());
        name.setText(retrievedFriend.getName());
        bio.setText(retrievedFriend.getBio());

        prefs = getSharedPreferences("settings", MODE_PRIVATE);

        float rating;
        RatingBar ratingBar;

        editor= prefs.edit();
        rating = prefs.getFloat(retrievedFriend.getName(), (float) 0.0);
        ratingBar = findViewById(R.id.rating);
        ratingBar.setRating(rating);

        ratingBar.setOnRatingBarChangeListener(new RatingBarListener());

    }

    private class RatingBarListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            editor.putFloat(retrievedFriend.getName(), v);
            editor.apply();
            retrievedFriend.setRating(v);
        }
    }
}
