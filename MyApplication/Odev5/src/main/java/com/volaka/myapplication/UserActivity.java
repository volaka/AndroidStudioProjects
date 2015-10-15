package com.volaka.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class UserActivity extends ActionBarActivity {

    String username;
    TextView infoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        infoView = (TextView)findViewById(R.id.textView_info);
        infoView.setText("Hello, " + username);
    }

}