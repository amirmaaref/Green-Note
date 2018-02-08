package DataConnection;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirhossein on 2/2/18.
 */

public class SimpleActions {
    public static void Insert(Context context1, String title,String text){
        AppDatabase  appDatabase = Room.databaseBuilder(context1,AppDatabase.class,"noteData")
                .allowMainThreadQueries().build();

        Note note = new Note(title, text);
         appDatabase.noteDao().insertOne(note);
    }
    public static void Insert(Context context,String title,String text,String img){
        AppDatabase  appDatabase = Room.databaseBuilder(context,AppDatabase.class,"noteData")
                .allowMainThreadQueries().allowMainThreadQueries().build();

        Note note = new Note(title, text,img);
        appDatabase.noteDao().insertOne(note);
    }
    public static void Update(Context context,Note nn){
        AppDatabase  appDatabase = Room.databaseBuilder(context,AppDatabase.class,"noteData")
                .allowMainThreadQueries().build();

        appDatabase.noteDao().Update(nn);
    }

    public static void Delete(Context context,Note nn) {
        AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class, "noteData")
                .allowMainThreadQueries().build();

        appDatabase.noteDao().deleteOne(nn);

    }
}
