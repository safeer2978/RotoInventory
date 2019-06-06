package com.rotonity.inventory.inventoryapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ViewListAdaptor extends RecyclerView.Adapter<ViewListAdaptor.ViewHolder> {

private InventoryItem[] inventoryItem;
private Context context;
protected ViewGroup viewGroup;
private List<InventoryItem> inventoryItemList;//TODO

        ViewListAdaptor(/*InventoryItem[] inventoryItem,*/List<InventoryItem> inventoryItemList, Context context) {
     //   this.inventoryItem = inventoryItem;
            this.inventoryItemList=inventoryItemList;
        this.context = context;
        }

    @NonNull
    @Override
    public ViewListAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item,null);
       // this.viewGroup=viewGroup;
        return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

            final InventoryItem item=inventoryItemList.get(position);

       // String[] Data=setData(inventoryItem);
        viewHolder.Name.setText(item.getDescription());
        viewHolder.availability.setText(item.getAvailability());
        viewHolder.currently_with.setText(item.getCurrently_with());

        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ViewPage.class);
               intent.putExtra("item",item);
                context.startActivity(intent);
            }
        });
 /*       final Intent intent1 = new Intent(context, ViewPage.class);
        intent1.putExtra("itemToDisplay",inventoryItem[i]);
        viewHolder.Item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // context.startActivity(new Intent(context,ViewInventory.class));
                context.startActivity(intent1);

            }
        });*/



    }
    @Override
    public int getItemCount() {
        return inventoryItemList.size();
        }

class ViewHolder extends RecyclerView.ViewHolder {
    ImageView itemIcon;
    TextView Name;
    TextView availability;
    LinearLayout card;
    TextView currently_with;
    Button Item_button;
   public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemIcon = itemView.findViewById(R.id.item_icon);
        Name= itemView.findViewById(R.id.item_name);
        availability=itemView.findViewById(R.id.availability);
        currently_with=itemView.findViewById(R.id.view_currently_with);
        card=itemView.findViewById(R.id.main_menu_card);
        //TODO: add View objects here
    }
}

   /* private String[] setData(InventoryItem item){
        String[] data= new String[item.no_of_Att()];
        data[0]="Part Type:"+item.getPart_type();
        data[1]="Part Name:"+item.getName();
        return data;
    }
*/
}
