package com.volaka.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends ActionBarActivity {

    EditText usernameText,passwordText,confirmText;
    Button registerButton;

    DatabaseContext userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userDb = new DatabaseContext(this);
        usernameText = (EditText)findViewById(R.id.editText_username);
        passwordText = (EditText)findViewById(R.id.editText_password);
        confirmText = (EditText)findViewById(R.id.editText_confirm);
        registerButton = (Button)findViewById(R.id.button_registerAct);

        registerClick();
    }

    public void registerClick() {
        registerButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(passwordText.getText().toString().equals(confirmText.getText().toString())) {
                            userDb.addUser(usernameText.getText().toString(), passwordText.getText().toString());
                            Intent intent = new Intent(RegisterActivity.this, UserActivity.class);
                            intent.putExtra("username",usernameText.getText().toString());
                            startActivity(intent);
                        }
                    }
                }
        );
    }
}