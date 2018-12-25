package com.example.valdemar.admevent.Box;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.valdemar.admevent.R;

import java.util.ArrayList;

public class Admidapter extends BaseAdapter {
    private Context CONTEXT;
    private ArrayList<Admi> LIST;

    public Admidapter(Context CONTEXT, ArrayList<Admi> LIST) {
        this.CONTEXT = CONTEXT;
        this.LIST = LIST;
    }

    @Override
    public int getCount() {
        return this.LIST.size();
    }

    @Override
    public Object getItem(int position) {
        return this.LIST.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflate =(LayoutInflater) this.CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.list_admin_item,null);
        }
        TextView nombre = (TextView)convertView.findViewById(R.id.lisnombre);
        TextView precio = (TextView)convertView.findViewById(R.id.lispss);

        nombre.setText(this.LIST.get(position).getNOMBRE());
        precio.setText(this.LIST.get(position).getCONTRASEÑA());
        return convertView;
    }
}
