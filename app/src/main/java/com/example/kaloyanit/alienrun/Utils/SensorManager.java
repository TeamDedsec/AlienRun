package com.example.kaloyanit.alienrun.Utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Created by Chet on 26.2.2017 г..
 */

public class SensorManager implements SensorEventListener {
    private android.hardware.SensorManager sensorManager;
    private int rotation;

    public int getRotation() {
        return rotation;
    }

    public SensorManager() {
        sensorManager = BasicConstants.SENSOR_SERVICE;
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), android.hardware.SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.rotation = (int) (event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
