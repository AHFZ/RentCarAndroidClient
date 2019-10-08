package ir.ahfz.rentcar.io.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<T> t);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(List<T> t);

    @Delete
    void delete(List<T> t);
}
