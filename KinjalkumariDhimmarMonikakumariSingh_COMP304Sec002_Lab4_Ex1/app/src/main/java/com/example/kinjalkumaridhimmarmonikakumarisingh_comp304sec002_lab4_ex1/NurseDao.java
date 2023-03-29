package com.example.kinjalkumaridhimmarmonikakumarisingh_comp304sec002_lab4_ex1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface NurseDao {
    @Insert
    void insert(Nurse nurse);

    @Update
    void update(Nurse nurse);

    @Delete
    void delete(Nurse nurse);

    @Query("DELETE FROM Nurse")
    void deleteAll();

    @Query("Select * FROM Nurse where nurseID = :nurseID")
    LiveData<Nurse> getByNurseID(int nurseID);

    @Query("Select * FROM Nurse")
    LiveData<List<Nurse>> getAllNurse();
}
