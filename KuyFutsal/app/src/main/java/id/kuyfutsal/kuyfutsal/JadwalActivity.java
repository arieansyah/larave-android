package id.kuyfutsal.kuyfutsal;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JadwalActivity extends AppCompatActivity {

    private static final String URL = "http://192.168.100.14:8000/api/jadwal/mobile";

    private ArrayList<Jadwal> dataJadwal;
    private RecyclerView listJadwal;
    private JadwalAdapter adapter;
    ProgressDialog pd;
    Handler mHandler;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        listJadwal = (RecyclerView) findViewById(R.id.jadwal_id);
        //pd = new ProgressDialog(JadwalActivity.this);
        generateData();//call
        listJadwal.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listJadwal.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(listJadwal.getContext(),
                layoutManager.getOrientation());
        listJadwal.addItemDecoration(divider);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        generateData();//call
                    }
                },3000);
            }
        });
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()
        {
            generateData();
            // MainActivity.this.mHandler.postDelayed(m_Runnable, 1000);
        }
    };//runnable

    private void generateData() {
//        pd.setMessage("Mengambil Data");
//        pd.setCancelable(false);
//        pd.show();

        dataJadwal = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        pd.cancel();
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject jadwal = array.getJSONObject(i);
                                dataJadwal.add(new Jadwal(
                                        jadwal.getString("id_jadwal"),
                                        jadwal.getString("jam"),
                                        jadwal.getString("status")
                                ));
                            }

                            adapter = new JadwalAdapter(JadwalActivity.this, dataJadwal);
                            adapter.notifyDataSetChanged();
                            listJadwal.setAdapter(adapter);


                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        //AppController.getInstance().addToRequestQueue(stringRequest);
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
