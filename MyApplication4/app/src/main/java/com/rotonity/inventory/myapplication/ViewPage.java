package com.rotonity.inventory.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewPage extends AppCompatActivity {


        TextView partType, description,cos,purchased_by,currently,uid;
        Button edit;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        partType=findViewById(R.id.partType);
        description =findViewById(R.id.description);
        cos=findViewById(R.id.cost);
        purchased_by=findViewById(R.id.purchasedBy);
        currently=findViewById(R.id.currentlyWith);
        uid=findViewById(R.id.uid);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        partType.setText(b.getString("partType"));
        description.setText(b.getString("description"));
        cos.setText(b.getString("cost"));
            purchased_by.setText(b.getString("pur"));
        currently.setText(b.getString("cW"));
        uid.setText(b.getString("bC"));
        edit=findViewById(R.id.Edit);
        id= b.getString("bC");
        if (Global.isAdmin){
            edit.setVisibility(Button.VISIBLE);
        }
          else{edit.setVisibility(Button.GONE);}
       }

       public void startEdit(View view){

        Intent i = new Intent(this,EditInventory.class);
        Bundle b = new Bundle();
        b.putString("id",id);
        i.putExtras(b);
        startActivity( i);

       }

}
