package sysu.sdcs.sensordatacollector;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by justk on 2018/6/14.
 */

public class DataSaveTask implements Runnable {

    private String file_name;

    public DataSaveTask(String file_name){
        this.file_name = file_name;
    }

    public void run(){
        String data = SensorData.getAllDataStr();
        SensorData.clear();
        if(!FileUtil.saveSensorData(file_name ,data))
            Log.d("data save", "data save task error!");
    }
}
