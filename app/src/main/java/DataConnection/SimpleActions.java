package DataConnection;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirhossein on 2/2/18.
 */

public class SimpleActions {
    public static void Insert(Context context1, String title, String text) {
        final AppDatabase appDatabase = Room.databaseBuilder(context1, AppDatabase.class, "noteData")
                .build();

        final Note note = new Note(title, text);
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.noteDao().insertOne(note);
            }
        }).start();

    }

    public static void Insert(Context context, String title,
                              String text, String img) {
        final AppDatabase appDatabase = Room.databaseBuilder(
                context, AppDatabase.class, "noteData")
                .build();
        final Note note = new Note(title, text, img);
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.noteDao().insertOne(note);
            }
        }).start();

    }

    public static void Update(Context context, Note nn) {
        final AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class, "noteData")
                .build();
        final Note ns = nn;
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.noteDao().Update(ns);
            }
        }).start();
    }

    public static void Delete(Context context, final Note nn) {
        final AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class, "noteData")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.noteDao().deleteOne(nn);
            }
        }).start();

    }
}
