package id.kuyfutsal.kuyfutsal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by borneo on 26/12/17.
 */

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.JadwalHolder> {

    Context ctx;
    ArrayList<Jadwal> dataJadwal;

    public JadwalAdapter(Context ctx, ArrayList<Jadwal> dataJadwal) {
        this.ctx = ctx;
        this.dataJadwal = dataJadwal;
    }

    @Override
    public JadwalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View jadwalView = inflater.inflate(R.layout.list_item_jadwal, parent, false);

        return new JadwalHolder(jadwalView);
    }

    @Override
    public void onBindViewHolder(JadwalHolder holder, final int position) {
        //holder.jadwal_id.setText(dataJadwal.get(position).getId_jadwal());
        holder.txtJam.setText(dataJadwal.get(position).getJam());
        if (dataJadwal.get(position).getStatus().equals("null") ){
            holder.txtStatus.setText("free");
        }else {
            holder.txtStatus.setText(dataJadwal.get(position).getStatus());
        }


        holder.rl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (dataJadwal.get(position).getStatus().equals("Booking")){
                    Toast.makeText(view.getContext(), "Jadwal Sudah di Booking", Toast.LENGTH_LONG).show();
                }else if (dataJadwal.get(position).getStatus().equals("Proses...")){
                    Toast.makeText(view.getContext(), "Jadwal Sedang di Proses", Toast.LENGTH_LONG).show();
                }else{
                    Intent it = new Intent(ctx, FormActivity.class);
                    it.putExtra("txtJadwal", dataJadwal.get(position).getId_jadwal());
                    it.putExtra("txtStatus", dataJadwal.get(position).getStatus());
                    ctx.startActivity(it);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataJadwal.size();
    }

    public class JadwalHolder extends RecyclerView.ViewHolder {

        TextView txtJam, txtStatus, jadwal_id;
        RelativeLayout rl;

        public JadwalHolder(View jadwalView) {
            super(jadwalView);
            jadwal_id = (TextView) jadwalView.findViewById(R.id.id_jadwal);
            txtJam = (TextView) jadwalView.findViewById(R.id.txt_jam);
            txtStatus = (TextView) jadwalView.findViewById(R.id.txt_status);
            rl = (RelativeLayout) jadwalView.findViewById(R.id.rl_id);

        }
    }
}
