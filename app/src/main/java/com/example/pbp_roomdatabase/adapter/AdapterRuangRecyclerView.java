package com.example.pbp_roomdatabase.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.pbp_roomdatabase.R;
import com.example.pbp_roomdatabase.RoomCreateActivity;
import com.example.pbp_roomdatabase.RoomReadSingleActivity;
import com.example.pbp_roomdatabase.data.factory.AppDatabase;
import com.example.pbp_roomdatabase.model.Ruang;


public class AdapterRuangRecyclerView extends RecyclerView.Adapter<AdapterRuangRecyclerView.ViewHolder> {

    private ArrayList<Ruang> daftarRuang;
    private Context context;
    private AppDatabase db;

    public AdapterRuangRecyclerView(ArrayList<Ruang> ruangs, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarRuang = ruangs;
        context = ctx;

        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "ruangdb").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_namaruang);
            cvMain = v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruang, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final String name = daftarRuang.get(position).getNamaRuang();

        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */
                Ruang ruang = db.ruangDAO().selectRuangDetail(daftarRuang.get(position).getRuangId());
                context.startActivity(RoomReadSingleActivity.getActIntent((Activity) context).putExtra("data", ruang));
            }
        });
        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_dialog);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = dialog.findViewById(R.id.bt_edit_data);
                Button delButton = dialog.findViewById(R.id.bt_delete_data);

                //apabila tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onEditRuang(position);
                            }
                        }
                );

                //apabila tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onDeteleRuang(position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    private void onDeteleRuang(int position){
        db.ruangDAO().deleteRuang(daftarRuang.get(position));
        daftarRuang.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarRuang.size());
    }

    private void onEditRuang(int position){
        context.startActivity(RoomCreateActivity.getActIntent((Activity) context).putExtra("data", daftarRuang.get(position)));
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada ruang
         */
        return daftarRuang.size();
    }
}
