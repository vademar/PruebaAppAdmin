package com.example.valdemar.admevent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.valdemar.admevent.Box.costo;
import com.example.valdemar.admevent.Hosting.Host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class EventEditions extends AppCompatActivity {
    private TextView nomb,fecI,horI,fecF,horF,desc,cost;
    private Button BtnFECI,BtnFECF,BtnHORI,BtnHORF ,BtnCOST;
    private int year,mes,dia, hora, min;
    private Context root;
    private ArrayList<costo> Profess;
    private String[] ListProfes;
    private ArrayList<Integer> lasprofesiones = new ArrayList<>();
    private boolean[] CheckProfes;
    private String EvID,NOMB,FECI,HORI,FECF,HORF,DESC,COST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_editions);

        nomb=(TextView)findViewById(R.id.ED_name);
        fecI=(TextView)findViewById(R.id.ED_Fechai);
        fecF=(TextView)findViewById(R.id.ED_Fechaf);
        horI=(TextView)findViewById(R.id.ED_Horai);
        horF=(TextView)findViewById(R.id.ED_Horaf);
        desc=(TextView)findViewById(R.id.ED_Descripcion);
        cost=(TextView)findViewById(R.id.ED_Participantes);

        BtnFECI = (Button)findViewById(R.id.ED_B_fechai);
        BtnFECF = (Button)findViewById(R.id.ED_B_fechaf);
        BtnHORI = (Button)findViewById(R.id.ED_B_horai);
        BtnHORF = (Button)findViewById(R.id.ED_B_horaf);
        BtnCOST = (Button)findViewById(R.id.ED_B_Participantes);

        Profess= new ArrayList<costo>();

        Rellenar();
        Lasprofesione();
    }

    private void Rellenar() {
        Intent detalle = getIntent();
        Bundle B = getIntent().getExtras();
        if (B != null){
            EvID = B.getString("eid");
            nomb.setText(B.getString("nom"));
            fecI.setText(B.getString("fei"));
            horI.setText(B.getString("hoi"));
            fecF.setText(B.getString("fef"));
            horF.setText(B.getString("hof"));
            desc.setText(B.getString("des"));
            cost.setText(B.getString("cos"));
        }
    }

    private void Lasprofesione() {
        final AsyncHttpClient proofes = new AsyncHttpClient();
        proofes.get(Host.Rest_Profesiones, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Profesion");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        costo ss = new costo();
                        ss.setProfesion(obj.optString("profesiones"));
                        ss.setPrecio(obj.optString("precio"));
                        //Toast.makeText(root,"obj"+obj.optString("profesiones"), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(root,"obj"+obj.optString("precio"), Toast.LENGTH_SHORT).show();
                        Profess.add(ss);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ListProfes = new String[Profess.size()];
                for (int i = 0; i < Profess.size(); i++) {
                    ListProfes [i] = String.valueOf(Profess.get(i));
                }
                CheckProfes = new boolean[ListProfes.length];
            }
        });
    }

    public void BTNFechaI(View View){
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog Ponerfecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fecI.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                //aqui hacer las validaciones antes de ingresarlos a los textos
                //fechaI=Fecha.getText().toString();
            }
        },year,mes,dia);
        Ponerfecha.show();
    }

    public void BTNFechaF(View View){
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog Ponerfecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fecF.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                //aqui hacer las validaciones antes de ingresarlos a los textos
                //fechaI=Fecha.getText().toString();
            }
        },year,mes,dia);
        Ponerfecha.show();
    }

    public void BTNHoraI(View view){
        final Calendar c = Calendar.getInstance();

        hora = c.get(Calendar.HOUR);
        min = c.get(Calendar.MINUTE);
        TimePickerDialog ponerhora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horI.setText(hourOfDay+" : "+minute);
                //horaI=Hora.getText().toString();
            }
        },hora,min,false);
        ponerhora.show();
    }

    public void BTNHoraF(View view){
        final Calendar c = Calendar.getInstance();

        hora = c.get(Calendar.HOUR);
        min = c.get(Calendar.MINUTE);
        TimePickerDialog ponerhora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horF.setText(hourOfDay+" : "+minute);
                //horaI=Hora.getText().toString();
            }
        },hora,min,false);
        ponerhora.show();
    }

    public void ProfesionesVER(View view){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Elija Los Participantes");
        mBuilder.setMultiChoiceItems(ListProfes, CheckProfes, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                if(isChecked){
                    if(!lasprofesiones.contains(position)){
                        lasprofesiones.add(position);
                    }
                }else if(lasprofesiones.contains(position)){
                    lasprofesiones.remove((Integer) position);
                }
            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String item="";
                for(int i=0; i<lasprofesiones.size();i++){
                    item = item + ListProfes[lasprofesiones.get(i)];
                    if(i!=lasprofesiones.size()-1){
                        item =  item + "\n";
                    }
                }
                cost.setText(item);
            }
        });

        mBuilder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        mBuilder.setNeutralButton("Borrar Todo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i=0; i<CheckProfes.length;i++){
                    CheckProfes[i]=false;
                    lasprofesiones.clear();
                    cost.setText("");
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    public void UpdateEvent(View view){
        NOMB = nomb.getText().toString();
        FECI = fecI.getText().toString();
        HORI = horI.getText().toString();
        FECF = fecF.getText().toString();
        HORF = horF.getText().toString();
        DESC = desc.getText().toString();
        COST = cost.getText().toString();
        if (NOMB.length()== 0) {
            Toast.makeText(this,"Ingrese Nombre Para Evento", Toast.LENGTH_SHORT).show();
        }
        if (FECI== null) {
            Toast.makeText(this,"La Fecha Inicio es Necesario", Toast.LENGTH_SHORT).show();
        }
        if (HORI== null) {
            Toast.makeText(this,"La Hora Inicio es Necesario", Toast.LENGTH_SHORT).show();
        }
        if (FECF== null) {
            Toast.makeText(this,"La Fecha Final es Necesario", Toast.LENGTH_SHORT).show();
        }
        if (HORF== null) {
            Toast.makeText(this,"La Hora Final es Necesario", Toast.LENGTH_SHORT).show();
        }
        if (DESC.length()==0){
            Toast.makeText(this,"Describa Su Evento", Toast.LENGTH_SHORT).show();
        }

        if(NOMB.length()!=0 && FECI!=null && HORI!= null && FECF!= null && HORF!= null && DESC.length()!=0){
                AsyncHttpClient UpdateUser = new AsyncHttpClient();
                RequestParams param = new RequestParams();
                param.put("nombre",NOMB);
                param.put("fechaIni",FECI);
                param.put("horaIni",HORI);
                param.put("fechaFin",FECF);
                param.put("horaFin",HORF);
                param.put("descripcion",DESC);
                param.put("invitados",COST);
                UpdateUser.put(Host.Rest_Eventi+EvID, param, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String msn = response.getString("msn");
                            Intent inte = new Intent(root,Menu.class);
                            root.startActivity(inte);
                            finish();
                            Toast.makeText(root,msn,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            String msn = errorResponse.getString("msn");
                            Toast.makeText(root,msn,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        }
    }

    public void DeleteEvent(View view){
        NOMB = nomb.getText().toString();
        FECI = fecI.getText().toString();
        HORI = horI.getText().toString();
        FECF = fecF.getText().toString();
        HORF = horF.getText().toString();
        DESC = desc.getText().toString();
        COST = cost.getText().toString();
        if (NOMB.length()== 0) {
            Toast.makeText(this,"Ingrese Nombre Para Evento", Toast.LENGTH_SHORT).show();
        }
        if(NOMB.length()!=0 && FECI!=null && HORI!= null && FECF!= null && HORF!= null && DESC.length()!=0){
            AsyncHttpClient UpdateUser = new AsyncHttpClient();
            RequestParams param = new RequestParams();
            UpdateUser.delete(Host.Rest_Eventi+NOMB, param, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String msn = response.getString("msn");
                        Intent inte = new Intent(root,Menu.class);
                        root.startActivity(inte);
                        finish();
                        Toast.makeText(root,msn,Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        String msn = errorResponse.getString("msn");
                        Toast.makeText(root,msn,Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
