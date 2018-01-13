package id.kuyfutsal.kuyfutsal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnToRegister(View view) {
        Intent it = new Intent(this, RegisterActivity.class);
        startActivity(it);
    }

    public void LoginApp(View view) {
        final EditText edtEmail, edtPassword;

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        final String email = edtEmail.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Please enter your email");
            edtEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Enter a valid email");
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Enter a password");
            edtPassword.requestFocus();
            return;
        }

        int method = Request.Method.POST;
        String url = "http://192.168.100.15:8000/api/login";

        StringRequest sr = new StringRequest(method, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        if (response.trim().equals("success")){

                            SharedPreferences sp = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();

                            editor.putString("email",email);
                            editor.commit();
                            editor.putString("password",password);
                            editor.commit();
                            finish();
                            Intent it = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(it);

                        }else {
                            Toast.makeText(LoginActivity.this, "Email & Password not Match !!!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(sr);
    }
}
