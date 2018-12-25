package com.example.valdemar.admevent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.valdemar.admevent.Box.Admi;
import com.example.valdemar.admevent.Box.Admidapter;
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

public class Admins extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView LIST;
    private ArrayList<Admi> LISTINFO;
    private Admidapter ADAPTER;
    private Context root;
    private EditText ANOMBRES, APASS;
    private Button ADD, DELETE;
    private String Anom, Apas, PId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins);
        root = this;
        LISTINFO = new ArrayList<Admi>();
        LIST =(ListView)findViewById(R.id.ADM_lista);
        ANOMBRES =(EditText)findViewById(R.id.ANOMBRE);
        APASS = (EditText)findViewById(R.id.APSS);
        ADD = (Button)findViewById(R.id.A_agregar);
        DELETE = (Button)findViewById(R.id.A_borrar);
        Elrelleno();
        LIST.setOnItemClickListener(this);
    }
    private void Elrelleno(){
        AsyncHttpClient admin = new AsyncHttpClient();
        admin.get(Host.Rest_Admin, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("admis");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String idp = obj.optString("_id");
                        String nom = obj.optString("nombre");
                        String ctr = obj.optString("pass");
                        Admi admii = new Admi(idp,nom,ctr);
                        LISTINFO.add(admii);
                    }
                    ADAPTER= new Admidapter(root,LISTINFO);
                    LIST.setAdapter(ADAPTER);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void Agregar(View view){
        Anom= ANOMBRES.getText().toString();
        Apas= APASS.getText().toString();
        if (Anom.length()== 0) {
            Toast.makeText(this,"Empty User", Toast.LENGTH_SHORT).show();
        }else if (Apas.length()== 0) {
            Toast.makeText(this,"Empty Password", Toast.LENGTH_SHORT).show();
        }
        if(Anom.length()!=0 && Apas.length()!=0){
            if(Anom.matches("[aA-zZñÑ áéíóú]*")){
                AsyncHttpClient InputAdm = new AsyncHttpClient();
                RequestParams param = new RequestParams();
                param.put("nombre",Anom);
                param.put("pass",Apas);
                InputAdm.post(Host.Rest_Admin, param, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String msn = response.getString("msn");
                            Intent inte = new Intent(root,Admins.class);
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
            }else{
                Toast.makeText(root,"Only Letters in the Field are Permitted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Borrar(View view) {
        Anom = ANOMBRES.getText().toString();
        Apas = APASS.getText().toString();
        if (Anom.length() == 0) {
            Toast.makeText(this, "Empty User", Toast.LENGTH_SHORT).show();
        } else if (Apas.length() == 0) {
            Toast.makeText(this, "Empty Password", Toast.LENGTH_SHORT).show();
        }
        if(Anom.length()!=0 && Apas.length()!=0){
            AsyncHttpClient DelAdmin = new AsyncHttpClient();
            RequestParams param = new RequestParams();
            DelAdmin.delete(Host.Rest_Admin+Anom, param, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String msn = response.getString("msn");
                        Intent inte = new Intent(root,Admins.class);
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
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PId = this.LISTINFO.get(position).getIDPRO();
        String NOM = this.LISTINFO.get(position).getNOMBRE();
        String PRE = this.LISTINFO.get(position).getCONTRASEÑA();
        ANOMBRES.setText(NOM);
        APASS.setText(PRE);
    }
}
