package com.sobkisu.store.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sobkisu.store.R;
import com.sobkisu.store.db.CategoryRepository;
import com.sobkisu.store.model.ProductCategory;

import java.util.ArrayList;

public class ProductCategoryAdapterJava extends RecyclerView.Adapter<ProductCategoryAdapterJava.MyViewHolder> {

    public Context mainContext;
    public ArrayList<ProductCategory> listData;
    LayoutInflater layoutInflater;

    public ProductCategoryAdapterJava(Context context, ArrayList<ProductCategory> data) {
        this.mainContext = context;
        listData = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(layoutInflater.inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {
            holder.nameTV.setText(listData.get(position).getPcName());
            holder.count.setText("Total Product : " + new CategoryRepository().getAllProductCount(listData.get(position).getPcName()));
            holder.onDeleteClicked(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton deleteButton;
        TextView nameTV;
        TextView count;


        public MyViewHolder(View itemView) {
            super(itemView);
            deleteButton = itemView.findViewById(R.id.categoryDelete);
            nameTV = itemView.findViewById(R.id.category_name_id);
            count = itemView.findViewById(R.id.total_product_under_category_id);

        }

        public void onDeleteClicked(final int pos) {

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean flag = new CategoryRepository().deleteProductCategoryByName(listData.get(pos).getPcName());
                    if (flag) {
                        listData.remove(pos);
                        notifyDataSetChanged();
                    }
                }
            });


        }

    }
}
