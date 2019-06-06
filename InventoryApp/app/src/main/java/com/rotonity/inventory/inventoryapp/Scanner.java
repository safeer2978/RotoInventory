package com.rotonity.inventory.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scanner extends Fragment {
        //qr code scanner object
    private IntentIntegrator qrScan;
    public static String barcode;
    TextView textView;
    String type;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_scanner,container,false);
        qrScan = new IntentIntegrator(getActivity());
        qrScan.setCameraId(0);
        qrScan.setBarcodeImageEnabled(true);

        qrScan.setOrientationLocked(true);
        qrScan.initiateScan();
        return view;
    }


    //Getting the scan results
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {


    }
    }
    @Override
    public void onResume() {
        super.onResume();

        // Register ourselves as a handler for scan results.
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
       // mScannerView.stopCamera();
        try{qrScan.wait();}catch (Exception e){}
    }


}
