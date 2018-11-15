package com.example.valdemar.admevent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.valdemar.admevent.Box.Event;
import com.example.valdemar.admevent.Box.EventAdapter;
import com.example.valdemar.admevent.Box.Profe;
import com.example.valdemar.admevent.Box.ProfeAdapter;
import com.example.valdemar.admevent.Hosting.Host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Prof extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView LIST;
    private ArrayList<Profe> LISTINFO;
    private ProfeAdapter ADAPTER;
    private Context root;
    private EditText VPROFES, VCOSTOS;
    private Button ADD, UPDATE, DELETE;
    private String PRof, PRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        LISTINFO = new ArrayList<Profe>();
        LIST =(ListView)findViewById(R.id.LISTPROFESION);
        VPROFES =(EditText)findViewById(R.id.VPROFE);
        VCOSTOS = (EditText)findViewById(R.id.VCOSTO);
        ADD = (Button)findViewById(R.id.P_AGREGAR);
        UPDATE = (Button)findViewById(R.id.P_UPDATE);
        DELETE = (Button)findViewById(R.id.P_DELETE);

        Elrelleno();
        LIST.setOnItemClickListener(this);
    }
    private void Elrelleno() {
        AsyncHttpClient profes = new AsyncHttpClient();
        profes.get(Host.Rest_Profesiones, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Profesion");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String nom = obj.optString("profesiones");
                        String pre = obj.optString("precio");
                        Profe profee = new Profe(nom,pre);
                        LISTINFO.add(profee);
                    }

                    ADAPTER= new ProfeAdapter(root,LISTINFO);
                    LIST.setAdapter(ADAPTER);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void AddProfesion(View view){
        PRof= VPROFES.getText().toString();
        PRec=VCOSTOS.getText().toString();

        if (PRof.length()== 0) {
            Toast.makeText(this,"Empty Profession", Toast.LENGTH_SHORT).show();
        }else if (PRec.length()== 0) {
            Toast.makeText(this,"Empty Price", Toast.LENGTH_SHORT).show();
        }
        if(PRof.length()!=0 && PRec.length()!=0){
            if(PRof.matches("[aA-zZ,ñÑ]*")){
                AsyncHttpClient InputProf = new AsyncHttpClient();
                RequestParams param = new RequestParams();
                param.put("profesiones",PRof);
                param.put("precio",PRec);
                InputProf.post(Host.Rest_Profesiones, param, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String msn = response.getString("msn");
                            Intent inte = new Intent(root,Prof.class);
                            root.startActivity(inte);
                            Toast.makeText(root,"Successfully Registered.",Toast.LENGTH_SHORT).show();
                            Toast.makeText(root, response.getString("Successfully Registered"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            String msn = errorResponse.getString("msn");
                            Toast.makeText(root,"Error when adding",Toast.LENGTH_SHORT).show();
                            Intent inte = new Intent(root,Prof.class);
                            root.startActivity(inte);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else{
                Toast.makeText(root,"Only Letters in the Field are Permitted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void DeleteProfesion(View view){
        Toast.makeText(this,"Presionando borrar", Toast.LENGTH_SHORT).show();
    }

    public void UpdateProfesion(View view){
        Toast.makeText(this,"precionando actualizar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String NOM = this.LISTINFO.get(position).getPROFES();
        String PRE = this.LISTINFO.get(position).getPRECIO();
        VPROFES.setText(NOM);
        VCOSTOS.setText(PRE);
    }
}
