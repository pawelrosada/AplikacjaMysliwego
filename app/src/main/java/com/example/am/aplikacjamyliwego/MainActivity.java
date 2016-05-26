package com.example.am.aplikacjamyliwego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button dbButton = (Button) findViewById(R.id.dbButton);
        Button gpsButton = (Button) findViewById(R.id.gpsButton);
        Button mapButton = (Button) findViewById(R.id.mapButton);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final Button logoutButton = (Button) findViewById(R.id.logoutButton);
        final Button registerButton = (Button) findViewById(R.id.registerButton);

        Boolean isLogged = ((UserLogin) this.getApplication()).IsLogged;
        if(isLogged)
        {
            loginButton.setVisibility(View.GONE);
            registerButton.setVisibility(View.GONE);
            logoutButton.setVisibility(View.VISIBLE);
        }else{
            loginButton.setVisibility(View.VISIBLE);
            registerButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.GONE);
        }


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

        if (loginButton != null){
            loginButton .setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (registerButton != null){
            registerButton .setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (logoutButton != null){
            logoutButton .setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ((UserLogin) getApplication()).UserId = "0";
                    ((UserLogin) getApplication()).IsLogged =  false;

                    loginButton.setVisibility(View.VISIBLE);
                    registerButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);
                }
            });
        }

        JSONObject returnFromJson = null;
        final MainActivity activityObj = this;

        try{
            String[] get = {"http://www.huntapp.pe.hu/api.php", "GET"};
            String jsonResponce = (new JSONHandling(activityObj).execute(get)).get();
            if (jsonResponce != null) {
                    returnFromJson = new JSONObject(jsonResponce);
                    JSONHandling.parseJson(returnFromJson, activityObj);
                }

                }catch (ExecutionException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
