package com.example.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.ET_Username);
        password = findViewById(R.id.ET_password);
        btnLogin = findViewById(R.id.btnLogin);
    }


    public void onClickLogin(View view) {
        String usernameKey = username.getText().toString();
        String passwordKey = password.getText().toString();

        if (usernameKey.equals("admin") && passwordKey.equals("1234")){
            //jika login berhasil
            Toast.makeText(getApplicationContext(), "LOGIN SUKSES",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
            finish();
        }else {
            //jika login gagal
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Username atau Password Anda salah!")
                    .setNegativeButton("Retry", null).create().show();
        }
    }
}
