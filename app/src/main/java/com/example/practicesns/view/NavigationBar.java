package com.example.practicesns.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.practicesns.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

public class NavigationBar extends AppCompatActivity implements OnTabItemSelectedListener,OnRequestListener{
    private static final String TAG="NavigationBar";
    final static String Nick="";
    final static String ProImg="";

    Fragment profileFragment;
    Fragment writeFragment;
    Fragment boardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Intent intent=getIntent();

        String Nick=intent.getStringExtra("nick");
        String ProImg=intent.getStringExtra("proimg");

        Log.i(TAG,"invoke: id="+Nick);

        profileFragment=new ProfileFragment();
        writeFragment=new WriteFragment();
        boardFragment=new BoardFragment();

        Bundle bundle = new Bundle();
        bundle.putString("proimg",ProImg);
        bundle.putString("nick",Nick); //fragment1로 번들 전달
        profileFragment.setArguments(bundle);

        setDefaultFragment();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_board:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, boardFragment).commit();
                        Toast.makeText(NavigationBar.this, "friends", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_write:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,writeFragment).commit();
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

    @Override
    public void onRequest(String command) {
        
    }

    @Override
    public void onTabSelected(int position) {

    }
}