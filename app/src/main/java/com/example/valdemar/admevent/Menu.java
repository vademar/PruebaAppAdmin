package com.example.valdemar.admevent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button USER, EVENT, PROFESS, ADMIN, MYQR;
    private Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = this;
        setContentView(R.layout.activity_menu);
        MYQR =  (Button)findViewById(R.id.MYQR);
        USER = (Button)findViewById(R.id.USER);
        ADMIN = (Button)findViewById(R.id.ADMIN);
        EVENT = (Button)findViewById(R.id.EVENT);
        PROFESS = (Button)findViewById(R.id.PROFESS);

        MYQR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent user = new Intent(root,ListEvent.class);
                root.startActivity(user);
            }
        });
        USER.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent user = new Intent(root,Users.class);
                root.startActivity(user);            }
        });

        EVENT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent user = new Intent(root,Event.class);
                root.startActivity(user);
            }
        });


        ADMIN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent user = new Intent(root,Admins.class);
                root.startActivity(user);
            }
        });

        PROFESS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent user = new Intent(root,Prof.class);
                root.startActivity(user);
            }
        });
    }
}
