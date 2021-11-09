package com.example.warehouse.models;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application {
    private FirebaseFirestore db;
    private final String pathCollection = "products";
    List<Products> products;

    public Application() {
        db = FirebaseFirestore.getInstance();
        products = new ArrayList<>();
    }

    public List<Products> readProducts(){
        db.collection(pathCollection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document: task.getResult()){
                        Map<String, Object> productData =  document.getData();
                        products.add(new Products(document.getId().toString(),productData.get("name").toString(), productData.get("category").toString(), Integer.parseInt(productData.get("price").toString()) ));
                    }
                }
            }
        });
        return products;
    }
    public Task<DocumentReference> createProducts(Map<String, Object> datas) {
        return db.collection(pathCollection).add(datas);
    }
    public Object deleteProducts(String id){
        return db.collection(pathCollection).document(id).delete();
    }
}
