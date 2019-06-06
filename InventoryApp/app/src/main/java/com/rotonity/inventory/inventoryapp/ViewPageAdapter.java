package com.rotonity.inventory.inventoryapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class ViewPageAdapter extends RecyclerView.Adapter<ViewPageAdapter.NewViewHolder> {

    private InventoryItem inventoryItem;
    private Context context;
    protected ViewGroup viewGroup;


    ViewPageAdapter(InventoryItem inventoryItem, Context context) {
        this.inventoryItem = inventoryItem;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewPageAdapter.NewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.view_page_new, viewGroup, false);
        this.viewGroup=viewGroup;
        return new NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPageAdapter.NewViewHolder viewHolder, int i) {
        String[] Data=setData(inventoryItem);
        viewHolder.textView2.setText(Data[i]);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class NewViewHolder extends RecyclerView.ViewHolder {

        TextView textView1,textView2;
        NewViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.qry_result);
            //TODO: add View objects here
        }
    }

    private String[] setData(InventoryItem item){
        String[] data= new String[]{
                "Part Type:"+item.getPart_type(),
                "Description"+item.getDescription(),
                "Barcode:"+item.getBar_code(),
                "Currently With"+item.getCurrently_with(),
                "Purchased By:"+item.getPurchased_by(),
                "Cost:"+item.getCost(),
                "Availability:"+item.getAvailability(),
                "Last Edited By:"+item.getLast_edit()
        };
        return data;
    }

}
