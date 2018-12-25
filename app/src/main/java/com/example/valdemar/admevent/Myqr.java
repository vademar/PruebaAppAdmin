package com.example.valdemar.admevent;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valdemar.admevent.Hosting.Host;
import com.google.zxing.Result;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Myqr extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView escannerView;
    private TextView LECTURA, EVENTO;
    private String Dato, ADevento;
    private TemplatePDF templatePDF;
    private Bundle B;
    private ListView LIST;
    private ArrayList<String[]> ARRAYLIST, rows;
    private ArrayAdapter<String[]> ADAPTER;
    private String[]header;
    private Button Reg;
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myqr);
        RellenoDelIntenet();
        Reg = (Button)findViewById(R.id.registros);
        ARRAYLIST = new ArrayList<String[]>();
        LIST = (ListView)findViewById(R.id.USUARIOS);
        ADAPTER = new ArrayAdapter<String[]>(this,R.layout.support_simple_spinner_dropdown_item, ARRAYLIST);
        LIST.setAdapter(ADAPTER);
        rows = new ArrayList<String[]>();
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.createTable(header,getClients());
    }
    private ArrayList<String[]>getClients(){
        return rows;
    }
    private void RellenoDelIntenet() {
        Intent detalle = getIntent();
        B = getIntent().getExtras();
        if(B != null){
            ADevento = B.getString("nom");
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
    }

    public void agregar(View view){
        Reg = (Button)findViewById(R.id.registros);
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR);
        int min = c.get(Calendar.MINUTE);
        if(Dato != null){
            i++;
            String num = String.valueOf(i);
            String Time = String.valueOf(hora+":"+min);
            ARRAYLIST.add(new String[]{num,Dato,Time});
            ADAPTER.notifyDataSetChanged();
            rows = (ArrayList<String[]>) ARRAYLIST.clone();
            Reg.setEnabled(false);
            Toast.makeText(this,i+" Usuario, Registrado",Toast.LENGTH_SHORT).show();
            AsyncHttpClient InputReg = new AsyncHttpClient();
            RequestParams param = new RequestParams();
            param.put("eventos",ADevento);
            param.put("usuarios",Dato);
            InputReg.post(Host.Rest_Regitrar, param, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String msn = response.getString("msn");
                        Toast.makeText(getApplicationContext(),msn,Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        String msn = errorResponse.getString("msn");
                        Toast.makeText(getApplicationContext(),msn,Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Aún no ha Hecho lectura de Datos", Toast.LENGTH_SHORT).show();
            Reg.setEnabled(true);
        }
    }
    public void createPDF(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Al generar el PDF, ya no podra ingresar mas usuarios");
        builder.setTitle("¡AVISO!");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                templatePDF.openDocument(ADevento);
                templatePDF.appviewPdF(Myqr.this);
                templatePDF.addTitles(ADevento,"Lista de registros","");
                header= new String[]{"Nº","Datos Personales","Hr Ingreso"};
                templatePDF.createTable(header,getClients());
                templatePDF.closeDocument();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
