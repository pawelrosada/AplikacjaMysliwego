package com.example.am.aplikacjamyliwego;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

                    TextView infoTextHunting = (TextView) findViewById(R.id.informationHunting);

                    HuntingArea newHuntingArea = new HuntingArea();
                    newHuntingArea.setName("nowy teren");
                    newHuntingArea.setDescription("opis terenu");
                    newHuntingArea.setTopLeftCorner(10.0);
                    newHuntingArea.setTopRightCorner(14.0);
                    newHuntingArea.setBottomLeftCorner(-2.0);
                    newHuntingArea.setBottomRightCorner(2.0);

                    if(db.addHuntingArea(newHuntingArea)) {
                        //adding success
                        if(infoText != null)
                            infoTextHunting.setText("Adding success");
                    }else{
                        //adding fail - drawing pin with this name already exist
                        if(infoText != null)
                            infoTextHunting.setText("Adding fail - hunting area with this name already exist");
                    }

                    List<HuntingArea> huntingAreas = db.getAllHuntingAreas();

                    if (huntingAreas.size() > 0) {
                        HuntingArea tmpHuntingArea = huntingAreas.get(0);
                        TextView name = (TextView) findViewById(R.id.nameHunting);
                        TextView description = (TextView) findViewById(R.id.descriptionHunting);
                        TextView topLeft = (TextView) findViewById(R.id.topLeft);
                        TextView topRight = (TextView) findViewById(R.id.topRight);
                        TextView bottomLeft = (TextView) findViewById(R.id.bottomLeft);
                        TextView bottomRight = (TextView) findViewById(R.id.bottomRight);

                        if (name != null)
                            name.setText(tmpHuntingArea.getName());
                        if (description != null)
                            description.setText(tmpHuntingArea.getDescription());
                        if (topLeft != null)
                            topLeft.setText(String.format("%.6f", tmpHuntingArea.getTopLeftCorner()));
                        if (topRight != null)
                            topRight.setText(String.format("%.6f", tmpHuntingArea.getTopRightCorner()));
                        if (bottomLeft != null)
                            bottomLeft.setText(String.format("%.6f", tmpHuntingArea.getBottomLeftCorner()));
                        if (bottomRight != null)
                            bottomRight.setText(String.format("%.6f", tmpHuntingArea.getBottomRightCorner()));

                        if(db.deleteHuntingArea(tmpHuntingArea.getId())){//delete drawing pin example
                            //deleted
                            if(infoText != null)
                                infoTextHunting.setText("Deleted");
                        }else{
                            //delete fail
                            if(infoText != null)
                                infoTextHunting.setText("Delete fail");
                        }
                    }
                }
            });
        }

    }


}