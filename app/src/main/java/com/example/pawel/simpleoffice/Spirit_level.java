package com.example.pawel.simpleoffice;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Spirit_level extends AppCompatActivity implements SensorEventListener{

    Context context = this;
    MediaPlayer mediaPlayer;
    private TextView level;
    private TextView level1;
    private SensorManager sensorManager;
    private Sensor accelerometrSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spirit_level);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mediaPlayer = MediaPlayer.create(context, R.raw.piano);

        level = (TextView) findViewById(R.id.Level);
        level1 = (TextView) findViewById(R.id.Level1);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometrSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometrSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        level.setText("" + event.values[0]);
        level1.setText("" + event.values[1]);

        if(event.values[0]>-0.1 && event.values[0]<0.1) {
            level.setTextColor(Color.GREEN);
            level1.setTextColor(Color.RED);
            level.setText("" + event.values[0]);
            level1.setText("" + event.values[1]);
            if (!mediaPlayer.isPlaying())
                mediaPlayer.start();
        }
        else if(event.values[1]>-0.1 && event.values[1]<0.1) {
            level1.setTextColor(Color.GREEN);
            level.setTextColor(Color.RED);
            level.setText("" + event.values[0]);
            level1.setText("" + event.values[1]);
            if (!mediaPlayer.isPlaying())
                mediaPlayer.start();
        }
        else {
            level1.setTextColor(Color.RED);
            level.setTextColor(Color.RED);
            level.setText("" + event.values[0]);
            level1.setText("" + event.values[1]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
