package com.example.pbp_roomdatabase.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "truang")
public class Ruang implements Serializable{

    @PrimaryKey(autoGenerate = true)
    public int ruangId;

    @ColumnInfo(name = "nama ruang")
    public String namaRuang;

    @ColumnInfo(name = "kode gedung")
    public String kodeGedung;

    @ColumnInfo(name = "kapasitas ruang")
    public String kapasitasRuang;

    public int getRuangId() {
        return ruangId;
    }

    public void setRuangId(int ruangId) {
        this.ruangId = ruangId;
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public String getKodeGedung() {
        return kodeGedung;
    }

    public String getKapasitasRuang() {
        return kapasitasRuang;
    }

    public void setNamaRuang(String namaRuang) {
        this.namaRuang = namaRuang;
    }

    public void setKodeGedung(String kodeGedung) {
        this.kodeGedung = kodeGedung;
    }

    public void setKapasitasRuang(String kapasitasRuang) {
        this.kapasitasRuang = kapasitasRuang;
    }

}
