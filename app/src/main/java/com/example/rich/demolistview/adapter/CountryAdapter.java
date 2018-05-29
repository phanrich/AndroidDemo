package com.example.rich.demolistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rich.demolistview.R;
import com.example.rich.demolistview.model.Country;

import java.util.ArrayList;

public class CountryAdapter extends BaseAdapter {
    Context context;
    ArrayList<Country> list;
    LayoutInflater inflater;

    public CountryAdapter(Context context, ArrayList<Country> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    public void addListItemAdapter(ArrayList<Country> itemPlus){
        list.addAll(itemPlus);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Country item = list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_row_item_country, parent, false);
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.imgFlag = convertView.findViewById(R.id.imgFlag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtName.setText(item.getName());
        holder.imgFlag.setImageResource(item.getImgFlag());
        return convertView;
    }

    public static class ViewHolder {
        TextView txtName;
        ImageView imgFlag;
    }
}
