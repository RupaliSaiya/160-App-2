package com.rau.friendships;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ViewDeliveries extends ActionBarActivity {

    public ArrayList<Item> results = new ArrayList<Item>();
    public ArrayList deets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_deliveries);


        // create list for deliveries
        deets = getListData();

        final ListView lv1 = (ListView)findViewById(R.id.custom_list);
        lv1.setAdapter(new ItemAdapter(this, deets));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Item itemData = (Item) o;
                Toast.makeText(ViewDeliveries.this, "Selected :" + " " + itemData, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_deliveries, menu);
        return true;
    } // end onCreate

    private ArrayList getListData(){

        String date = getIntent().getStringExtra("Date");
        String title = getIntent().getStringExtra("Title");
        String recipient = getIntent().getStringExtra("Recipient");

        Item itemData = new Item();

        itemData.setDate(date);
        itemData.setTitle(title);
        itemData.setRecipient(recipient);

        results.add(itemData);

        return results;

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
