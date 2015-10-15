package com.volaka.myapplication;

        import android.os.Handler;
        import android.os.Looper;
        import android.os.Message;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;


public class ExperimentActivity extends AppCompatActivity {

    /*
    Variables
     */
    Button stopButton, backButton;
    TextView randomNumberTextView;

    private final Handler myHandler = new Handler();
    private String randStr;
    private double rand;
    private boolean _stop = false; //to stop thread runner
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        //ui objects and java objects binding
        stopButton = (Button)findViewById(R.id.button_experiment_stop);
        backButton = (Button)findViewById(R.id.button_experiment_back);
        randomNumberTextView = (TextView)findViewById(R.id.textView_experiment_randomNumber);

        /*
        this thread starts immediately when activity starts,
        it calls randomNumberGenerate method to calculate a
        random and shows it on ui
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(!_stop) {
                        randomNumberGenerate();
                    }
                }
                catch (Exception ex){
                    ex.getMessage();
                }

            }
        }).start();

        stopClick();
        backClick();
    }

    /*
    This runnable is to update UI
    It will be used with a handler
     */
    final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            updateUI();
        }
    };
    /*
    this method is to update ui object
     */
    private void updateUI() {
        randomNumberTextView.setText(randStr);
    }
    /*
    this method calculates a random, converts it to string
    and uses the handler to be able to post it on ui.
    then waits for a sec.(this can be modified and can be changed
    to 0 )
     */
    private void randomNumberGenerate(){

        rand = Math.random()*1000;
        randStr = String.valueOf(rand);
        myHandler.post(updateRunnable);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    when user presses stop button, _stop variable is set to true
    and while loop in onCreate thread exists.
    after pressing this button a back button appears to go back
    to user page
     */
    public void stopClick() {
        stopButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //stop thread
                        _stop = true;
                        backButton.setVisibility(View.VISIBLE);
                    }
                }
        );
    }
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

    /*
    with this override, back button is disabled,
    the only possible to go back is to press stop button
    then back button
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }


}