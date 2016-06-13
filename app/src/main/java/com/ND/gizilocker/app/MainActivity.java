package com.ND.gizilocker.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import java.util.logging.Logger;


public class MainActivity extends ActionBarActivity {
    EditText username_input, password_input;
    TextView result_request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Logger log = Logger.getLogger(String.valueOf(MainActivity.class));
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_main);
        Button login_btn = (Button) findViewById(R.id.ck_btn);
        username_input = (EditText) findViewById(R.id.username_input);
        password_input = (EditText) findViewById(R.id.password_input);

        result_request = (TextView) findViewById(R.id.result_request);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                result_request.setText("My Application Created");
                String url = "http://192.168.1.213:3000/api/v1/sessions";
                Connection con = new Connection();
                con.execute(url);
                log.warning(String.valueOf(con));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
