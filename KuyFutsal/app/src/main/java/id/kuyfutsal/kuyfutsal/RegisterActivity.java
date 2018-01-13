package id.kuyfutsal.kuyfutsal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

public class RegisterActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtEmail;
    EditText edtPassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

    }


    public void Register(View view) {

        int method = Request.Method.POST;
        String url = "http://192.168.100.15:8000/api/register";

        final String name = edtName.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            edtName.setError("Please enter username");
            edtName.requestFocus();
            return;
        }

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

        StringRequest sr = new StringRequest(method, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        SharedPreferences sp = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                        String newName = name;
                        String newEmail = email;
                        String newPassword = password;

                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString("name",newName);
                        editor.commit();
                        editor.putString("email",newEmail);
                        editor.commit();
                        editor.putString("password", newPassword);
                        editor.commit();

                        Toast.makeText(getBaseContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent it = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(it);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        error.printStackTrace();

                            edtEmail.setError("Email is Already Exist");
                            edtEmail.requestFocus();


                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(sr);

    }
}
