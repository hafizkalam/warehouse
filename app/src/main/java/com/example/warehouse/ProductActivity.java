package com.example.warehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.warehouse.adapters.ProductAdapter;
import com.example.warehouse.models.ActionDatas;
import com.example.warehouse.models.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductActivity extends AppCompatActivity {
    private List<Products> productsList;
    private ActionDatas actionDatas;
    private RecyclerView productRV;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productRV = findViewById(R.id.productRV);
        productsList = new ArrayList<>();
        actionDatas = new ActionDatas();
        fetchDatas();
    }

    private void fetchDatas() {
        actionDatas.readProducts().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> productData =  document.getData();
                        productsList.add(new Products(document.getId().toString(),productData.get("name").toString(), productData.get("category").toString(), Integer.parseInt(productData.get("price").toString()) ));
                    }
                    setupRV();
                } else {
                    Log.w("RESULT", "Error getting documents.", task.getException());
                }
            }
        });
    }
    private void setupRV() {
        productRV.setAdapter(new ProductAdapter(productsList));
        productRV.setLayoutManager(new LinearLayoutManager(this));
    }


}