package com.example.pbp_roomdatabase;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pbp_roomdatabase.data.factory.AppDatabase;
import com.example.pbp_roomdatabase.model.Ruang;

public class RoomCreateActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "ruangdb").build();

        final EditText etNamaRuang   = findViewById(R.id.et_namaruang);
        final EditText etKodeGedung   = findViewById(R.id.et_kodegedung);
        final EditText etKapasitasRuang  = findViewById(R.id.et_kapasitasruang);
        Button btSubmit         = findViewById(R.id.bt_submit);

        final Ruang ruang = (Ruang) getIntent().getSerializableExtra("data");

        if(ruang!=null){
            etNamaRuang.setText(ruang.getNamaRuang());
            etKodeGedung.setText(ruang.getKodeGedung());
            etKapasitasRuang.setText(ruang.getKapasitasRuang());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ruang.setNamaRuang(etNamaRuang.getText().toString());
                    ruang.setKodeGedung(etKodeGedung.getText().toString());
                    ruang.setKapasitasRuang(etKapasitasRuang.getText().toString());

                    updateRuang(ruang);
                }
            });
        }else{
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ruang r = new Ruang();
                    r.setKapasitasRuang(etKapasitasRuang.getText().toString());
                    r.setKodeGedung(etKodeGedung.getText().toString());
                    r.setNamaRuang(etNamaRuang.getText().toString());
                    insertData(r);
                }
            });
        }
    }

    private void updateRuang(final Ruang ruang){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.ruangDAO().updateRuang(ruang);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(RoomCreateActivity.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void insertData(final Ruang ruang){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.ruangDAO().insertRuang(ruang);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(RoomCreateActivity.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, RoomCreateActivity.class);
    }
}
