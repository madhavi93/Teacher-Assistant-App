package com.example.madhaviruwandika.teacher_assistant.Adapter.Util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemSlideMenu;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Madhavi Ruwandika on 5/1/2016.
 */
public class SpinnerItemAdapter extends ArrayAdapter {

    private Context context;
    private List<TutionClass> tutionClasses;

    public SpinnerItemAdapter(Context context, int textViewResourceId, List<TutionClass> objects) {
        super(context, textViewResourceId, objects);

        /********** Take passed values **********/
        this.context = context;
        this.tutionClasses = objects;


    }

    @Override
    public int getCount() {
        return tutionClasses.size();
    }

    @Override
    public Object getItem(int position) {
        return tutionClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Inflate spinner_rows.xml file for each row ( Defined below )
        View row = View.inflate(context,R.layout.spinner_item, null);
        TextView txt = (TextView) row.findViewById(R.id.ClassName);

        if(position==0){
            txt.setText(" ");
            return null;
        }
        else {
            txt.setText(tutionClasses.get(position).getClassName());
            return row;
        }

    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View row = View.inflate(context,R.layout.spinner_item, null);
        TextView txt = (TextView) row.findViewById(R.id.ClassName);

        Typeface myTypeFace = Typeface.createFromAsset(context.getAssets(),
                "fonts/gilsanslight.otf");

        if(position==0){
            txt.setText(tutionClasses.get(position).getClassName());
            return null;
        }
        else {
            txt.setText(tutionClasses.get(position).getClassName());
            return row;
        }

    }
}
