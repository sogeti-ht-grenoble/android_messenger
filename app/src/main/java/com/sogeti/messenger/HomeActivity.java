package com.sogeti.messenger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements MessageDisplayer {

    private TextView output;
    private EditText input;
    private View button;

    private MessageReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);

        receiver = new MessageReceiver(this);
        registerReceiver(receiver, receiver.getIntentFilter());

        output = (TextView)findViewById(R.id.home_output);

        input = (EditText) findViewById(R.id.home_input);

        button = findViewById(R.id.home_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(input.getText().toString());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void sendMessage(String message) {
        new MessageSender(this, message).send();
    }

    @Override
    public void displayMessage(String sender, String content) {
        final String formattedMessage = getString(R.string.message_format, sender, content);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                output.setText(formattedMessage);
            }
        });
    }
}
