package com.example.madhaviruwandika.teacher_assistant.Activity.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;

import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemSlideMenu;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 4/26/2016.
 */
public class BaseActivity extends AppCompatActivity  {

    private DrawerLayout drawerLayout ;
    private ActionBarDrawerToggle drawerToggle;
    private ListView newList;
    private List<ItemSlideMenu> list;
    private ArrayAdapter<ItemSlideMenu> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_Layout);
        newList = (ListView)findViewById(R.id.drawer_list);
        list = new ArrayList<ItemSlideMenu>();


        list.add(new ItemSlideMenu("  Start class"));
        list.add(new ItemSlideMenu("  Syllabus tracker"));
        list.add(new ItemSlideMenu("  Student performance"));
        list.add(new ItemSlideMenu("  Send message"));
        list.add(new ItemSlideMenu("  Manage class"));

        adapter = new ArrayAdapter(this,R.layout.appdrawer,list);



        newList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        newList.setAdapter(adapter);

        newList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.TodaysClassActivity");
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity.MyProgressActivity");
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity.StudentProgressActivity");
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity.SendMessageActivity");
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity");
                        startActivity(intent);
                        break;
                    default:
                        intent = new Intent(".Activity.HomeActivity");
                        startActivity(intent);
                        break;

                }

            }

            @SuppressWarnings("unused")
            public void onClick(View v) {
            }

            ;
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Drawer_open ,R.string.Drawer_close){

            @Override
            public void syncState() {
                super.syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
               // getActionBar().setTitle(getTitle());
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
              //  getActionBar().setTitle(getTitle());
                invalidateOptionsMenu();
            }


        };


        drawerLayout.setDrawerListener(drawerToggle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == android.R.id.home){
            if(drawerLayout.isDrawerOpen(newList)){
                drawerLayout.closeDrawer(newList);
            }
            else {
                drawerLayout.openDrawer(newList);
            }
        }

        return super.onOptionsItemSelected(item);
    }


}





