package com.rotonity.inventory.inventoryapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class fragment_myInventory extends Fragment {

    TextView ooo;
    String[] data;
    RecyclerView dataList;
    InventoryItem[] inventoryItem= new InventoryItem[100];
    List<InventoryItem> inventoryItemList;

    //private OnFragmentInteractionListener mListener;

    LinearLayoutManager manager;
    ViewListAdaptor adaptor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainmenu,container,false);


        return inflater.inflate(R.layout.fragment_mainmenu, container, false);
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
        DBHelper dbHelper=new DBHelper(context);
        inventoryItemList=dbHelper.getItemWith("safeer");
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





    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }}
