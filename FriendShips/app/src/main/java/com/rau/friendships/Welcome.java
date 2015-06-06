package com.rau.friendships;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Welcome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.menu_add_friend) {
            goToAddFriend();
            return true;
        }else if (id == R.id.menu_create_delivery) {
            goToCreateDeliveries();
            return true;
        }else if(id == R.id.menu_view_deliveries){
            goToViewDeliveries();
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
    private void goToAddFriend(){
        Intent intent = new Intent(this, AddFriend.class);
        startActivity(intent);
    }

    private void goToViewDeliveries(){
        Intent intent = new Intent(this, ViewDeliveries.class);
        startActivity(intent);
    }

    private void goToCreateDeliveries(){
        Intent intent = new Intent(this, CreateDelivery.class);
        startActivity(intent);
    }

    private void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**********************************
     |      button click actions      |
     **********************************/
    public void onButtonClick(View v){
        // create delivery
        if (v.getId() == R.id.BTcreatedelivery){
            Intent i = new Intent(Welcome.this, CreateDelivery.class);
            startActivity(i);

        }

        // add friend
        else if (v.getId() == R.id.BTaddfriend){
            Intent i = new Intent(Welcome.this, AddFriend.class);
            startActivity(i);
        }

        // logout
        else if (v.getId() == R.id.BTlogout){
            Intent i = new Intent(Welcome.this, MainActivity.class);
            startActivity(i);
            finish();

        }

        // view deliveries
        else if (v.getId() == R.id.BTviewDeliveries){
            Intent i = new Intent(Welcome.this, ViewDeliveries.class);
            startActivity(i);
        }

        // something went wrong
        else{
            String error = "Not a valid selection.";
            Toast output = Toast.makeText(Welcome.this, error, Toast.LENGTH_SHORT);
            output.show();
        }

    }
}
