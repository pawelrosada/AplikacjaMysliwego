package com.example.am.aplikacjamyliwego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText loginInput, passwordInput;
    RequestQueue requestQueue;
    private StringRequest request12;

    String logintUrl = "http://dmenlin.ayz.pl/android/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button)findViewById(R.id.loginButton);
        loginInput = (EditText) findViewById(R.id.loginInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request12 = new StringRequest(Request.Method.POST, logintUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject json= (JSONObject) new JSONTokener(response).nextValue();
                            JSONObject json2 = json.getJSONObject("alert");
                            if(json2.get("status").toString().equals("success")){
                                ((UserLogin) getApplication()).UserId = json2.get("login").toString();
                                ((UserLogin) getApplication()).IsLogged =  true;
                                Toast.makeText(getApplicationContext(), json2.get("message").toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(), json2.get("message").toString(), Toast.LENGTH_SHORT).show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("login", loginInput.getText().toString());
                        hashMap.put("password", passwordInput.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request12);
            }
        });
    }
}
