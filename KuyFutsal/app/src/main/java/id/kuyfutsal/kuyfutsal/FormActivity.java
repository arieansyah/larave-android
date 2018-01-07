package id.kuyfutsal.kuyfutsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {

    private EditText id_jadwal, status, nama, no_hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        id_jadwal = (EditText) findViewById(R.id.id_jadwal);

        nama = (EditText) findViewById(R.id.nama);
        no_hp = (EditText) findViewById(R.id.phone);

        Bundle it = getIntent().getExtras();
//        String idJdwl = it.getString("txtJadwal");
//        String sts = it.getString("txtStatus");
        //String nama = it.getString("nama");


        id_jadwal.setText(it.getCharSequence("txtJadwal"));
       // status.setText(it.getCharSequence("txtStatus"));
    }

    public void saveData (View view) {
        int method = Request.Method.POST;
        String url = "http://192.168.100.14:8000/api/jadwal";

        StringRequest sr = new StringRequest(method, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        Toast.makeText(getBaseContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
                        startActivity( new Intent(FormActivity.this,JadwalActivity.class));
                        //finish();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("nama", nama.getText().toString());
                params.put("jadwal_id", id_jadwal.getText().toString());
                params.put("no_hanphone", no_hp.getText().toString());
                params.put("status", "Proses...");

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(sr);
    }
}
