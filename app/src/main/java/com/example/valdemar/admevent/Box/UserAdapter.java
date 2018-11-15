package com.example.valdemar.admevent.Box;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.valdemar.admevent.R;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private Context CONTEXT;
    private ArrayList<User> LIST;

    public UserAdapter(Context CONTEXT, ArrayList<User> LIST) {
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
            convertView = inflate.inflate(R.layout.list_user_item,null);
        }
        TextView nombr = (TextView)convertView.findViewById(R.id.L_nomb);
        TextView apell = (TextView)convertView.findViewById(R.id.L_apell);
        TextView cedul = (TextView)convertView.findViewById(R.id.L_ci);
        TextView insti = (TextView)convertView.findViewById(R.id.L_ins);

        nombr.setText(this.LIST.get(position).getNOMB());
        apell.setText(this.LIST.get(position).getAPEL());
        cedul.setText(this.LIST.get(position).getCEDU());
        insti.setText(this.LIST.get(position).getINST());
        return convertView;
    }
}
