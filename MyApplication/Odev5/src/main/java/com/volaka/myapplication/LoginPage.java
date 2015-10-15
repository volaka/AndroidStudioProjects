package com.volaka.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.volaka.myapplication.DatabaseContext;
import com.volaka.myapplication.R;


public class LoginPage extends ActionBarActivity {

    DatabaseContext userDb;
    EditText textUsername,textPassword;
    Button loginButton, registerButton,showallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        userDb = new DatabaseContext(this);
        textPassword = (EditText)findViewById(R.id.editText_password);
        textUsername = (EditText)findViewById(R.id.editText_username);
        loginButton = (Button)findViewById(R.id.button_login);
        registerButton =(Button)findViewById(R.id.button_register);
        showallButton = (Button)findViewById(R.id.button_showall);
        loginClick();
        registerClick();
        ViewAll();
    }

    public void loginClick(){
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = userDb.loginMatch(textUsername.getText().toString(),
                                textPassword.getText().toString());
                        if(res.getCount() > 0) {
                            Intent intent = new Intent(LoginPage.this,UserActivity.class);
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
                            Toast.makeText(LoginPage.this,"User is not registered",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void registerClick() {
        registerButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginPage.this,RegisterActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void ViewAll() {
        showallButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = userDb.getAllData();
                        if (res.getCount() == 0) {
                            //show message
                            showMessage("Error", "No data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n"+"\n");
                        }

                        //show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.show();

    }

}