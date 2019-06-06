package com.rotonity.inventory.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPage extends AppCompatActivity {
    RecyclerView recyclerView;

    String bar_code;
    TextView item_name;

    String part_type,available,lastedit,purchasedby,currentlywith,description1,cost1;
    String[] data=new String[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        recyclerView=findViewById(R.id.recycle_viewpage);
        item_name=findViewById(R.id.item_name);

       // NEEDS BARCODE OF OBJECT TO DISPLAY
        InventoryItem item= (InventoryItem) getIntent().getSerializableExtra("item");
        item_name.setText(item.getDescription());
        try {
            //recyclerView = vifindViewById(R.id.re);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(new ViewPageAdapter(item,getApplicationContext()));
        }
        catch (Exception r) {
            Toast.makeText(this.getApplicationContext(), r.getMessage()+"65165156!", Toast.LENGTH_LONG).show();
        }

        /*
        if(b.getString("scan").equals("yes")){
            Global.type="one";
            String barcode=b.getString("barcode");
           // Toast.makeText(this,"b:"+barcode, Toast.LENGTH_LONG).show();

            String data = EntireDatabase.giveSpecificData(barcode);

           final String[] ne = data.split(", ");
           item_name.setText(ne[6]);

                final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),1,false);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                String[] sata= new String[10];
try {
    sata[0] = "Part Type:" + ne[0];
    sata[1] = "Available :" + ne[1];
    sata[2] = "Last Edited:" + ne[2];
    sata[3] = "Purchased By:" + ne[4];
    sata[4] = "Currently With:" + ne[3];
    sata[5] = "Cost:" + ne[5];
    sata[6] = "Description:" + ne[6];
    sata[7] = "Barcode:" + ne[7];
    this.bar_code=ne[7];
              *//*  TextView textView=findViewById(R.id.item_name);
                textView.setText(ne[6]);*//*
}catch (Exception e){}
                datalist.setLayoutManager(layoutManager);
                datalist.setAdapter(new ProgrammingAdapter(sata, this));


            //TODO add to Que/ check/ get data from Entire Database
        }
        else if (b.getString("scan").equals("no")){

        item_name=findViewById(R.id.item_name);
        item_name.setText("Inventory!".toUpperCase());


        part_type=b.getString("partType");
        available=b.getString("availability");
        lastedit=b.getString("last_edit");
        currentlywith=b.getString("cW");
        purchasedby=b.getString("pur");
        description1=b.getString("description");
        cost1=b.getString("cost");

        String one="Part Type: "+part_type;
        String two="Availability "+available;
        String last_edit="Last Edited By: "+lastedit;
        String currently="Currently With: "+currentlywith;
        String id="UID no: "+b.getString("bC");
        String purchased="Purchased by: "+purchasedby;
        String description="Description: "+description1;
        String cost="Cost:"+cost1;
        //TODO ORDER: data[i]=(part_type[i]+", "+availability[i]+", "+last_edit[i]+", "+currently_with[i]+", "+purchased_by[i]+", "+cost[i]+", "+description[i]+", "+bar_code[i]);

            item_name.setText(b.getString("partType").toUpperCase());

            data[0]=one;
            data[1]=two;
            data[2]=last_edit;
            data[3]=currently;
            data[4]=purchased;
            data[5]=cost;
            data[6]=description;
            data[7]=id;
            this.bar_code=b.getString("bC");

        try{
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this,1,false);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            datalist.setLayoutManager(layoutManager);
            datalist.setAdapter(new ProgrammingAdapter(data, this));
        }
        catch (Exception r) {
            Toast.makeText(this, r.getMessage()+"Error in View Page", Toast.LENGTH_SHORT).show();
        }

    }}

    public void start_edit(View view){
        Intent intent = new Intent(ViewPage.this,Edit.class);
        Bundle bundle = new Bundle();
        bundle.putString("bar_code",this.bar_code);
        bundle.putString("description",this.description1);
        bundle.putString("avail",available);
        bundle.putString("purchased_by",purchasedby);
        bundle.putString("part_type",part_type);
        bundle.putString("currently_with",currentlywith);
        bundle.putString("cost",cost1);
        intent.putExtras(bundle);
        startActivity(intent);
    }*/

}}
