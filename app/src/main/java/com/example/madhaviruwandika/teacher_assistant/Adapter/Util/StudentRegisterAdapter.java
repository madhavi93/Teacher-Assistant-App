package com.example.madhaviruwandika.teacher_assistant.Adapter.Util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemRegisterName;
import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemSlideMenu;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 5/1/2016.
 */
public class StudentRegisterAdapter extends BaseAdapter {

    private Context context;
    private List<ItemRegisterName> student;

    public StudentRegisterAdapter(Context context, List<ItemRegisterName> student) {
        this.context = context;
        this.student = student;
    }

    @Override
    public int getCount() {
        return student.size();
    }

    @Override
    public Object getItem(int position) {
        return student.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.register_student_name, null);

        TextView txt = (TextView)v.findViewById(R.id.name);
        CheckBox checkBox = (CheckBox) v.findViewById(R.id.attendence);

        ItemRegisterName item = student.get(position);
        txt.setText(item.getName());

        if(student.get(position).getAttendence() )
            checkBox.setChecked(true);
        else
            checkBox.setChecked(false);

        return v;

    }

}
