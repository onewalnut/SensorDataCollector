package sysu.sdcs.sensordatacollector;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by justk on 2018/6/13.
 */

public class SensorListener implements SensorEventListener{

        private static int[] SYNC = {0,0,0,0,0,0};
        private String[] accelerometerData;
        private String[] magneticData ;
        private String[] orientationData ;
        private String[] gyroscopeData;
        private String stepCounterData = "0";
        private String stepDetectorData = "0";


        @Override
        public void onSensorChanged(SensorEvent event){
            switch(event.sensor.getType()){
                case Sensor.TYPE_GYROSCOPE:{
                    gyroscopeData = new String[3];
                    gyroscopeData[0] = ""+event.values[0];
                    gyroscopeData[1] = ""+event.values[1];
                    gyroscopeData[2] = ""+event.values[2];
                    SYNC[0] = 1;
                    Log.d("capsensordata_g", accelerometerData[1]);
                    break;
                }
                case Sensor.TYPE_ACCELEROMETER:{
                    accelerometerData = new String[3];
                    accelerometerData[0] = ""+event.values[0];
                    accelerometerData[1] = ""+event.values[1];
                    accelerometerData[2] = ""+event.values[2];
                    SYNC[1] = 1;
                    Log.d("capsensordata_a", accelerometerData[1]);
                    break;
                }
                case Sensor.TYPE_ORIENTATION:{
                    orientationData = new String[3];
                    orientationData[0] = ""+event.values[0];
                    orientationData[1] = ""+event.values[1];
                    orientationData[2] = ""+event.values[2];
                    SYNC[2] = 1;
                    Log.d("capsensordata_o", orientationData[1]);
                    break;
                }
                case Sensor.TYPE_MAGNETIC_FIELD:{
                    magneticData = new String[3];
                    magneticData[0] = ""+event.values[0];
                    magneticData[1] = ""+event.values[1];
                    magneticData[2] = ""+event.values[2];
                    SYNC[3] = 1;
                    Log.d("capsensordata_m", magneticData[1]);
                    break;
                }
                case Sensor.TYPE_STEP_COUNTER:{
                    stepCounterData = ""+event.values[0];
                    Log.d("capsensordata_s1", stepCounterData);
                    SYNC[4] = 1;
                    break;
                }
                case Sensor.TYPE_STEP_DETECTOR:{
                    stepDetectorData = ""+event.values[0];
                    Log.d("capsensordata_s2", stepCounterData);
                    SYNC[5] = 1;
                    break;
                }
                default:
                    break;
            }

            if(sync()) {
                String captime = getCurrentTime();
                String[] stepData = new String[2];
                stepData[1] = stepCounterData;
                stepData[0] = stepDetectorData;

                SensorData.addSensorData(magneticData, accelerometerData, orientationData,
                        gyroscopeData, stepData, captime);
                reset();
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        public boolean sync(){
            for(int i = 0 ; i < 4 ; i++){
                if(SYNC[i] == 0)
                    return false;
            }
            return true;
        }

        public void reset(){
            for(int i = 0 ; i < SYNC.length ; i++){
                SYNC[i] = 0;
            }
        }

        public String getCurrentTime(){
            return new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        }

}
