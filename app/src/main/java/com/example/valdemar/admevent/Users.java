package com.example.valdemar.admevent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.valdemar.admevent.Box.Profe;
import com.example.valdemar.admevent.Box.ProfeAdapter;
import com.example.valdemar.admevent.Box.User;
import com.example.valdemar.admevent.Box.UserAdapter;
import com.example.valdemar.admevent.Hosting.Host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Users extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView LIST;
    private ArrayList<User> LISTINFO;
    private UserAdapter ADAPTER;
    private Context root;
    private EditText NOMBRE, APELLIDO, CI, PROFESION, INSTITUCION, CARGO, PASSWORD;
    private Button UPDATE, DELETE;
    private String nombre, apellido, ci, profesion, institucion, cargo, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        LISTINFO = new ArrayList<User>();
        LIST = (ListView)findViewById(R.id.LIST_USERS);

        NOMBRE = (EditText)findViewById(R.id.Us_name);
        APELLIDO = (EditText)findViewById(R.id.Us_surname);
        CI = (EditText)findViewById(R.id.US_cedula);
        PROFESION = (EditText)findViewById(R.id.Us_profesion);
        INSTITUCION = (EditText)findViewById(R.id.Us_institution);
        CARGO = (EditText)findViewById(R.id.Us_position);
        PASSWORD = (EditText)findViewById(R.id.Us_password);

        UPDATE = (Button)findViewById(R.id.Us_update);
        DELETE = (Button)findViewById(R.id.Us_baja);
        Elrelleno();
        LIST.setOnItemClickListener(this);
    }

    private void Elrelleno() {
        AsyncHttpClient Usuarios = new AsyncHttpClient();
        Usuarios.get(Host.Rest_Registro, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("mostrar");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String nom = obj.optString("nombre");
                        String ape = obj.optString("apellido");
                        String pro= obj.optString("profesion");
                        String ins = obj.optString("institucion");
                        String car = obj.optString("cargo");
                        String cii = obj.optString("ci");
                        String pas = obj.optString("password");
                        User USer = new User(nom,ape,pro,ins,car,cii,pas);
                        LISTINFO.add(USer);
                    }

                    ADAPTER= new UserAdapter(root,LISTINFO);
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
        String APE = this.LISTINFO.get(position).getAPEL();
        String CII = this.LISTINFO.get(position).getCEDU();
        String PRO = this.LISTINFO.get(position).getPROF();
        String INS = this.LISTINFO.get(position).getINST();
        String CAR = this.LISTINFO.get(position).getCARG();
        String PAS = this.LISTINFO.get(position).getPASS();

        NOMBRE.setText(NOM);
        APELLIDO.setText(APE);
        CI.setText(CII);
        PROFESION.setText(PRO);
        INSTITUCION.setText(INS);
        CARGO.setText(CAR);
        PASSWORD.setText(PAS);
    }
}
