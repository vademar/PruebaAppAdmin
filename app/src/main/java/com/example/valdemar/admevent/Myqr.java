package com.example.valdemar.admevent;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Myqr extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView escannerView;
    private TextView LECTURA, EVENTO;
    private String Dato, NOM;
    private Bundle B;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myqr);
        EVENTO=(TextView)findViewById(R.id.EVENTO);
        Intent detalle = getIntent();
        B = getIntent().getExtras();
        RellenoDelIntenet();
    }

    private void RellenoDelIntenet() {
        if(B != null){
                //Toast.makeText(this, "son iguales", Toast.LENGTH_SHORT).show();
                EVENTO.setText(B.getString("nom"));
        }
    }

    public void EscanerQR(View view){
        escannerView =new ZXingScannerView(this);
        setContentView(escannerView);
        escannerView.setResultHandler(this);
        escannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Dato = result.getText();
        setContentView(R.layout.activity_myqr);
        escannerView.stopCamera();
        LECTURA= (TextView)findViewById(R.id.LECTURA);
        LECTURA.setText(Dato);
        control();
    }

    private void control() {
        //REGISTRAR AL USUARIO EN UN EVENTO PREVIAMENTE REGISTRADO
        Toast.makeText(this,"User: "+Dato,Toast.LENGTH_LONG).show();
    }
}
