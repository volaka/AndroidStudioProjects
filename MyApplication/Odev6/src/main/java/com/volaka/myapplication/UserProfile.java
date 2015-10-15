package com.volaka.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.volaka.myapplication.R;


public class UserProfile extends AppCompatActivity {

    //Variables
    Button startExperimentButton, logoutButton, exitButton;
    TextView usernameTextView;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        /* binding ui objects to java objects */
        startExperimentButton = (Button)findViewById(R.id.button_user_experiment);
        logoutButton = (Button)findViewById(R.id.button_user_logout);
        exitButton = (Button)findViewById(R.id.button_user_exit);
        usernameTextView = (TextView)findViewById(R.id.textView_user_username);
        /* Gets sent username and sets TextView with that string*/
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        usernameTextView.setText(username);
        /* calling methods onCreate to activate*/
        exitClick();
        logoutClick();
        experimentClick();
    }

    /*
    setting button click listener.
    finishes all open activities in
    that application.
     */
    public void exitClick() {
        exitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishAffinity(); //Finishes all
                    }
                }
        );
    }

    /*
    setting button click listener.
    closes activity and goes back to login page.
     */
    public void logoutClick() {
        logoutButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }

    /*
    setting button click listener.
    opens experiment activity
    doesn't close this activity
     */
    public void experimentClick() {
        startExperimentButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(),ExperimentActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

}