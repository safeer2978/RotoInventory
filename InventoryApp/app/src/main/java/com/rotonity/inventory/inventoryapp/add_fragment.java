package com.rotonity.inventory.inventoryapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class add_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public add_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        super.onPause();
    }



    @Override
    public void onResume() {
        try{
            add_barcode.setText(Scanner.barcode);
        }catch(NullPointerException e)
        {add_barcode.setText("");}
        super.onResume();
    }

    public     EditText Title_et, decription_et, cost_et, purchased_by_et;
    Spinner currently_with_sp, part_type_sp;
    TextView add_barcode;
    CheckBox available_cb;
    Button save;
    ImageButton scan;
    String user = "safeer";//TODO make user as shared prefeance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_fragment, container, false);
        cost_et = view.findViewById(R.id.add_cost);
        decription_et = view.findViewById(R.id.add_description);
        purchased_by_et = view.findViewById(R.id.add_purchased_by);
        Title_et = view.findViewById(R.id.add_name);
        add_barcode = view.findViewById(R.id.add_barcode);
        currently_with_sp = view.findViewById(R.id.add_currently_with);
        part_type_sp = view.findViewById(R.id.add_part_type);
        available_cb = view.findViewById(R.id.add_available);
        save=view.findViewById(R.id.add_button);
        scan=view.findViewById(R.id.imageButton);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b= new Bundle();
                b.putString("type","Add");
                Intent intent=new Intent(getActivity(),Scanner.class);
                intent.putExtras(b);
       startActivity(intent);
       onPause();
            }
        });
    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                if (purchased_by_et.getText().toString().equals("")
                        || decription_et.getText().toString().equals("")
                        || cost_et.getText().toString().equals("")
                        || Title_et.getText().toString().equals("")
                        ||add_barcode.getText().toString().equals("")) {
                    Snackbar.make(v, "Please fill all fields and scan QR code", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
                else{
                    VolleySingleton.getInstance(getActivity()).addToRequestQueue(add_volly_request);

                }}catch (Exception e){}
        }
    });

    }
    StringRequest add_volly_request = new StringRequest(Request.Method.POST, URLs.ADD_URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(response);
                        String status=obj.getString("status");
                        if(status.equals("true")) {
                             Toast.makeText(getActivity(), "Add Successful!, your entry will be moderated by our admins", Toast.LENGTH_LONG).show();
                        }
                        //if error in response
                        else  {
                            Toast.makeText(getActivity(), "add Failed, try again!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {// For RunTime Error!
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();


            params.put("partType", part_type_sp.getSelectedItem().toString() );
            params.put("description", decription_et.getText().toString());
            params.put("cost",cost_et.getText().toString());
            params.put("currentlyWith", currently_with_sp.getSelectedItem().toString());
            params.put("purchased",purchased_by_et.getText().toString());
            params.put("last_edit",user);
            params.put("available", available_cb.isChecked()?"yes":"no");
            params.put("qrResult", add_barcode.getText().toString());

            return params;
        }
    };



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
