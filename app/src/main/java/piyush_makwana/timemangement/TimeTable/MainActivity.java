package piyush_makwana.timemangement.TimeTable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import piyush_makwana.timemangement.R;
import piyush_makwana.timemangement.Utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DayItems> dayItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toptimetable);
        setSupportActionBar(toolbar);

        setupBottomNavigationView();



        recyclerView = findViewById(R.id.listDays);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        dayItems = new ArrayList<>();

        dayItems.add(new  DayItems("Monday","1","2"));
        dayItems.add(new DayItems("Tuesday",null,null));
        dayItems.add(new DayItems("Wednesday",null,null));
        dayItems.add(new DayItems("Thursday",null,null));
        dayItems.add(new DayItems("Friday",null,null));
        dayItems.add(new DayItems("Saturday",null,null));
        dayItems.add(new DayItems("Sunday",null,null));

        adapter = new RecyclerAdapterDaysTimeTable(this,dayItems);
        recyclerView.setAdapter(adapter);






    }

    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.navigation);
        BottomNavigationViewHelper.setBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(MainActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


    }



}
