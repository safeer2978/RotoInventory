package com.rotonity.inventory.inventoryapp;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Edit extends AppCompatActivity {

    private final String last_edit=User.username;


    CheckBox avail;
    TextView barcode;
    EditText description1, cost1, purchased;
    Spinner partType, currentlyWith;
    ImageView itemType;
    ImageView im;
    Animation blink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        InventoryItem item=(InventoryItem) getIntent().getSerializableExtra("item");

        barcode=findViewById(R.id.edit_barcode);
        barcode.setText(item.getBar_code());
        // btn=findViewById(R.id.button4);
        description1 = findViewById(R.id.edit_description);
        description1.setText(item.getDescription());


        cost1 = findViewById(R.id.edit_cost);
    cost1.setText(item.getCost());
        im=findViewById(R.id.edit_item_image);

        purchased = findViewById(R.id.edit_purchased_by);
        purchased.setText(item.getPurchased_by());
        //blink = AnimationUtils.loadAnimation(this, R.anim.loading_blink);
        partType = findViewById(R.id.edit_part_type);

        currentlyWith = findViewById(R.id.edit_currently_with);

      //  itemType=findViewById(R.id.edit_part_type);
        avail=findViewById(R.id.checkBox);
        if (item.getAvailability().equals("yes")) {
            avail.setChecked(true);
        } else {
            avail.setChecked(false);
        }


        //textView.setText();

        // itemType.setImageDrawable(getDrawable(Global.getIconImage("")));
    }
    //---------------ON Add
    public void onAdd(View view){
        try{

            im.startAnimation(blink);
            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
            // VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest2);


        }catch (Exception e){}
    }



    //-- Volley DBHelper
    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.Edit_URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(response);
                        String status=obj.getString("status");
                        if(status.equals("true")) {
                            Toast.makeText(getApplicationContext(), "Add Successful!, your entry will be moderated by our admins", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        //if error in response
                        else  {
                            Toast.makeText(getApplicationContext(), "add Failed, try again!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (JSONException e) {// For RunTime Error!
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();


            params.put("partType", partType.getSelectedItem().toString());
            params.put("description", description1.getText().toString());
            params.put("cost",cost1.getText().toString());
            params.put("currentlyWith",currentlyWith.getSelectedItem().toString());
            params.put("purchased",purchased.getText().toString());
            params.put("last_edit",last_edit);
            params.put("available",avail.isChecked()?"yes":"no");
            params.put("qrResult", barcode.getText().toString());

        /*
        For Reference From PHP script
        $part_type=$_POST['partType'];
        $desc=$_POST['description'];
        $cost=$_POST['cost'];
        $currently=$_POST['currentlyWith'];
        $qr_result=$_POST['qrResult'];
        $purchased=$_POST['purchased'];*/

            return params;
        }
    };
}
