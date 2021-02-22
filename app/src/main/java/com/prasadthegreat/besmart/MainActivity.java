package com.prasadthegreat.besmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mbottomNavigationView;
    FloatingActionButton mfloatButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mbottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomNavView);
        mbottomNavigationView.setBackground(null);
        Menu menuNav=mbottomNavigationView.getMenu();
        MenuItem nav_item2 = menuNav.findItem(R.id.nothing);
        nav_item2.setEnabled(false);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new savemefragment()).commit();
        mbottomNavigationView.setOnNavigationItemSelectedListener(bottomnavMethod);

        mfloatButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        mfloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new savemefragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            }
        });

    }

    private  BottomNavigationView.OnNavigationItemSelectedListener bottomnavMethod=new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment=null;
                    switch (item.getItemId()){

                        case R.id.makeiteasy:
                            fragment=new makeiteasyfragment();
                            break;
                        case R.id.attendance:
                            fragment=new attendancefragment();
                            break;
                        case R.id.relateyouridea:
                            fragment=new relateyourideafragment();
                            break;
                        case R.id.timemanagement:
                            fragment=new timemanagementfragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                    return true;
                }
            };
}