package com.volaka.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.volaka.myapplication.R;


public class RegisterActivity extends AppCompatActivity {

    //Variables
    Button registerButton, backButton;
    EditText usernameEditText, passwordEditText;
    DatabaseContext userDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //creates database object
        userDb = new DatabaseContext(getBaseContext());
        /* binding ui objects to java objects */
        registerButton = (Button)findViewById(R.id.button_register_register);
        backButton = (Button)findViewById(R.id.button_register_back);
        usernameEditText = (EditText)findViewById(R.id.editText_register_username);
        passwordEditText = (EditText)findViewById(R.id.editText_register_password);
        /* calling methods onCreate to activate*/
        registerClick();
        backClick();
    }

    /*
    setting button click listener.
    calls add user method to update database
    sends username to the new activity using bundle
    starts new activity
     */
    public void registerClick() {
        registerButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userDb.addUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                        Intent intent = new Intent(getBaseContext(), UserProfile.class);
                        intent.putExtra("username", usernameEditText.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }

    /*
    go back to login activity
     */
    public void backClick() {
        backButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }

}