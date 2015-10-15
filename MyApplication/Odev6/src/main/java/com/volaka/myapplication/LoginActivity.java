package com.volaka.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.volaka.myapplication.R;


public class LoginActivity extends AppCompatActivity {

    /* Variables */
    DatabaseContext userDb;
    Button loginButton, registerButton;
    EditText usernameEditText, passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDb = new DatabaseContext(this); //created database object
        /* binding ui objects to java objects */
        loginButton = (Button)findViewById(R.id.button_login_login);
        registerButton = (Button)findViewById(R.id.button_login_register);
        usernameEditText = (EditText)findViewById(R.id.editText_login_username);
        passwordEditText = (EditText)findViewById(R.id.editText_login_password);
        /* calling methods onCreate to activate*/
        registerClick();
        loginClick();
    }

    /*
    setting button click listener.
    creating register activity intent and
    starting the activity
     */
    public void registerClick() {
        registerButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
    /*
    setting button click listener
    checking if user exists in database
    if exists and password matches, starts user activity
    getting username from cursor object and sending it
    with intent to the new activity
    if not exists or if password is wrong, toasts a message
     */
    public void loginClick(){
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = userDb.loginMatch(usernameEditText.getText().toString(),
                                passwordEditText.getText().toString());
                        if(res.getCount() > 0) {
                            Intent intent = new Intent(getBaseContext(), UserProfile.class);
                            try {
                                res.moveToNext();
                                intent.putExtra("username", res.getString(1));
                                startActivity(intent);
                            }
                            catch (Exception ex) {
                                ex.getMessage();
                            }
                        }
                        else {
                            Toast.makeText(getBaseContext(),"User is not registered", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    /*
    if activity resumes,
    sets EditableTextAreas to empty strings
    to delete traces
     */
    @Override
    protected void onResume() {
        super.onResume();

        passwordEditText.setText("");
        usernameEditText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

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