package com.example.eslam.mywedding.DataBases;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.eslam.mywedding.Models.HallsModels.Hall;

import java.util.List;

@Dao
public interface SetupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHall(Hall hall);

    @Query("SELECT * FROM hall")
    LiveData<List<Hall>> loadAllHalls();

    @Query("SELECT * FROM hall WHERE id=:mId")
    LiveData<List<Hall>> loadAllHallByID(int mId);

    @Query("DELETE FROM hall WHERE id = :mId")
    int deleteHallById(int mId);
}
