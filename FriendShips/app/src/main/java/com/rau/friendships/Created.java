package com.rau.friendships;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Created extends ActionBarActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    private String titleSTR = "";
    private String recipientSTR = "";
    private String dateSTR = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created);

        // put date into text view
        String date = getIntent().getStringExtra("Date");
        TextView dateTV = (TextView)findViewById(R.id.TVshowDate);
        dateTV.setText(date);
        dateSTR = date;

        // put time into text view
        String time = getIntent().getStringExtra("Time");
        TextView timeTV = (TextView)findViewById(R.id.TVshowTime);
        timeTV.setText(time);

        // put title into text view
        String title = getIntent().getStringExtra("Title");
        TextView titleTV = (TextView)findViewById(R.id.TVshowTitle);
        titleTV.setText(title);
        titleSTR = title;

        // put recipient into text view
        String recipient = getIntent().getStringExtra("Recipient");
        TextView recipientTV = (TextView)findViewById(R.id.TVshowRecipient);
        recipientTV.setText(recipient);
        recipientSTR = recipient;

        // put location into text view
        String location = getIntent().getStringExtra("Location");
        TextView locationTV = (TextView)findViewById(R.id.TVshowLocation);
        locationTV.setText(location);

        // put additional info into text view
        String addInfo = getIntent().getStringExtra("AddInfo");
        TextView addInfoTV = (TextView)findViewById(R.id.TVshowAddinfo);
        addInfoTV.setText(addInfo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_created, menu);
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
        }else if (id == R.id.menu_create_delivery) {
            goToCreateDeliveries();
            return true;
        }
        return super.onOptionsItemSelected(item);

    } // end onOptionsItemSelected

    /**********************************
     |   methods for menu actions     |
     **********************************/
    private void goToWelcome(){
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }

    private void goToCreateDeliveries(){
        Intent intent = new Intent(this, CreateDelivery.class);
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

    public void onButtonClick(View v){
        if (v.getId() == R.id.BTcreateAnother){
            Intent i = new Intent(Created.this, CreateDelivery.class);
            startActivity(i);

        } else if (v.getId() == R.id.BTviewDeliveries){
            // add strings into intent and pass to view deliveries
            Intent vd = new Intent(Created.this, ViewDeliveries.class);
            vd.putExtra("Title", titleSTR);
            vd.putExtra("Recipient", recipientSTR);
            vd.putExtra("Date", dateSTR);
            startActivity(vd);
        }
    }

} // end of Created class