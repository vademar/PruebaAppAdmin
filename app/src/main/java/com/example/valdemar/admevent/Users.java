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

import com.example.valdemar.admevent.Box.Profe;
import com.example.valdemar.admevent.Box.ProfeAdapter;
import com.example.valdemar.admevent.Box.User;
import com.example.valdemar.admevent.Box.UserAdapter;
import com.example.valdemar.admevent.Hosting.Host;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    private String  UsID,nombre, apellido, ci, profesion, institucion, cargo, password;
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
                        String idu = obj.optString("_id");
                        String nom = obj.optString("nombre");
                        String ape = obj.optString("apellido");
                        String pro= obj.optString("profesion");
                        String ins = obj.optString("institucion");
                        String car = obj.optString("cargo");
                        String cii = obj.optString("ci");
                        String pas = obj.optString("password");
                        User USer = new User(idu,nom,ape,pro,ins,car,cii,pas);
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

    public void UpdateUser(View view){
        nombre = NOMBRE.getText().toString();
        apellido = APELLIDO.getText().toString();
        ci = CI.getText().toString();
        profesion = PROFESION.getText().toString();
        institucion = INSTITUCION.getText().toString();
        cargo = CARGO.getText().toString();
        password = PASSWORD.getText().toString();
        if (nombre.length()== 0) {
            Toast.makeText(this,"Empty Name", Toast.LENGTH_SHORT).show();
        }
        if (apellido.length()== 0) {
            Toast.makeText(this,"Empty Surname", Toast.LENGTH_SHORT).show();
        }
        if (ci.length()== 0) {
            Toast.makeText(this,"Empty Cedula", Toast.LENGTH_SHORT).show();
        }
        if (password.length()== 0) {
            Toast.makeText(this,"Empty Password", Toast.LENGTH_SHORT).show();
        }
        if (institucion.length()== 0) {
            Toast.makeText(this,"Empty Institución", Toast.LENGTH_SHORT).show();
        }
        if (profesion.length()== 0) {
            Toast.makeText(this,"Empty profesion", Toast.LENGTH_SHORT).show();
        }
        if (cargo.length()== 0) {
            Toast.makeText(this,"Empty cargo", Toast.LENGTH_SHORT).show();
        }

        if(nombre.length()!=0 && apellido.length()!=0 && ci.length()!=0 && password.length()!=0 && institucion.length()!=0 && profesion.length()!=0&& cargo.length()!=0){
            if(nombre.matches("[aA-zZ,ñÑ]*")&&apellido.matches("[aA-zZ,ñÑ]*")){
                AsyncHttpClient UpdateUser = new AsyncHttpClient();
                RequestParams param = new RequestParams();
                param.put("nombre",nombre);
                param.put("apellido",apellido);
                param.put("profesion", profesion);
                param.put("institucion", institucion);
                param.put("cargo",cargo);
                param.put("ci",ci);
                param.put("password",password);
                UpdateUser.put(Host.Rest_Registro+UsID, param, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String msn = response.getString("msn");
                            Intent inte = new Intent(root,Users.class);
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

    public void DeleteUser(View view){
        nombre = NOMBRE.getText().toString();
        apellido = APELLIDO.getText().toString();
        ci = CI.getText().toString();
        profesion = PROFESION.getText().toString();
        institucion = INSTITUCION.getText().toString();
        cargo = CARGO.getText().toString();
        password = PASSWORD.getText().toString();
        if (nombre.length()== 0) {
            Toast.makeText(this,"Empty Name", Toast.LENGTH_SHORT).show();
        }
        if (apellido.length()== 0) {
            Toast.makeText(this,"Empty Surname", Toast.LENGTH_SHORT).show();
        }
        if (ci.length()== 0) {
            Toast.makeText(this,"Empty Cedula", Toast.LENGTH_SHORT).show();
        }
        if (password.length()== 0) {
            Toast.makeText(this,"Empty Password", Toast.LENGTH_SHORT).show();
        }
        if (institucion.length()== 0) {
            Toast.makeText(this,"Empty Institución", Toast.LENGTH_SHORT).show();
        }
        if (profesion.length()== 0) {
            Toast.makeText(this,"Empty profesion", Toast.LENGTH_SHORT).show();
        }
        if (cargo.length()== 0) {
            Toast.makeText(this,"Empty cargo", Toast.LENGTH_SHORT).show();
        }

        if(nombre.length()!=0 && apellido.length()!=0 && ci.length()!=0 && password.length()!=0 && institucion.length()!=0 && profesion.length()!=0&& cargo.length()!=0){
            if(nombre.matches("[aA-zZ,ñÑ]*")&&apellido.matches("[aA-zZ,ñÑ]*")){
                AsyncHttpClient DeleteUser = new AsyncHttpClient();
                RequestParams param = new RequestParams();
                DeleteUser.delete(Host.Rest_Registro+ci, param, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String msn = response.getString("msn");
                            Intent inte = new Intent(root,Users.class);
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
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UsID = this.LISTINFO.get(position).getUSID();
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
