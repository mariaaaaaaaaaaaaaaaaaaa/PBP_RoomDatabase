package com.example.pbp_roomdatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pbp_roomdatabase.model.Ruang;

public class RoomReadSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditText etNama = findViewById(R.id.et_namaruang);
        EditText etGedung = findViewById(R.id.et_kodegedung);
        EditText etKapasitas = findViewById(R.id.et_kapasitasruang);
        Button btSubmit = findViewById(R.id.bt_submit);

        etNama.setEnabled(false);
        etGedung.setEnabled(false);
        etKapasitas.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Ruang ruang = (Ruang) getIntent().getSerializableExtra("data");
        if(ruang!=null){
            etNama.setText(ruang.getNamaRuang());
            etGedung.setText(ruang.getKodeGedung());
            etKapasitas.setText(ruang.getKapasitasRuang());
        }

    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, RoomReadSingleActivity.class);
    }
}
