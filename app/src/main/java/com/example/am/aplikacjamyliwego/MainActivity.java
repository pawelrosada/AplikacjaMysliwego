package com.example.am.aplikacjamyliwego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button dbButton = (Button) findViewById(R.id.dbButton);
        Button gpsButton = (Button) findViewById(R.id.gpsButton);
        Button mapButton = (Button) findViewById(R.id.mapButton);


        if (dbButton != null){
            dbButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Database example activity start
                    Intent intent = new Intent(MainActivity.this, DatabaseUseExampleActivity.class);
                    startActivity(intent);
                }
            });
        }
        if (gpsButton != null){
            gpsButton .setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //GPS example activity start
                    Intent intent = new Intent(MainActivity.this, GpsUseExampleActivity.class);
                    startActivity(intent);
                }
            });
        }
        if (mapButton != null){
            mapButton .setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //GPS example activity start
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
