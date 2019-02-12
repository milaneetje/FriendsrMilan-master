package com.example.milan.friendsrmilan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friends = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] names = {"Arya", "Cersei", "Daenerys", "Jaime", "Jon", "Jorah", "Margaery",
                          "Melisandre", "Sansa", "Tyrion"};

        String bio = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo " +
                "ligula eget dolor. Aenean massa.";

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            friends.add(new Friend(name, bio, getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName())));
        }

        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        GridView gridview = findViewById(R.id.grid);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new GridItemClickListener());
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Friend clickedFriend = (Friend) adapterView.getItemAtPosition(i);

            System.out.println("Dit is: " + clickedFriend.getName());

            Intent intent = new Intent(MainActivity.this, activity_profile.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }


}
