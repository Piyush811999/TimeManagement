package piyush_makwana.timemangement.TimeTable;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import piyush_makwana.timemangement.DatabaseRelated.DatabaseHelper;
import piyush_makwana.timemangement.R;

/**
 * Created by HP on 30-12-2017.
 */

public class PopupAddActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    private EditText activityName;
    private EditText activityVenu;
    private ImageButton cancelButton;
    private ImageButton addButton;
    private TextView timeFrom;
    private TextView timeTo;
    private Bundle extras;
    private String dayName;
    int mhour;
    int mminute;
    int phour;
    int pminute;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_activity_daily);

        extras = getIntent().getExtras();
        if (extras != null) {
            dayName = extras.getString("day1");
        }
        mHelper = new DatabaseHelper(this);

        activityName = (EditText) findViewById(R.id.activityName);
        activityVenu = (EditText) findViewById(R.id.activityVenue);
        addButton = findViewById(R.id.add_activity);
        cancelButton = findViewById(R.id.cancel_activity);
        timeFrom = findViewById(R.id.timeFrom);
        timeTo = findViewById(R.id.timeTo);

        showTimePickerDilaog();




        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mHelper.getWritableDatabase();

                String mActivityName = String.valueOf(activityName.getText());
                String mVenu = String.valueOf(activityVenu.getText());
                int duration = duration(phour,pminute,mhour,mminute);

                if (mActivityName.isEmpty()){
                    Toast.makeText(PopupAddActivity.this, "Enter Activity Name !", Toast.LENGTH_SHORT).show();

                }
                else {
                    if (duration <= 0) {
                        Toast.makeText(PopupAddActivity.this, "Check the time you entered ", Toast.LENGTH_SHORT).show();
                    } else {


                        ContentValues values = new ContentValues();
                        values.put(activity.activityEntery.COL_day_name, dayName);
                        values.put(activity.activityEntery.COL_activity_TITLE, mActivityName);
                        values.put(activity.activityEntery.COL_activity_location, mVenu);
                        values.put(activity.activityEntery.COL_from_hour, mhour);
                        values.put(activity.activityEntery.COL_from_min, mminute);
                        values.put(activity.activityEntery.COL_to_hour, phour);
                        values.put(activity.activityEntery.COL_to_min, pminute);
                        values.put(activity.activityEntery.COL_duration, duration);
                        db.insert(activity.TABLE_NAME, null, values);
                        db.close();
                        finish();
                    }
                }




            }
        });


    }

    public void showTimePickerDilaog() {
        timeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);

            }
        });

        timeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==0)
            return  new TimePickerDialog(PopupAddActivity.this,mTimePickerListener, mhour, mminute,false);
        if(id==1)
            return  new TimePickerDialog(PopupAddActivity.this,pTimePickListener,phour,pminute,false);
        return null;
    }


    //do something so it displays two digits always -----------------------------------------------------------------------------------------

    protected TimePickerDialog.OnTimeSetListener mTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mhour=hourOfDay;
            mminute=minute;
            if (mhour == 0){timeFrom.setText( 12 + ":" + mminute +" AM");}
            else if (mhour<12){
                timeFrom.setText( mhour + ":" + mminute +" AM");}
            else if (mhour == 12){
                timeFrom.setText( mhour + ":" + mminute +" PM");}
            else { timeFrom.setText(mhour-12 + ":" + mminute + " PM");}

        }
    };
    protected TimePickerDialog.OnTimeSetListener pTimePickListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            phour=hourOfDay;
            pminute=minute;
            if (phour == 0){timeTo.setText( 12 + ":" + pminute +" AM");}
            else if (phour<12){
            timeTo.setText( phour + ":" + pminute +" AM");}
            else if (phour == 12){
                timeTo.setText( phour + ":" + pminute +" PM");}
            else { timeTo.setText(phour-12 + ":" + pminute + " PM");}

        }
    };

    public int duration (int time1,int min1,int time2, int min2){

            return ((time1-time2)*60 + (min1-min2));

    }

    public String timeToString (int hours , int minutes ){


            if (hours == 0) {
                return (12 + ":" + minutes + " AM");
            } else if (hours < 12) {
                return (hours + ":" + minutes + " AM");
            } else if (hours == 12) {
                return (hours + ":" + minutes + " PM");
            } else {
                return (hours - 12 + ":" + minutes + " PM");
            }


    }


}
