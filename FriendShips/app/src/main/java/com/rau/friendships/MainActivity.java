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
                Toast output = Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT);
                output.show();
            }
        } // end if

        // send to Registration screen
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
        return super.onOptionsItemSelected(item);
    }
}