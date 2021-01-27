package com.example.practicesns.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.practicesns.R;
import com.example.practicesns.view.login.BoardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationBar extends AppCompatActivity {

    Fragment profileFragment;
    Fragment chatFragment;
    Fragment boardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        profileFragment=new ProfileFragment();
        chatFragment=new ChatFragment();
        boardFragment=new BoardFragment();

        setDefaultFragment();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_friends:

                        Toast.makeText(NavigationBar.this, "friends", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_chat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,chatFragment).commit();
                        Toast.makeText(NavigationBar.this, "chatting", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                        Toast.makeText(NavigationBar.this, "profile", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
    public void setDefaultFragment(){
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container,profileFragment);
        transaction.commit();
    }
}