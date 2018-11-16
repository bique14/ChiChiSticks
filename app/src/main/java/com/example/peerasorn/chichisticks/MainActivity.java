package com.example.peerasorn.chichisticks;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Context context;
    ImageView img;

    static final int POLL_INTERVAL = 3000;
    SensorInfo sensorInfo = new SensorInfo();
    SensorManager sensorManager;
    Sensor accelerometerSensor;
    Handler hdr = new Handler();

    private Runnable pollTask = new Runnable() {
        @Override
        public void run() {
            sensorInfo.showDialog(context);
            hdr.postDelayed(pollTask, POLL_INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        img = (ImageView)findViewById(R.id.imageView);
        img.setImageResource(R.drawable.siemsee);

    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        hdr.postDelayed(pollTask, POLL_INTERVAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        if(type == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            if (x < -2) {
                img.setImageResource(R.drawable.stick2);
            } else if (x < 2) {
                img.setImageResource(R.drawable.stick0);
            } else {
                img.setImageResource(R.drawable.stick3);
            }

            sensorInfo.setSensor(x, y, z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
