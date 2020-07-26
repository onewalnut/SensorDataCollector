package sysu.sdcs.sensordatacollector;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by justk on 2018/6/13.
 */

public class FileUtil {

    public static String file_path = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/SensorData/";

    public static boolean saveSensorData(String file_name, String sensor_data){
        createFilePath();
        try {
            FileWriter writer;
            writer = new FileWriter(createFile(file_name), true);
            writer.append(sensor_data);
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            Log.e("file write error", e.getMessage() );
        }
        return false;
    }


    public static File createFile(String file_name){
        File file = new File(file_path + file_name);
        try{
            if(!file.exists())
                file.createNewFile();

        }catch (IOException e ){
            Log.e("file create error", e.getMessage() );

        }
        return file;
    }

    public static void createFilePath(){
        try{
            File file = new File(file_path);
            if(!file.exists())
                file.mkdir();
        }
        catch(Exception e){
            Log.e("file path create error", e.getMessage() );

        }
    }

}
