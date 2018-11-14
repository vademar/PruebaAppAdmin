package com.example.valdemar.admevent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.valdemar.admevent.Box.Event;
import com.example.valdemar.admevent.Box.EventAdapter;
import com.example.valdemar.admevent.Box.Profe;
import com.example.valdemar.admevent.Box.ProfeAdapter;
import com.example.valdemar.admevent.Hosting.Host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        LISTINFO = new ArrayList<Profe>();
        LIST =(ListView)findViewById(R.id.LISTPROFESION);
        Elrelleno();
        LIST.setOnItemClickListener(this);
        VPROFES =(EditText)findViewById(R.id.VPROFE);
        VCOSTOS = (EditText)findViewById(R.id.VCOSTO);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String NOM = this.LISTINFO.get(position).getPROFES();
        String PRE = this.LISTINFO.get(position).getPRECIO();
        VPROFES.setText(NOM);
        VCOSTOS.setText(PRE);
    }
}
