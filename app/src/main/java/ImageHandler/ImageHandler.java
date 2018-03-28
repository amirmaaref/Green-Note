package ImageHandler;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import DataConnection.AppDatabase;
import DataConnection.Note;

/**
 * Created by amirhossein on 2/2/18.
 */

public class ImageHandler {
   private static Bitmap b;

    public static Bitmap getImageBitmap(Context context , String Filename,final AppDatabase appDatabase){


        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Note> getList = appDatabase.noteDao().getAll();
            }
        }).start();

        try{

            ContextWrapper cw = new ContextWrapper(context);
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            // Create imageDir
            File mypath=new File(directory,Filename+".jpg");

            b = BitmapFactory.decodeStream(new FileInputStream(mypath));
            return b;
        }
        catch (Exception e){
          //  Toast.makeText(context, "File Not Found", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
