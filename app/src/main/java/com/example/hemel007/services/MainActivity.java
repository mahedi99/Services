package com.example.hemel007.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button startBtn;
    private Button stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        startBtn= (Button) findViewById(R.id.startBtn);
        stopBtn= (Button) findViewById(R.id.stopBtn);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent service = new Intent(MainActivity.this, ForegroundService.class);
        if (view==startBtn && !ForegroundService.IS_SERVICE_RUNNING)
        {
            service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
            ForegroundService.IS_SERVICE_RUNNING = true;
            //button.setText("Stop Service");
            startService(service);
        }
        if (view==stopBtn && ForegroundService.IS_SERVICE_RUNNING)
        {
            service.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
            ForegroundService.IS_SERVICE_RUNNING = false;
            //button.setText("Start Service");
            startService(service);
        }

    }
}
