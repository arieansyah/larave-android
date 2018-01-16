package id.kuyfutsal.kuyfutsal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    String name,username,email, id_user;
    EditText edtName, edtUsername, edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edtName = (EditText) findViewById(R.id.txtName);
        edtUsername = (EditText) findViewById(R.id.txtUsername);
        edtEmail = (EditText) findViewById(R.id.txtEmail);
        edtPassword = (EditText) findViewById(R.id.txtPassword);

        SharedPreferences sp = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        name = sp.getString("name", "nothing");
        username = sp.getString("username", "nothing");
        email = sp.getString("email", "nothing");
        id_user = sp.getString("id_user", "nothing");

        edtName.setText(name);
        edtUsername.setText(username);
        edtEmail.setText(email);
    }

    public void ChoiceProfile(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void UpdateProfile(View view) {

        int method = Request.Method.PUT;
        String url = "http://192.168.100.4:8000/api/upUser/" + id_user;

        final String name = edtName.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String username = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();

        StringRequest sr = new StringRequest(method, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        SharedPreferences sp = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                        String newName = name;
                        String newUsername = username;
                        String newEmail = email;
                        String newPassword = password;

                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString("name",newName);
                        editor.commit();
                        editor.putString("name",newUsername);
                        editor.commit();
                        editor.putString("email",newEmail);
                        editor.commit();
                        editor.putString("password", newPassword);
                        editor.commit();

                        Toast.makeText(getBaseContext(), "Data Already Saved", Toast
                                .LENGTH_SHORT).show();
//                        Intent it = new Intent(EditProfile.this, ProfileActivity.class);
//                        startActivity(it);
                        finish();
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
                params.put("name", name);
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

//        MyApplication.getInstance().addToReqQueue(postRequest);
//        break;
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(sr);
    }
}
