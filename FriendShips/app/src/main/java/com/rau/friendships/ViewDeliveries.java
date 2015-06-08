package com.rau.friendships;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ViewDeliveries extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_deliveries);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_deliveries, menu);
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
        }else if (id == R.id.menu_create_delivery) {
            goToCreateDeliveries();
            return true;
        }else if(id == R.id.menu_add_friend){
            goToAddFried();
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

    private void goToAddFried(){
        Intent intent = new Intent(this, AddFriend.class);
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




    
}
