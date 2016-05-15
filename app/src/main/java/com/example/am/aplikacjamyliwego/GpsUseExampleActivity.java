package com.example.am.aplikacjamyliwego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GpsUseExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_use_example);

        final GpsUseExampleActivity activityObj = this;

        Button clickButton = (Button) findViewById(R.id.button);
        if (clickButton != null){
            clickButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Gps tmp = new Gps(activityObj);

                    tmp.getLongitude();
                    TextView latitude = (TextView)findViewById(R.id.latitudeText);
                    TextView longitude = (TextView)findViewById(R.id.longitudeText);

                    latitude.setText((String.format("%.6f", tmp.getLatitude())));
                    longitude.setText((String.format("%.6f", tmp.getLongitude())));
                }
            });
        }
    }
}
