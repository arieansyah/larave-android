package id.kuyfutsal.kuyfutsal;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookedActivity extends AppCompatActivity {

    private ArrayList<Booked> dataBooked;
    private RecyclerView listBooked;
    private BookedAdapter adapter;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);

        listBooked = (RecyclerView) findViewById(R.id.booked_id);
        //pd = new ProgressDialog(JadwalActivity.this);
        generateData();//call
        listBooked.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listBooked.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(listBooked.getContext(),
                layoutManager.getOrientation());
        listBooked.addItemDecoration(divider);

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

        SharedPreferences sp = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        username = sp.getString("username", "nothing");
    }

    private void generateData() {
        dataBooked = new ArrayList<>();

        String url = "http://192.168.100.4:8000/api/booked/mobile/" + username;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject booked = array.getJSONObject(i);
                                dataBooked.add(new Booked(
                                        booked.getString("id_jadwal"),
                                        booked.getString("jam"),
                                        booked.getString("status")
                                ));
                            }

                            adapter = new BookedAdapter(BookedActivity.this, dataBooked);
                            adapter.notifyDataSetChanged();
                            listBooked.setAdapter(adapter);


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
