package com.example.pbp_roomdatabase.data.factory;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.pbp_roomdatabase.data.RuangDAO;
import com.example.pbp_roomdatabase.model.Ruang;

@Database(entities = {Ruang.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RuangDAO ruangDAO();

}
