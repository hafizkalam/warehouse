package com.example.warehouse.models;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActionDatas {
    private FirebaseFirestore db;
    private final String pathCollection = "products";
    List<Products> products;

    public ActionDatas() {
        db = FirebaseFirestore.getInstance();
        products = new ArrayList<>();
    }

    public Task<QuerySnapshot> readProducts(){
        return db.collection(pathCollection).get();
    }
    public Task<DocumentReference> createProducts(Map<String, Object> datas) {
        return db.collection(pathCollection).add(datas);
    }
    public Object deleteProducts(String id){
        return db.collection(pathCollection).document(id).delete();
    }
}
