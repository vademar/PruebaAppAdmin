package com.example.valdemar.admevent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.valdemar.admevent.Box.EventAdapter;
import com.example.valdemar.admevent.Hosting.Host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.valdemar.admevent.Box.Event;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListEvent extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView LIST;
    private ArrayList<Event> LISTINFO;
    private EventAdapter ADAPTER;
    private Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);
        LISTINFO = new ArrayList<Event>();
        LIST =(ListView)findViewById(R.id.LISTEVENT);
        Elrelleno();
        LIST.setOnItemClickListener(this);
    }

    private void Elrelleno() {
        AsyncHttpClient Events = new AsyncHttpClient();
        Events.get(Host.Rest_Eventi, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("event");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String IDe = obj.optString("_id");
                        String nom = obj.optString("nombre");
                        String fechi = obj.optString("fechaIni");
                        String hori = obj.optString("horaIni");
                        String fechf = obj.optString("fechaFin");
                        String horf = obj.optString("horaFin");
                        String des = obj.optString("descripcion");
                        String cos = obj.optString("invitados");
                        Event evento = new Event(IDe,nom,fechi,hori,fechf,horf,des,cos);
                        LISTINFO.add(evento);
                    }

                    ADAPTER= new EventAdapter(root,LISTINFO);
                    LIST.setAdapter(ADAPTER);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String NOM = this.LISTINFO.get(position).getNOMB();
        String FECI = this.LISTINFO.get(position).getFECI();
        String FECF = this.LISTINFO.get(position).getFECF();
        String HORI= this.LISTINFO.get(position).getHORI();
        String HORF = this.LISTINFO.get(position).getHORF();
        String DES = this.LISTINFO.get(position).getDESC();
        String COS = this.LISTINFO.get(position).getCOST();

        Intent detalle = new Intent(root,Myqr.class);
        detalle.putExtra("nom",NOM);
        this.startActivity(detalle);
    }
}
