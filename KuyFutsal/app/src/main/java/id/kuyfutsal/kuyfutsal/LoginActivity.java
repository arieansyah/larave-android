package id.kuyfutsal.kuyfutsal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        EditText edtName, edtpassword;

        edtName = (EditText) findViewById(R.id.edtName);
        edtpassword = (EditText) findViewById(R.id.edtPassword);

        String username = edtName.getText().toString();
        String password = edtpassword.getText().toString();

        SharedPreferences sp = getSharedPreferences("MYPREFS", MODE_PRIVATE);

        //get data from shared preferences
        String user = sp.getString("name", "");
        String pass = sp.getString("password", "");


        if (username.equals(user) && password.equals(pass)){
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
        }else{
            Toast.makeText(this, "Username and Password not Match, Please.. Try Again!!", Toast
                    .LENGTH_LONG).show();
        }
    }
}
