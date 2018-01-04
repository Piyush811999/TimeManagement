package piyush_makwana.timemangement.Analysis;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import piyush_makwana.timemangement.DatabaseRelated.DatabaseHelper;
import piyush_makwana.timemangement.R;
import piyush_makwana.timemangement.TimeTable.activity;
import piyush_makwana.timemangement.TodayView.dayInfo;
import piyush_makwana.timemangement.Utils.BottomNavigationViewHelper;

/**
 * Created by HP on 25-12-2017.
 */

public class AnalysisActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    PieChart pieChartYesterday;
    PieChart pieChartWeekly;
    PieChart pieChartMonthly;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dailylog);
        setupBottomNavigationView();
        mHelper = new DatabaseHelper(this);
        pieChartYesterday = (PieChart)findViewById(R.id.chartYesterday);
        pieChartYesterday.setRotationEnabled(true);
        pieChartWeekly=(PieChart)findViewById(R.id.chartLastWeek);
        pieChartWeekly.setRotationEnabled(true);
        pieChartMonthly=(PieChart)findViewById(R.id.chartLastMonth);
        pieChartMonthly.setRotationEnabled(true);


        Description data = new Description();
        data.setText("");

        pieChartYesterday.setDescription(data);
        pieChartWeekly.setDescription(data);
        pieChartMonthly.setDescription(data);





        //adding todays data
         float[] yData = {sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,1),sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,1),(sumOFcolumn(dayInfo.dayInfoEntery.COL_freetime,1)-sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,1)-sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,1))};
         String[] xData = {"Productive", "Social" , "Others" };

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
                ArrayList<String> xEntrys = new ArrayList<>();

                       for(int i = 0; i < yData.length; i++){
                       yEntrys.add(new PieEntry(yData[i] , i));
                       }


                       for(int i = 1; i < xData.length; i++){
                     xEntrys.add(xData[i]);
                    }



                        //create the data set
                PieDataSet pieDataSet = new PieDataSet(yEntrys, "Statics");
                pieDataSet.setSliceSpace(2);
                pieDataSet.setValueTextSize(0);

                        //add colors to dataset
                                ArrayList<Integer> colors = new ArrayList<>();
                colors.add(Color.parseColor("#1976d2"));
                colors.add(Color.parseColor("#2E7D32"));
                colors.add(Color.parseColor("#EEEEEE"));


        pieDataSet.setColors(colors);
        Legend legend = pieChartYesterday.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        legend.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);


                      //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChartYesterday.setData(pieData);
        pieChartYesterday.invalidate();


        float[] yData1 = {(float)sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,7),(float)sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,7),(float)(sumOFcolumn(dayInfo.dayInfoEntery.COL_freetime,7)-sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,7)-sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,7))};
        String[] xData1 = {"Productive", "Social" , "Others" };

        ArrayList<PieEntry> yEntrys1 = new ArrayList<>();
        ArrayList<String> xEntrys1 = new ArrayList<>();

        for(int i = 0; i < yData1.length; i++){
            yEntrys1.add(new PieEntry(yData1[i] , i));
        }


        for(int i = 1; i < xData1.length; i++){
            xEntrys1.add(xData1[i]);
        }



        //create the data set
        PieDataSet pieDataSet1 = new PieDataSet(yEntrys1, "Statics");
        pieDataSet1.setSliceSpace(2);
        pieDataSet1.setValueTextSize(0);

        //add colors to dataset

        colors.add(Color.parseColor("#1976d2"));
        colors.add(Color.parseColor("#2E7D32"));
        colors.add(Color.parseColor("#EEEEEE"));


        pieDataSet1.setColors(colors);
        Legend legend1 = pieChartWeekly.getLegend();
        legend1.setForm(Legend.LegendForm.CIRCLE);

        legend1.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);


        //create pie data object
        PieData pieData1 = new PieData(pieDataSet1);
        pieChartWeekly.setData(pieData1);
        pieChartWeekly.invalidate();

        float[] yData2 = {(float)sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,30),(float)sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,30),(float)(sumOFcolumn(dayInfo.dayInfoEntery.COL_freetime,30)-sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,30)-sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,30))};
        String[] xData2 = {"Productive", "Social" , "Others" };

        ArrayList<PieEntry> yEntrys2 = new ArrayList<>();
        ArrayList<String> xEntrys2 = new ArrayList<>();

        for(int i = 0; i < yData2.length; i++){
            yEntrys2.add(new PieEntry(yData2[i] , i));
        }


        for(int i = 1; i < xData2.length; i++){
            xEntrys2.add(xData2[i]);
        }



        //create the data set
        PieDataSet pieDataSet2 = new PieDataSet(yEntrys2, "Statics");
        pieDataSet2.setSliceSpace(2);
        pieDataSet2.setValueTextSize(0);

        //add colors to dataset

        colors.add(Color.parseColor("#1976d2"));
        colors.add(Color.parseColor("#2E7D32"));
        colors.add(Color.parseColor("#EEEEEE"));


        pieDataSet2.setColors(colors);
        Legend legen2 = pieChartMonthly.getLegend();
        legen2.setForm(Legend.LegendForm.CIRCLE);

        legen2.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);


        //create pie data object
        PieData pieData2 = new PieData(pieDataSet2);
        pieChartMonthly.setData(pieData2);
        pieChartMonthly.invalidate();





    }

    private void addDataSet(PieChart chart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntry = new ArrayList<>();


    }

    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.navigation);
        BottomNavigationViewHelper.setBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(AnalysisActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
    }

    private int sumOFcolumn(String col_name,int limit){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + col_name + ") as Total FROM ( select * from "+dayInfo.TABLE_NAME+" order by "+dayInfo.dayInfoEntery._ID+ " desc limit " +String.valueOf(limit) +")",null);

        if (cursor.moveToFirst()) {

            int total = cursor.getInt(cursor.getColumnIndex("Total"));
            cursor.close();
            return total;
        }
        else return 0;

    }
    private String sant(String str) {
        return DatabaseUtils.sqlEscapeString(str);
    }


}
