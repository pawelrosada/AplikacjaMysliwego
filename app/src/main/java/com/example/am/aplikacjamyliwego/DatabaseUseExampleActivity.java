package com.example.am.aplikacjamyliwego;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DatabaseUseExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_use_example);

        final DatabaseUseExampleActivity activityObj = this;

        Button clickButton = (Button) findViewById(R.id.button2);
        if (clickButton != null){
            clickButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    TextView infoText = (TextView) findViewById(R.id.infoText);

                    Database db = new Database(activityObj);
                    Gps tmp = new Gps(activityObj);

                    DrawingPin newDrawingPin = new DrawingPin();
                    newDrawingPin.setName("Nazwa testowa pinezki");
                    newDrawingPin.setLatitude(tmp.getLatitude());
                    newDrawingPin.setLongitude(tmp.getLongitude());
                    newDrawingPin.setType("Samochód");

                    if(db.addDrawingPin(newDrawingPin)) {
                        //adding success
                        if(infoText != null)
                            infoText.setText("Adding success");
                    }else{
                        //adding fail - drawing pin with this name already exist
                        if(infoText != null)
                            infoText.setText("Adding fail - drawing pin with this name already exist");
                    }

                    List<DrawingPin> drawingPins = db.getAllDrawingPins();

                    if (drawingPins.size() > 0) {
                        DrawingPin tmpDrawingPin = drawingPins.get(0);
                        TextView name = (TextView) findViewById(R.id.nameText);
                        TextView latitude = (TextView) findViewById(R.id.latitudeText);
                        TextView longitude = (TextView) findViewById(R.id.longitudeText);
                        TextView type = (TextView) findViewById(R.id.typeText);
                        if (name != null)
                            name.setText(tmpDrawingPin.getName());
                        if (latitude != null)
                            latitude.setText(String.format("%.6f", tmpDrawingPin.getLatitude()));
                        if (longitude != null)
                            longitude.setText(String.format("%.6f", tmpDrawingPin.getLongitude()));
                        if (type != null)
                            type.setText(tmpDrawingPin.getType());

                        if(db.deleteDrawingPin(tmpDrawingPin.getId())){//delete drawing pin example
                            //deleted
                            if(infoText != null)
                                infoText.setText("Deleted");
                        }else{
                            //delete fail
                            if(infoText != null)
                                infoText.setText("Delete fail");
                        }
                    }
                }
            });
        }

    }


}
