package com.rotonity.inventory.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.v4.content.ContextCompat.startActivity;

public class ProgrammingAdaptor extends RecyclerView.Adapter<ProgrammingAdaptor.ProgrammingViewHolder> {
    String[] data;
    protected ViewGroup viewGroup;
   private Context context;
    ProgrammingAdaptor(String[] data,Context context){
        this.context=context;
        this.data=data;
    }


    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());//TODO);
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        this.viewGroup=viewGroup;

        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder programmingViewHolder, int i) {
       final String[] ne = data[i].split(", ");
try {
    {

        EditInventory.context = context;
        EditInventory.pT = ne[0];
        EditInventory.des = ne[1];
        EditInventory.cos = ne[2];
        EditInventory.pur = ne[3];
        EditInventory.cW = ne[4];
        EditInventory.bC = ne[5];

        Bundle bundle = new Bundle();
        bundle.putString("partType", ne[0]);
        bundle.putString("description", ne[1]);
        bundle.putString("cost", ne[2]);
        bundle.putString("pur", ne[3]);
        bundle.putString("cW", ne[4]);
        bundle.putString("bC", ne[5]);

        final Intent intent = new Intent(context, ViewPage.class);
        intent.putExtras(bundle);


        String title = ne[0];
        String des = ne[1];
        programmingViewHolder.textView.setText(title);
        programmingViewHolder.t1.setText(des);
        programmingViewHolder.viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  context.startActivity(new Intent(context,EditInventory.class));
                context.startActivity(intent);

            }
        });
    }}catch (Exception r){Toast.makeText(context,r.getMessage(),Toast.LENGTH_LONG).show();
}

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView t1;
        Button viewItem;
        ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imageView2);
            textView = itemView.findViewById(R.id.one);
            t1=itemView.findViewById(R.id.desc);
            viewItem=itemView.findViewById(R.id.viewItem);
        }
    }
}
