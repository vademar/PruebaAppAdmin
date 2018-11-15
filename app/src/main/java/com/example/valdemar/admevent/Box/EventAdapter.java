package com.example.valdemar.admevent.Box;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.valdemar.admevent.R;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter{
    private Context CONTEXT;
    private ArrayList<Event>LIST;

    public EventAdapter(Context CONTEXT, ArrayList<Event> LIST) {
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
            convertView = inflate.inflate(R.layout.evento_list_register,null);
        }
        TextView nombre = (TextView)convertView.findViewById(R.id.NOMBRE);
        TextView descri = (TextView)convertView.findViewById(R.id.DESCRIPCION);
        TextView fechaI = (TextView)convertView.findViewById(R.id.FECHAI);
        TextView fechaF = (TextView)convertView.findViewById(R.id.FECHAF);
        TextView horaI = (TextView)convertView.findViewById(R.id.HORAI);
        TextView horaF = (TextView)convertView.findViewById(R.id.HORAF);
        TextView costo = (TextView)convertView.findViewById(R.id.COSTOS);

        nombre.setText(this.LIST.get(position).getNOMB());
        descri.setText(this.LIST.get(position).getDESC());
        fechaI.setText(this.LIST.get(position).getFECI());
        fechaF.setText(this.LIST.get(position).getFECF());
        horaI.setText(this.LIST.get(position).getHORI());
        horaF.setText(this.LIST.get(position).getHORF());
        costo.setText(this.LIST.get(position).getCOST());
        return convertView;
    }
}
