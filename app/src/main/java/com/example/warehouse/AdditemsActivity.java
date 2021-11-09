package com.example.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.warehouse.models.ActionDatas;
import com.example.warehouse.models.Products;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdditemsActivity extends AppCompatActivity {
    private EditText itemname,itemcategory,itemprice;
    Button scanbutton, additemtodatabase;
    ActionDatas actionDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additems);
        additemtodatabase = findViewById(R.id.additembuttontodatabase);
        itemname = findViewById(R.id.edititemname);
        itemcategory= findViewById(R.id.editcategory);
        itemprice = findViewById(R.id.editprice);
        actionDatas = new ActionDatas();

        additemtodatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additem();
            }
        });
    }


    // addding item to databse
    public  void additem(){
        String itemnameValue = itemname.getText().toString();
        String itemcategoryValue = itemcategory.getText().toString();
        String itempriceValue = itemprice.getText().toString();
        Map<String, Object> product = new HashMap<>();

        if(!TextUtils.isEmpty(itemnameValue)&&!TextUtils.isEmpty(itemcategoryValue)&&!TextUtils.isEmpty(itempriceValue)){
            // add to Map Object
            product.put("name", itemnameValue);
            product.put("category", itemcategoryValue);
            product.put("price", Integer.parseInt(itempriceValue));
            actionDatas.createProducts(product).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        itemname.setText("");
                        itemprice.setText("");
                        itemcategory.setText("");
                        Toast.makeText(getApplicationContext(), "Berhasil menambahkan data. "+documentReference.getId(), Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(AdditemsActivity.this, MainActivity.class));
                    }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Error : "+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            Toast.makeText(AdditemsActivity.this,"Please Fill all the fields",Toast.LENGTH_SHORT).show();
        }
    }

    // logout below
    private void Logout()
    {
        finish();
        startActivity(new Intent(AdditemsActivity.this,LoginActivity.class));
        Toast.makeText(AdditemsActivity.this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

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
