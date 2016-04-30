package com.example.madhaviruwandika.teacher_assistant.Adapter.Util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemSlideMenu;
import com.example.madhaviruwandika.teacher_assistant.R;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class SlidingMenuAdapter extends BaseAdapter {

    private Context context;
    private List<ItemSlideMenu> lstMenu;

    public SlidingMenuAdapter(Context context, List<ItemSlideMenu> lstMenu) {
        this.context = context;
        this.lstMenu = lstMenu;
    }

    @Override
    public int getCount() {
        return lstMenu.size();
    }

    @Override
    public Object getItem(int position) {
        return lstMenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.appdrawer, null);
        TextView txt = (TextView)v.findViewById(R.id.list_item);

        ItemSlideMenu item = lstMenu.get(position);
        txt.setText(item.getTitle());

        return v;
    }
}
