package com.example.pbp_roomdatabase.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.pbp_roomdatabase.model.Ruang;

@Dao
public interface RuangDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRuang(Ruang ruang);

    @Update
    int updateRuang(Ruang ruang);

    @Delete
    int deleteRuang(Ruang ruang);

    @Query("SELECT * FROM truang")
    Ruang[] selectAllRuangs();

    @Query("SELECT * FROM truang WHERE ruangId = :id LIMIT 1")
    Ruang selectRuangDetail(int id);
}
