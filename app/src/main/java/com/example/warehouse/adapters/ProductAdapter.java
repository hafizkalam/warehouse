package com.example.warehouse.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehouse.R;
import com.example.warehouse.models.Products;

import java.text.NumberFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Products> productsList;

    public ProductAdapter(List<Products> productsList) {
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Products products = productsList.get(position);
        holder.bind(position, products);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTV, productCategoryTV, productPriceTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTV = itemView.findViewById(R.id.item_product_name);
            productCategoryTV = itemView.findViewById(R.id.item_product_category_name);
            productPriceTV = itemView.findViewById(R.id.item_product_price);
        }
        public void bind(int position, Products products) {
            productNameTV.setText(products.getName());
            productCategoryTV.setText(products.getCategory());
            productPriceTV.setText("Rp. " + NumberFormat.getInstance().format(products.getPrice()).toString());
        }
    }
}
