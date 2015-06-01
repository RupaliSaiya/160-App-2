package com.rau.friendships;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.Blogin){
            EditText a = (EditText)findViewById(R.id.TFusername);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.TFpassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);

            if(pass.equals(password)){
                Intent i = new Intent(MainActivity.this, Welcome.class);
                i.putExtra("Username", str);
                startActivity(i);
            }
            else {
                String error = "Username and Password do NOT match.";
                Toast temp = Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT);
                temp.show();
            }
        } // end if

        if(v.getId() == R.id.Bsignup) {
            Intent i = new Intent(MainActivity.this, Register.class);
            startActivity(i);
        }
    } // end onButtonClick

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

        return super.onOptionsItemSelected(item);
    }
}