package id.kuyfutsal.kuyfutsal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {
    String email, name, username;
    TextView txtName, txtEmail, txtUsername;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pd = new ProgressDialog(ProfileActivity.this);
        txtUsername = (TextView) findViewById(R.id.usernameId);
        txtName = (TextView) findViewById(R.id.nameId);
        txtEmail = (TextView) findViewById(R.id.emailId);

        SharedPreferences sp = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        username = sp.getString("username", "nothing");
        name = sp.getString("name", "nothing");
        email = sp.getString("email", "nothing");

        getData();
    }

    public void getData(){

        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        txtUsername.setText(username);

        String url = "http://192.168.100.4:8000/api/getUser" + "/" + username;
        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                txtName.setText(data.getString("name"));
                                txtEmail.setText(data.getString("email"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        Volley.newRequestQueue(this).add(reqData);
        //AppController.getInstance().addToRequestQueue(reqData);
    }

    public void UpdateProfile(View view) {
        Intent it = new Intent(this, EditProfile.class);
        startActivity(it);
    }
}
