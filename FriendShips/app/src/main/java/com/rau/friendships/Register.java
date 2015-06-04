package com.rau.friendships;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onSignUpClick(View v)
    {
        if(v.getId()== R.id.Bsignupbutton)
        {
            EditText name = (EditText)findViewById(R.id.TFname);
            EditText email = (EditText)findViewById(R.id.TFemail);
            EditText uname = (EditText)findViewById(R.id.TFuname);
            EditText pass1 = (EditText)findViewById(R.id.TFpass1);
            EditText pass2 = (EditText)findViewById(R.id.TFpass2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(!pass1str.equals(pass2str)){

                // show error message
                String error = "Passwords do NOT match.";
                Toast pass = Toast.makeText(Register.this, error, Toast.LENGTH_SHORT);
                pass.show();
            }
            else{

                // insert into database
                User c = new User();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);
                helper.insertContact(c);

                new FetchSQL().execute();

                // direct to welcome screen
                Intent i = new Intent(Register.this, Welcome.class);
                startActivity(i);
            }
        }
    }
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

            EditText tname = (EditText)findViewById(R.id.TFname);
            EditText temail = (EditText)findViewById(R.id.TFemail);
            EditText tuname = (EditText)findViewById(R.id.TFuname);
            EditText tpass1 = (EditText)findViewById(R.id.TFpass1);

            try {
                DriverManager.setLoginTimeout(5);
                conn = DriverManager.getConnection(url);
                Statement st = conn.createStatement();
                String sql;
                sql = "INSERT INTO user_info (name, password, email) VALUES ('"+ tname.getText() +  "','" + tpass1.getText() + "','" + temail.getText() + "');";
                 st.executeQuery(sql);
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                retval = e.toString();
            }
            return retval;
        }

    }
}
