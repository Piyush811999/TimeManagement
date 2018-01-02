package piyush_makwana.timemangement.TodayView;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.Calendar;
import java.util.Date;

import piyush_makwana.timemangement.DatabaseRelated.DatabaseHelper;
import piyush_makwana.timemangement.R;
import piyush_makwana.timemangement.TimeTable.activity;
import piyush_makwana.timemangement.Utils.BottomNavigationViewHelper;

/**
 * Created by HP on 25-12-2017.
 */

public class TodayActivity extends AppCompatActivity {
    private TextView day;
    private TextView date;
    private DatabaseHelper mHelper;
    private TextView productiveHours;
    private TextView socialHours;
    private ImageButton plusProductive;
    private ImageButton minusProductive;
    private ImageButton plusSocial;
    private ImageButton minusSocial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity);
        setupBottomNavigationView();

        productiveHours = findViewById(R.id.productiveTimeView);
        socialHours = findViewById(R.id.socialTimeView);
        plusProductive = findViewById(R.id.plusProductiveTime);
        minusProductive = findViewById(R.id.minusProuctiveTime);
        plusSocial = findViewById(R.id.plusSocialTime);
        minusSocial = findViewById(R.id.minusSocialTime);


        date = findViewById(R.id.date);
        day = findViewById(R.id.dayView);
        mHelper = new DatabaseHelper(this);

        Calendar calendar = Calendar.getInstance();
        int day1 = calendar.get(Calendar.DAY_OF_WEEK);
        int dateNumber = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        final String formatedDate = dateFormat.format(new Date(java.lang.System.currentTimeMillis()).getTime());

        date.setText(formatedDate);
        day.setText(dayName(day1));

        if (!CheckIfDataAlreadyInDBorNot(dayInfo.TABLE_NAME,dayInfo.dayInfoEntery.COL_date,formatedDate)){
            SQLiteDatabase db = mHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(dayInfo.dayInfoEntery.COL_date,formatedDate);
            values.put(dayInfo.dayInfoEntery.COL_freetime,(1440-BusyHours(dayName(day1))));
            values.put(dayInfo.dayInfoEntery.COL_productiveTime,0);
            values.put(dayInfo.dayInfoEntery.COL_socialTime,0);
            db.insert(dayInfo.TABLE_NAME,null,values);
            productiveHours.setText(0);
            socialHours.setText(0);
        }

        plusProductive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mHelper.getReadableDatabase();
                Cursor cursor = db.query(dayInfo.TABLE_NAME,new String[]{dayInfo.dayInfoEntery.COL_productiveTime},dayInfo.dayInfoEntery.COL_date+"="+formatedDate,null,null,null,null);
                while(cursor.moveToNext()){
                    int index = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_productiveTime);

                    int minute = cursor.getInt(index);
                    cursor.close();

                    int newMinute = minute + 15 ;
                    double hours = newMinute/60;

                    SQLiteDatabase db1 = mHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(dayInfo.dayInfoEntery.COL_productiveTime,newMinute);
                    db1.update(dayInfo.TABLE_NAME,values,dayInfo.dayInfoEntery.COL_date+"="+formatedDate,null);
                    productiveHours.setText(String.valueOf(hours));
                }


            }
        });


    }


    private void setupBottomNavigationView() {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.navigation);
        BottomNavigationViewHelper.setBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(TodayActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }

    private String dayName(int dayInt) {
        switch (dayInt) {

            case Calendar.SUNDAY:
                return "Sunday";


            case Calendar.MONDAY:
                return "Monday";


            case Calendar.TUESDAY:
                return "Tuesday";


            case Calendar.WEDNESDAY:
                return "Wednesday";

            case Calendar.THURSDAY:
                return "Thursday";


            case Calendar.FRIDAY:
                return "Friday";


            case Calendar.SATURDAY:
                return "Saturday";


            default:return "error";

        }
    }

    public  boolean CheckIfDataAlreadyInDBorNot(String TableName,
                                                      String dbfield, String fieldValue) {
        SQLiteDatabase sqldb = mHelper.getReadableDatabase();
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public int BusyHours(String thisday) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + activity.activityEntery.COL_duration + ") as Total FROM " + activity.TABLE_NAME + " WHERE "+ activity.activityEntery.COL_day_name + " = "+ thisday, null);

        if (cursor.moveToFirst()) {

            int total = cursor.getInt(cursor.getColumnIndex("Total"));
            cursor.close();
            return total;
        }
        else return 0;




    }


}

