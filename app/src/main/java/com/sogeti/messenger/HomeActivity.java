package com.sogeti.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private EditText input;
    private View button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);

        input = (EditText) findViewById(R.id.home_input);

        button = findViewById(R.id.home_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(input.getText().toString());
            }
        });

    }


    private void sendMessage(String message) {
        new MessageSender(this, message).send();
    }

}
