package com.example.valdemar.admevent;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.valdemar.admevent.Hosting.Host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {
    private EditText CI, PSS;
    private Button LOGIN;
    private Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        root = this;
        setContentView(R.layout.activity_login);
        CI = (EditText)findViewById(R.id.CI);
        PSS = (EditText)findViewById(R.id.PSS);
        LOGIN = (Button)findViewById(R.id.LOGIN);
    }
    public void InicioSesion(View view){

        String cedula= CI.getText().toString();
        String contraseña= PSS.getText().toString();

        String ci="admin";
        String pss="admin";

        if (cedula.length()== 0) {
            Toast.makeText(this,"Ingrese Su CI", Toast.LENGTH_SHORT).show();
        }
        if (contraseña.length()== 0) {
            Toast.makeText(this,"Ingrese Su Contraseña", Toast.LENGTH_SHORT).show();
        }
        /*if( contraseña.compareTo(pss) == 0){
            if (cedula.compareTo(ci)==0){
                Intent adm = new Intent(this,Menu.class);
                startActivity(adm);
                finish();
            }else
                Toast.makeText(this, "Cedula De Identidad Incorrecta", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
        }*/
        AsyncHttpClient logue = new AsyncHttpClient();
        logue.get(Host.Rest_Login+cedula+"="+contraseña, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject Login = response.getJSONObject("admin");
                    String nombre = Login.optString("nombre");
                    Toast.makeText(getApplicationContext(),"Welcome: "+nombre, Toast.LENGTH_SHORT).show();
                    Intent Delogueo = new Intent(root,Menu.class);
                    startActivity(Delogueo);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)  {
                String msn = null;
                try {
                    msn = errorResponse.getString("msn");
                    Toast.makeText(root,msn,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
