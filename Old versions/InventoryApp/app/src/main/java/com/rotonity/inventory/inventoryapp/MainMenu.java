package com.rotonity.inventory.inventoryapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentTransaction;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MainMenu extends Fragment {RecyclerView dataList;
        TextView ooo;
        String[] data;
        InventoryItem[] inventoryItem= new InventoryItem[100];
        List<InventoryItem> inventoryItemList;
        LinearLayout layout;
        Spinner sp_part_type,sp_currently_with;
        CheckBox cb_available;
        //private OnFragmentInteractionListener mListener;
        Button button;
        LinearLayoutManager manager;
        ViewListAdaptor adaptor;
        FloatingActionButton fab;

        ArrayList<String>  tools_list;

    @Override
    public void onResume() {
      //  dataList.notifyAll();
        super.onResume();
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_mainmenu,container,false);
            sp_currently_with=view.findViewById(R.id.spinner_currently_with);
            sp_part_type=view.findViewById(R.id.spinner_part_type);
            cb_available=view.findViewById(R.id.checkBox);

            return inflater.inflate(R.layout.fragment_mainmenu, container, false);
        }

        public void populate_Spinners(){
            List<String> list_pt = MainActivity.part_type_list;
            List<String> list_member=MainActivity.members_list;
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,list_pt);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_part_type.setAdapter(adapter);
            ArrayAdapter<String> adapter_members = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,list_member);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_part_type.setAdapter(adapter_members);
        }


        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

        /*    //recycler
            RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);

            // define an adapter
            mAdapter = new ListAdapter(null, getContext());
            recyclerView.setAdapter(mAdapter);*/
            Context context=getContext();
            final DBHelper dbHelper=new DBHelper(context);
            inventoryItemList=dbHelper.getAllItems();
            manager=new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            adaptor=new ViewListAdaptor(inventoryItemList,getContext());

            try {
                dataList = view.findViewById(R.id.re);
                dataList.setLayoutManager(manager);
                dataList.setAdapter(adaptor);

            }
            catch (Exception r) {
                Toast.makeText(this.getActivity(), r.getMessage()+"65165156!", Toast.LENGTH_LONG).show();
            }

          /*  sp_part_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (sp_part_type.getSelectedItem().toString()){
                        case "motor":
                          //  inventoryItemList=dbHelper.getItemsType("motor");
                            break;
                        case "Tool":
                            //inventoryItemList=dbHelper.getItemsType("motor");
                        default:
                            inventoryItemList=dbHelper.getAllItems();
                    }

                    dataList.setAdapter(adaptor);
                }
            });*/





        }
        public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }}
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainMenu.OnFragmentInteractionListener) {
            mListener = (MainMenu.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/

