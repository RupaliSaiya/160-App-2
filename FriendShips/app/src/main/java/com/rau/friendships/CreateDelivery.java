package com.rau.friendships;

import android.os.AsyncTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// this class needs to be abstract
public class CreateDelivery extends ActionBarActivity
        implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {

    // global variables
    private int pickerHour = 0;
    private int pickerMin = 0;
    private int pickerMonth = 0;
    private int pickerDay= 0;
    private int pickerYear = 0;
    private String titleSTR = "";
    private String recipientSTR = "";
    private String locationSTR = "";
    private String addinfoSTR = "";
    private String dateSTR = "";
    private String timeSTR = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_delivery);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_delivery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.menu_welcome) {
            goToWelcome();
            return true;
        }else if (id == R.id.menu_view_deliveries) {
            goToViewDeliveries();
            return true;
        }else if(id == R.id.menu_add_friend){
            goToAddFriends();
            return true;
        }else if(id == R.id.menu_logout){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**********************************
     |   methods for menu actions     |
     **********************************/
    private void goToWelcome(){
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }

    private void goToViewDeliveries(){
        Intent intent = new Intent(this, ViewDeliveries.class);
        startActivity(intent);
    }

    private void goToAddFriends(){
        Intent intent = new Intent(this, AddFriend.class);
        startActivity(intent);
    }

    private void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    /**********************************
     |   Set and show Time selected   |
     **********************************/
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        pickerHour = hourOfDay;
        pickerMin = minute;
        String normalizedMinute;
        String time;

        if (pickerMin < 10){
            normalizedMinute = "0" + pickerMin;
            time = pickerHour + ":" + normalizedMinute;
        }
        else {
            time = pickerHour + ":" + pickerMin;
        }

        TextView timeTV = (TextView)findViewById(R.id.TVtime);
        timeTV.setText(time);

    }

    /**********************************
     |   Set and show Date selected   |
     **********************************/
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day){
        pickerYear = year;
        pickerMonth = month;
        pickerDay = day;
        String date = pickerMonth + "/" + pickerDay + "/" + pickerYear;
        TextView dateTV = (TextView)findViewById(R.id.TVdate);
        dateTV.setText(date);

    }

    /**********************************
     |   Create the delivery in db    |
     **********************************/
    public void onCreateDelivery(View v){

        // switch to created page
        if (v.getId() == R.id.BTcreate){

            // get string vars
            EditText title = (EditText)findViewById(R.id.ETdeliveryTitle);
            EditText recipient = (EditText)findViewById(R.id.ETrecipient);
            EditText location = (EditText)findViewById(R.id.ETlocation);
            EditText addinfo = (EditText)findViewById(R.id.ETaddinfo);

            // convert them to strings
            titleSTR = title.getText().toString();
            recipientSTR = recipient.getText().toString();
            locationSTR = location.getText().toString();
            addinfoSTR = addinfo.getText().toString();

            // build date the date string
            dateSTR = pickerMonth + "/" + pickerDay + "/" + pickerYear;

            // build the time string
            // normalize the minute in time
            String normalizedMinute;
            if (pickerMin < 10){
                normalizedMinute = "0" + pickerMin;
                timeSTR = pickerHour + ":" + normalizedMinute;
            }
            else {
                timeSTR = pickerHour + ":" + pickerMin;
            }

            // insert new delivery into database
            new FetchSQL().execute();

            // add strings into intent to pass to created activity
            Intent cd = new Intent(CreateDelivery.this, Created.class);
            cd.putExtra("Title", titleSTR);
            cd.putExtra("Recipient", recipientSTR);
            cd.putExtra("Location", locationSTR);
            cd.putExtra("AddInfo", addinfoSTR);
            cd.putExtra("Date", dateSTR);
            cd.putExtra("Time", timeSTR);
            startActivity(cd);


        } // end if statement
    } // end onCreateDelivery

    private class FetchSQL extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params) {
            String retval = "";
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                retval = e.toString();
            }
            String url = "jdbc:postgresql://panguin.chickenkiller.com:34567/postgres?user=postgres&password=helloworld!";
            Connection conn;

            try {
                DriverManager.setLoginTimeout(5);
                conn = DriverManager.getConnection(url);
                Statement st = conn.createStatement();
                String sql;
                sql = "INSERT INTO delivery (title, recipient, location, aditional_info, date, time) " +
                        "VALUES ('"+ titleSTR + "','"
                        + recipientSTR + "','"
                        + locationSTR + "','"
                        + addinfoSTR + "','"
                        + dateSTR + "','"
                        + timeSTR + "');";
                st.executeQuery(sql);
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                retval = e.toString();

            } // end of try-catch

            return retval;

        } // end of doInBackground
    } // end of FetchSQL

} // end of CreateDelivery class


