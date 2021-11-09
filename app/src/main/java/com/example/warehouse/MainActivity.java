package com.example.warehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView firebasenameview;
    Button toast;

    private CardView addItems, deleteItems, scanItems, viewInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebasenameview = findViewById(R.id.txtUsername);

        addItems = (CardView)findViewById(R.id.addItems);
        deleteItems = (CardView) findViewById(R.id.deleteItems);
        scanItems = (CardView) findViewById(R.id.scanItems);
        viewInventory = (CardView) findViewById(R.id.viewInventory);

        addItems.setOnClickListener(this);
        deleteItems.setOnClickListener(this);
        scanItems.setOnClickListener(this);
        viewInventory.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.addItems : i = new Intent(this,additemActivity.class); startActivity(i); break;
//            case R.id.deleteItems : i = new Intent(this,deleteItemsActivity.class);startActivity(i); break;
//            case R.id.scanItems : i = new Intent(this,scanItemsActivity.class);startActivity(i); break;
            case R.id.viewInventory : i = new Intent(this,viewInventoryActivity.class);startActivity(i); break;
            default: break;
        }
    }

    // logout below
    private void Logout()
    {
        finish();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        Toast.makeText(MainActivity.this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.logoutMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}