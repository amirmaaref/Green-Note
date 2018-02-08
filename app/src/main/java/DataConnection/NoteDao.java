package DataConnection;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amirhossein on 1/29/18.
 */

    @Dao
    public interface NoteDao {


        @Query("SELECT * FROM note")
        List<Note> getAll();

        @Insert
        void insertOne(Note noteForInsert);

        @Delete
        void deleteOne(Note noteForDelete);

        @Update
        void Update(Note nn);


    }

