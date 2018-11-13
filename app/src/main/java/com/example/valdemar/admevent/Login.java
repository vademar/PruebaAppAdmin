package com.example.valdemar.admevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText CI, PSS;
    private Button LOGIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CI = (EditText)findViewById(R.id.CI);
        PSS = (EditText)findViewById(R.id.PSS);
        LOGIN = (Button)findViewById(R.id.LOGIN);
    }
    public void InicioSesion(View view){

        String cedula= CI.getText().toString();
        String contraseña= PSS.getText().toString();

        String ci="654";
        String pss="ade";

        if (cedula.length()== 0) {
            Toast.makeText(this,"Ingrese Su CI", Toast.LENGTH_SHORT).show();
        }
        if (contraseña.length()== 0) {
            Toast.makeText(this,"Ingrese Su Contraseña", Toast.LENGTH_SHORT).show();
        }
        if( contraseña.compareTo(pss) == 0){
            if (cedula.compareTo(ci)==0){
                Intent adm = new Intent(this,Menu.class);
                startActivity(adm);
                finish();
            }else
                Toast.makeText(this, "Cedula De Identidad Incorrecta", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
        }
    }
}
