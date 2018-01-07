package id.kuyfutsal.kuyfutsal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    EditText name;
    EditText email;
    EditText password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.edtName);
        email = (EditText) findViewById(R.id.edtEmail);
        password = (EditText) findViewById(R.id.edtPassword);

    }


    public void Register(View view) {

        int method = Request.Method.POST;
        String url = "http://192.168.100.14:8000/api/register";

        StringRequest sr = new StringRequest(method, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        SharedPreferences sp = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                        String newName = name.getText().toString();
                        String newEmail = email.getText().toString();
                        String newPassword = password.getText().toString();

                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString("name",newName);
                        editor.commit();
                        editor.putString("email",newEmail);
                        editor.commit();
                        editor.putString("password", newPassword);
                        editor.commit();
//                        editor.putString(newName + newPassword + "data", newName + "\n" + newEmail);
//                        editor.commit();

                        Toast.makeText(getBaseContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(it);
//                        Toast.makeText(getBaseContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
//                        finish();
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
                params.put("name", name.getText().toString());
                params.put("email", email.getText().toString());
                params.put("password", password.getText().toString());

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(sr);

//        SharedPreferences sp = getSharedPreferences("MYPREFS", MODE_PRIVATE);
//        String newName = name.getText().toString();
//        String newEmail = email.getText().toString();
//        String newPassword = password.getText().toString();
//
//        SharedPreferences.Editor editor = sp.edit();
//
//        editor.putString("name",newName);
//        editor.commit();
//        editor.putString("email",newEmail);
//        editor.commit();
//        editor.putString("password", newPassword);
//        editor.commit();
////                        editor.putString(newName + newPassword + "data", newName + "\n" + newEmail);
////                        editor.commit();
//
//        Toast.makeText(getBaseContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
//        Intent it = new Intent(getBaseContext(), LoginActivity.class);
//        startActivity(it);

    }
}
