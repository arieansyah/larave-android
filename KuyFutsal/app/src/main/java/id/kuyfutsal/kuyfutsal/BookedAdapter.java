package id.kuyfutsal.kuyfutsal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by borneo on 16/01/18.
 */

public class BookedAdapter extends RecyclerView.Adapter<BookedAdapter.BookedHolder> {

    Context ctx;
    ArrayList<Booked> dataBooked;

    public BookedAdapter(Context ctx, ArrayList<Booked> dataBooked) {
        this.ctx = ctx;
        this.dataBooked = dataBooked;
    }

    @Override
    public BookedAdapter.BookedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View BookedView = inflater.inflate(R.layout.booked_single, parent, false);

        return new BookedHolder(BookedView);
    }

    @Override
    public void onBindViewHolder(BookedAdapter.BookedHolder holder, final int position) {
        holder.txtJam.setText(dataBooked.get(position).getJam());
        holder.txtStatus.setText(dataBooked.get(position).getStatus());


        holder.ll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                    Intent it = new Intent(ctx, DetailBookedActivity.class);
                    it.putExtra("txtJadwal", dataBooked.get(position).getJam());
                    it.putExtra("txtStatus", dataBooked.get(position).getStatus());
                    ctx.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBooked.size();
    }

    public class BookedHolder extends RecyclerView.ViewHolder{
        TextView txtJam,txtStatus;
        LinearLayout ll;

        public BookedHolder(View bookedView) {
            super(bookedView);
            txtJam = (TextView) bookedView.findViewById(R.id.jamId);
            txtStatus= (TextView) bookedView.findViewById(R.id.statusId);
            ll = (LinearLayout) bookedView.findViewById(R.id.ll_id);
        }
    }
}
